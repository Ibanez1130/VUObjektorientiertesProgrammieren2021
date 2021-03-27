package rbs.flight;

public abstract class Flight implements IFlight {
	private String flightId;
	protected String departure;
	protected String destination;
	private float price;

	public Flight (String id, String departure, String destination, float price) {
		this.flightId = id;
		this.departure = departure;
		this.destination = destination;
		this.price = price;
	}
	
	public Flight (String id, String departure, String destination) {
		this(id, departure, destination, 100);
	}
	
	public Flight (String id) {
		this(id, "", "");
	}
	
	public String getFlightId() {
		return this.flightId;
	}
	
	public float getPrice() {
		return this.price;
	}

	public String getDestination() {
		return this.destination;
	}
	
	public String getDeparture() {
		return this.departure;
	}
	
	public String toString() {
		return "Flight [ id = " + this.getFlightId() + ", departure = " + this.getDeparture() + ", destination = " + this.getDestination() + ", price = " + this.getPrice() + " ]";
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}
