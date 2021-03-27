package rbs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import rbs.flight.IFlight;
import rbs.flight.OneWayFlight;
import rbs.flight.MultiStopFlight;
import rbs.flight.RoundTripFlight;
import rbs.flight.FlightsNotConnectedException;

public class BookingSystem {
	private List<TravelAgency> agencies = new ArrayList<TravelAgency>();
	private List<IFlight> flightPlan = new ArrayList<IFlight>();
	private List<Booking> bookings = new ArrayList<Booking>();
	
	public void createAgency(String name) {
		this.agencies.add(new TravelAgency(name));
	}
	
	public List<String> getAgencyNames() {
		return this.agencies
				.stream()
				.map(a -> a.getName())
				.collect(Collectors.toList());
	}
	
	public TravelAgency findAgency(String name) {
		for (TravelAgency a : this.agencies) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public TravelAgency findAgency(TravelAgency agency) {
		for (TravelAgency a : this.agencies) {
			if (agency.equals(a)) {
				return a;
			}
		}
		return null;
	}
	
	public boolean containsFlight(IFlight flight) {
		// System.out.println(this.flightPlan.stream().map(f -> f.getFlightId()).collect(Collectors.joining(", ")));
		for (IFlight f : this.flightPlan) {
			if (flight.equals(f)) return true;
		}
		return false;
	}
	
	public boolean addFlight(IFlight flight) {
		if (this.containsFlight(flight)) return false;
		return this.flightPlan.add(flight.deepCopy());
	}
	
	public boolean addFlight(List<IFlight> flights) {
		List<Boolean> s = flights
			.stream()
			.map(f -> this.addFlight(f))
			.collect(Collectors.toList());
		for (boolean b : s) {
			if (!b) return false;
		}
		return true;
	}
	
	public long bookFlight(IFlight flight, TravelAgency agency) throws NotBookableException {
		if (!this.containsFlight(flight)) throw new NotBookableException();
		List<IFlight> fl = new ArrayList<IFlight>();
		fl.add(flight);
		Booking b = new Booking(Booking.generateBookingId(), agency, fl);
		this.bookings.add(b);
		return b.getId();
	}
	
	public long bookFlight(List<IFlight> flights, TravelAgency agency) throws NotBookableException {
		List<Boolean> s = flights
				.stream()
				.map(f -> this.containsFlight(f))
				.collect(Collectors.toList());
		for (boolean b : s) {
			if (!b) throw new NotBookableException();
		}
		Booking b = new Booking(Booking.generateBookingId(), agency, flights);
		return b.getId();
	}
	
	public List<IFlight> getFlights() {
		return this.flightPlan;
	}
	
	public Booking getBookingById(long id) {
		for (Booking b : this.bookings) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}
	
	public boolean addFlightToBooking(IFlight flight, long bookingId) {
		Booking b = this.getBookingById(bookingId);
		if ((b == null) || (b.getState() != BookingState.OPEN)) return false;
		if (!this.containsFlight(flight)) return false;
		b.addFlight(flight);
		return true;
	}
	
	protected IFlight generateReturnFlight(OneWayFlight flight) {
		OneWayFlight r = new OneWayFlight(flight.getFlightId() + "R", flight.getDestination(), flight.getDeparture(), flight.getPrice());
		List<IFlight> flightsWithSimilarId = this.flightPlan
					.stream()
					.filter(f -> f.getFlightId().equals(r.getFlightId()))
					.collect(Collectors.toList());
		if ((flightsWithSimilarId.size() == 0) && (this.addFlight(r))) return r;
		return null;
	}
	
	private static List<IFlight> generateOneWayFlights() {
		List<IFlight> l = new ArrayList<IFlight>();
		l.add(new OneWayFlight("AA101", "Wien", "Neapel", 100.0f));
		l.add(new OneWayFlight("AB102", "Wien", "Stockholm", 150.0f));
		l.add(new OneWayFlight("AC103", "Wien", "Tokyo", 750.0f));
		l.add(new OneWayFlight("AD104", "Wien", "Washington", 650.0f));
		l.add(new OneWayFlight("AE105", "Wien", "Berlin", 50.0f));
		return l;
	}
	
	private static List<IFlight> generateMultiStopFlights() {
		List<IFlight> l = new ArrayList<IFlight>();
		
		List<IFlight> l1 = new ArrayList<IFlight>();
		l1.add(new OneWayFlight("AA201", "Wien", "Neapel", 100.0f));
		l1.add(new OneWayFlight("AB202", "Neapel", "Stockholm", 150.0f));
		l1.add(new OneWayFlight("AC203", "Stockholm", "Tokyo", 750.0f));
		try {
			l.add(new MultiStopFlight("BA301", l1));
		} catch (FlightsNotConnectedException e) {
			System.out.println("Die Flüge in L1 sind nicht miteinander verbunden!");
		}
		
		List<IFlight> l2 = new ArrayList<IFlight>();
		l2.add(new OneWayFlight("AD204", "Wien", "Singapur", 700.0f));
		l2.add(new OneWayFlight("AE205", "Singapur", "Peking", 250.0f));
		try {
			l.add(new MultiStopFlight("BB302", l2));
		} catch (FlightsNotConnectedException e) {
			System.out.println("Die Flüge in L2 sind nicht miteinander verbunden!");
		}
		
		List<IFlight> l3 = new ArrayList<IFlight>();
		l3.add(new OneWayFlight("AF206", "Wien", "Mailand", 150.0f));
		l3.add(new OneWayFlight("AG207", "Mailand", "Rom", 80.0f));
		l3.add(new OneWayFlight("AH208", "Rom", "Athen", 210.0f));
		l3.add(new OneWayFlight("AI209", "Athen", "Istanbul", 250.0f));
		try {
			l.add(new MultiStopFlight("BC303", l3));
		} catch (FlightsNotConnectedException e) {
			System.out.println("Die Flüge in L3 sind nicht miteinander verbunden!");
		}
		
		List<IFlight> l4 = new ArrayList<IFlight>();
		l4.add(new OneWayFlight("AF206", "Paris", "Salzburg", 150.0f));
		l4.add(new OneWayFlight("AG207", "Salzburg", "Wien", 80.0f));
		try {
			l4.add(new MultiStopFlight("BD304", l2));
			l.add(new MultiStopFlight("BE305", l4));
		} catch (FlightsNotConnectedException e) {
			System.out.println("Die Flüge in L4 sind nicht miteinander verbunden!");
		}
		
		return l;
	}
	
	private static List<IFlight> generateRoundTripFlights() {
		List<IFlight> l = new ArrayList<IFlight>();

		List<IFlight> l1 = new ArrayList<IFlight>();
		l1.add(new OneWayFlight("AA401", "Wien", "Singapur", 700.0f));
		l1.add(new OneWayFlight("AB402", "Singapur", "Wien", 750.0f));
		l.add(new RoundTripFlight("CA501", l1));
		
		List<IFlight> l2 = new ArrayList<IFlight>();
		l2.add(new OneWayFlight("AC403", "Paris", "Washington", 650.0f));
		l2.add(new OneWayFlight("AD404", "Washington", "Paris", 550.0f));
		l.add(new RoundTripFlight("CB502", l2));
		
		List<IFlight> l3 = new ArrayList<IFlight>();
		l3.add(new OneWayFlight("AE405", "Tokyo", "Moskau", 700.0f));
		l3.add(new OneWayFlight("AF406", "Moskau", "Tokyo", 680.0f));
		l.add(new RoundTripFlight("CC503", l3));
		
		return l;
	}

	public static void main(String[] args) {
		BookingSystem system = new BookingSystem();

		system.addFlight(BookingSystem.generateOneWayFlights());
		system.addFlight(BookingSystem.generateMultiStopFlights());
		system.addFlight(BookingSystem.generateRoundTripFlights());

		// This should fail:
		System.out.println(system.addFlight(new OneWayFlight("AA101", "Wien", "Neapel", 100.0f)));
		// end of fail

		system.createAgency("Ruefa");
		system.createAgency("Hofer Reisen");

		try {
			long bookingId = system.bookFlight(new OneWayFlight("AA101", "Wien", "Neapel", 100.0f), system.findAgency("Ruefa"));
			system.getBookingById(bookingId).addFlight(new OneWayFlight("AB102", "Wien", "Stockholm", 150.0f));
			system.bookFlight(new OneWayFlight("AC103", "Wien", "Tokyo", 750.0f), system.findAgency("Ruefa"));
		} catch (NotBookableException e) {
			System.out.println("Die Flüge AA101 und AC103 konnten nicht gebucht werden.");
		}

		try {
			system.bookFlight(BookingSystem.generateMultiStopFlights().get(0), system.findAgency("Hofer Reisen"));
			system.bookFlight(new OneWayFlight("AE105", "Wien", "Berlin", 50.0f), system.findAgency("Hofer Reisen"));
		} catch (NotBookableException e) {
			System.out.println("Der Multiflug konnte nicht gebucht werden.");
		}
	}
}
