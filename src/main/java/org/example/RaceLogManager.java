package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RaceLogManager {
	public List<Racer> getSortedRaceResult() throws IOException {
		Map<String, Racer> racerMap = prepareRaceData();
		List<Racer> sortedRacers = sortRacers(racerMap.values());

		printRaceResult(sortedRacers);

		return sortedRacers;
	}

	public Map<String, Racer> prepareRaceData() throws IOException {
		Map<String, Racer> racers = getCollectedStartData();

		BufferedReader endLogReader = readFile("end.log");
		endLogReader.lines().forEach(stringLine -> setAdditionalData(racers, stringLine));
		endLogReader.close();

		return racers;
	}

	public Map<String, Racer> getCollectedStartData() throws IOException {
		BufferedReader startLogReader = readFile("start.log");

		Map<String, Racer> racers = startLogReader.lines()
			.map(this::setAdditionalData)
			.collect(Collectors.toMap(Racer::getRacerAbbreviation, Function.identity()));
		startLogReader.close();

		return racers;
	}

	public BufferedReader readFile(String fileName) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);

		return new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
	}

	public Racer setAdditionalData(String raceData) {
		String racerAbbreviation = raceData.substring(0, 3);
		String startLapTime = raceData.substring(14);

		return new Racer(racerAbbreviation, startLapTime);
	}

	private void setAdditionalData(Map<String, Racer> racerMap, String raceData) {
		String racerAbbreviation = raceData.substring(0, 3);
		String endLapTime = raceData.substring(14);

		RacerAbbreviation abbreviation = RacerAbbreviation.valueOf(racerAbbreviation);

		Racer racer = racerMap.get(racerAbbreviation);
		racer.setEndTime(endLapTime);
		racer.setTeam(abbreviation.getTeamName());
		racer.setRacerAbbreviation(abbreviation.getRacerName());
		racer.setLapTime(calculateTime(racer.getStartTime(), racer.getEndTime()));
	}

	public List<Racer> sortRacers(Collection<Racer> racers) {
		return racers
			.stream()
			.sorted(Comparator.comparing(Racer::getLapTime))
			.collect(Collectors.toList());
	}

	public String calculateTime(String startLapTime, String endLapTime){
		LocalTime startTime = LocalTime.parse(startLapTime);
		LocalTime endTime = LocalTime.parse(endLapTime);
		Duration timeDifference = Duration.between(startTime, endTime);
		String durationToString = timeDifference.toString();
		Duration duration = Duration.parse(durationToString);
		String lapTime = String.format("%01d:%02d.%02d", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());

		return makeThreeDigits(lapTime);
	}

	public String makeThreeDigits(String lapTime) {
		int index = lapTime.indexOf('.');

		if (index == -1) {
			return lapTime;
		}

		String decimalValue = lapTime.substring(index + 1);

		if (decimalValue.length() == 2) {
			decimalValue = "0" + decimalValue;
		}

		return lapTime.substring(0, index + 1) + decimalValue;
	}

	private void printRaceResult(List<Racer> racers){
		for (int i = 0; i < 15; i++) {
			Racer racer = racers.get(i);
			System.out.println(i + 1 + ". " + racer.getRacerAbbreviation() + " | " + racer.getTeam() + " | " + racer.getLapTime());
		}

		System.out.println("------------------------------------------------------------------------");

		for (int i = 15; i < racers.size(); i++) {
			Racer racer = racers.get(i);
			System.out.println(i + 1 + ". " + racer.getRacerAbbreviation() + " | " + racer.getTeam() + " | " + racer.getLapTime());
		}
	}
}
