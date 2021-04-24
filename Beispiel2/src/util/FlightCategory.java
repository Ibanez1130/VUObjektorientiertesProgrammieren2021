package util;

public enum FlightCategory {
	DEFAULT("default"),
	DUAL("dual"),
	ONEWAY("oneway");

	private final String label;
	
	private FlightCategory (String label) {
		this.label = label;
	}

	public String getLabel () {
		return this.label;
	}
}
