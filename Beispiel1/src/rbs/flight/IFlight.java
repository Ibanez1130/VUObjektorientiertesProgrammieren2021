package rbs.flight;

public interface IFlight {
	public String getFlightId();
	public float getPrice();
	public String getDestination();
	public String getDeparture();
	public IFlight deepCopy();
}
