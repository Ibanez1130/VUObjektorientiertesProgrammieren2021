package rbs;

import java.util.List;
import java.util.ArrayList;
import rbs.flight.IFlight;
import rbs.record.Record;

public class Booking extends Record {
	private List<IFlight> flights = new ArrayList<IFlight>();
	private TravelAgency agency;
	private BookingState state;
	private static long uniqueBookingId = 0;
	
	public Booking(long id, TravelAgency agency, List<IFlight> flights) {
		super(id);
		this.agency = agency;
		this.state = BookingState.OPEN;
		this.flights = flights;
	}
	
	public List<IFlight> getFlights() {
		return this.flights;
	}
	
	public void addFlight(IFlight flight) {
		this.flights.add(flight.deepCopy());
	}
	
	protected boolean setState(BookingState state) {
		if ((this.getState() == BookingState.OPEN) && (state == BookingState.PAID)) return false;
		this.state = state;
		return true;
	}
	
	public BookingState getState() {
		return this.state;
	}
	
	public boolean isPaid() {
		return (this.getState() == BookingState.PAID);
	}
	
	public TravelAgency getAgency() {
		return this.agency;
	}
	
	public static long generateBookingId() {
		long bookingId = Booking.uniqueBookingId;
		Booking.uniqueBookingId += 1;
		return bookingId;
	}
}
