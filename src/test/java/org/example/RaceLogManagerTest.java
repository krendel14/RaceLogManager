package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RaceLogManagerTest {
	RaceLogManager raceLogManager = new RaceLogManager();
	List<Racer> expectedList = new ArrayList<>();
	@TempDir
	Path tempDir;

	@Test
	void testIsSortedRaceResultCorrect() throws IOException {

		Racer racer1 = new Racer("Sebastian Vettel", "12:02:58.917");
		racer1.setTeam("FERRARI");
		racer1.setEndTime("12:04:03.332");
		racer1.setLapTime("1:04.415");

		Racer racer2 = new Racer("Daniel Ricciardo", "12:14:12.054");
		racer2.setTeam("RED BULL RACING TAG HEUER");
		racer2.setEndTime("12:15:24.067");
		racer2.setLapTime("1:12.013");

		Racer racer19 = new Racer("Kevin Magnussen", "12:02:51.003");
		racer19.setTeam("HAAS FERRARI");
		racer19.setEndTime("12:04:04.396");
		racer19.setLapTime("1:13.393");

		expectedList.add(racer1);
		expectedList.add(racer2);
		expectedList.add(racer19);

		List<Racer> result = raceLogManager.getSortedRaceResult();
		for (int i = 0; i < 2; i++) {
			assertEquals(expectedList.get(i).getRacerAbbreviation(),result.get(i).getRacerAbbreviation());
			assertEquals(expectedList.get(i).getLapTime(),result.get(i).getLapTime());
			assertEquals(expectedList.get(i).getTeam(),result.get(i).getTeam());
			assertEquals(expectedList.get(i).getStartTime(),result.get(i).getStartTime());
			assertEquals(expectedList.get(i).getEndTime(),result.get(i).getEndTime());
		}

		assertEquals(expectedList.get(expectedList.size() - 1).getRacerAbbreviation(),result.get(result.size() - 1).getRacerAbbreviation());
		assertEquals(expectedList.get(expectedList.size() - 1).getLapTime(),result.get(result.size() - 1).getLapTime());
		assertEquals(expectedList.get(expectedList.size() - 1).getTeam(),result.get(result.size() - 1).getTeam());
		assertEquals(expectedList.get(expectedList.size() - 1).getStartTime(),result.get(result.size() - 1).getStartTime());
		assertEquals(expectedList.get(expectedList.size() - 1).getEndTime(),result.get(result.size() - 1).getEndTime());

	}

	@Test
	void testIsReadFileWork() throws IOException {
		Path startLogFile = Files.createFile(tempDir.resolve("start.log"));
		Files.writeString(startLogFile, "SVF2018-05-24_12:02:58.917");
		BufferedReader reader = raceLogManager.readFile("start.log");
		assertNotNull(reader);
	}

	@Test
	void testDoesCalculateTimeWork() {
		String startTime = "12:13:04.512";
		String endTime = "12:14:17.169";
		String expected = "1:12.657";
		String lapTime = raceLogManager.calculateTime(startTime, endTime);
		assertEquals(expected, lapTime);
	}

	@Test
	void testDoesMakeThreeDigitsWork() {
		String givingTime = "1:12.57";
		String expected = "1:12.057";
		String lapTime = raceLogManager.makeThreeDigits(givingTime);
		assertEquals(expected, lapTime);
	}
}
