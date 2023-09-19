package org.example;

public enum RacerAbbreviation {
	SVF ("Sebastian Vettel", "FERRARI"),
	DRR ("Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
	LHM ("Lewis Hamilton", "MERCEDES"),
	KRF ("Kimi Raikkonen", "FERRARI"),
	VBM ("Valtteri Bottas", "MERCEDES"),
	EOF ("Esteban Ocon", "FORCE INDIA MERCEDES"),
	FAM ("Fernando Alonso", "MCLAREN RENAULT"),
	CSR ("Carlos Sainz", "RENAULT"),
	SPF ("Sergio Perez", "FORCE INDIA MERCEDES"),
	PGS ("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"),
	NHR ("Nico Hulkenberg", "RENAULT"),
	SVM ("Stoffel Vandoorne", "MCLAREN RENAULT"),
	SSW ("Sergey Sirotkin", "WILLIAMS MERCEDES"),
	CLS ("Charles Leclerc", "SAUBER FERRARI"),
	RGH ("Romain Grosjean", "HAAS FERRARI"),
	BHS ("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"),
	MES ("Marcus Ericsson", "SAUBER FERRARI"),
	LSW ("Lance Stroll", "WILLIAMS MERCEDES"),
	KMH ("Kevin Magnussen", "HAAS FERRARI");

	private final String racerName;
	private final String teamName;

	RacerAbbreviation(String racerName, String teamName) {
		this.racerName = racerName;
		this.teamName = teamName;
	}

	public String getRacerName() {
		return racerName;
	}

	public String getTeamName() {
		return teamName;
	}
}
