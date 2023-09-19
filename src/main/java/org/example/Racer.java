package org.example;

public class Racer {

	private String racerAbbreviation;
	private String team;
	private final String startTime;
	private String endTime;
	private String lapTime;

	public Racer(String racerAbbreviation, String startTime) {
		this.racerAbbreviation = racerAbbreviation;
		this.team = null;
		this.startTime = startTime;
		this.endTime = null;
	}

	public void setLapTime(String lapTime) {
		this.lapTime = lapTime;
	}

	public String getLapTime() {
		return lapTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setRacerAbbreviation(String racerAbbreviation) {
		this.racerAbbreviation = racerAbbreviation;
	}

	public String getRacerAbbreviation() {
		return racerAbbreviation;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeam() {
		return team;
	}
}
