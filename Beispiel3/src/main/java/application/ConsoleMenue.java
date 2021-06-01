package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import rbs.flight.OneWayFlight;
import rbs.flight.DualFlight;
import rbs.flight.IFlight;
import aircontrol.airline.IAirline;
import aircontrol.airline.AirlineAlreadyExists;
import aircontrol.terminal.ITerminal;
import aircontrol.AirControlStation;
import aircontrol.airline.IAirlineListener;
import aircontrol.airline.IAirlineObservable;
import aircontrol.filter.DepartureNotificationFilter;
import aircontrol.filter.DestinationNotificationFilter;
import aircontrol.filter.PriceNotificationFilter;
import aircontrol.filter.FlightFacadeFilter;

public class ConsoleMenue {

	public static void main(String[] args) {
		AirControlStation aircontrol = AirControlStation.GET_INSTANCE();
		
		Collection<IFlight> c = new ArrayList<IFlight>();
		IFlight flight0 = new OneWayFlight("AA101", "Wien", "Neapel", 100.0f);
		IFlight flight1 = new OneWayFlight("AD104", "Wien", "Washington", 650.0f);
		c.add(flight0);
		c.add(new OneWayFlight("AB102", "Wien", "Stockholm", 150.0f));
		c.add(new OneWayFlight("AC103", "Wien", "Tokyo", 750.0f));
		c.add(new OneWayFlight("AD104", "Wien", "Washington", 650.0f));
		c.add(new OneWayFlight("AE105", "Wien", "Berlin", 50.0f));
		List<IFlight> l3 = new ArrayList<IFlight>();
		l3.add(new OneWayFlight("AE405", "Tokyo", "Moskau", 700.0f));
		l3.add(new OneWayFlight("AF406", "Moskau", "Tokyo", 680.0f));
		c.add(new DualFlight("CC503", l3));
		
		System.out.println(new OneWayFlight("AC103", "Wien", "Tokyo", 750.0f).toString());
		
		try {
			IAirline airline = aircontrol.createAirline("Austrian Airlines");
			ITerminal terminal = aircontrol.createTerminal();
			aircontrol.registerAirlineListenerAtAirlineObservable((IAirlineListener) terminal, (IAirlineObservable) airline);
			// aircontrol.addNotificationFilter((IAirlineListener) terminal0, (IAirlineObservable) airline, new PriceNotificationFilter(150.0));
			// aircontrol.addNotificationFilter((IAirlineListener) terminal1, (IAirlineObservable) airline, new DepartureNotificationFilter("Wien"));
			// aircontrol.addNotificationFilter((IAirlineListener) terminal2, (IAirlineObservable) airline, new DestinationNotificationFilter("Tokyo"));
			aircontrol.addNotificationFilter((IAirlineListener) terminal, (IAirlineObservable) airline, new FlightFacadeFilter("Wien", null, 700));
			airline.addFlights(c);
			flight0.updatePrice(120);
			airline.updateFlight(flight0);
			airline.removeFlight(flight1);
			terminal.outputLogEntries();
		} catch (AirlineAlreadyExists e) {
			System.out.println("Airline \"Austrial Airlines\" already exists!");
		}
	}
}
