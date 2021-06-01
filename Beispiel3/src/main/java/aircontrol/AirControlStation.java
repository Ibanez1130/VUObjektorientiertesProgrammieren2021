package aircontrol;

import java.util.ArrayList;
import java.util.Collection;

import rbs.flight.IFlight;
import aircontrol.airline.Airline;
import aircontrol.airline.IAirline;
import aircontrol.airline.IAirlineListener;
import aircontrol.airline.IAirlineObservable;
import aircontrol.terminal.Terminal;
import aircontrol.terminal.ITerminal;
import aircontrol.filter.INotificationFilter;
import aircontrol.airline.AirlineAlreadyExists;

public class AirControlStation implements IAirControlStation, IAirlineFactory, ITerminalFactory {
	private static AirControlStation INSTANCE;
	private static long TERMINAL_ID = 0;
	private Collection<ITerminal> terminals = new ArrayList<>();
	private Collection<IAirline> airlines = new ArrayList<>();
	private Collection<IAirlineListener> listeners = new ArrayList<>();
	private Collection<IAirlineObservable> observables = new ArrayList<>();
	
	private AirControlStation() {
		AirControlStation.INSTANCE = this;
	}
	
	public static AirControlStation GET_INSTANCE() {
		if (AirControlStation.INSTANCE != null) return AirControlStation.INSTANCE;
		return new AirControlStation();
	}
	
	public ITerminal createTerminal() {
		ITerminal t = new Terminal(AirControlStation.TERMINAL_ID);
		if (t instanceof IAirlineListener) this.listeners.add((IAirlineListener) t);
		AirControlStation.TERMINAL_ID++;
		this.terminals.add(t);
		return t;
	}

	public IAirline createAirline(String name) throws AirlineAlreadyExists {
		for (IAirline a : this.airlines) {
			if (((Airline) a).getName().equals(name)) throw new AirlineAlreadyExists();
		}
		IAirline na = new Airline(name);
		this.airlines.add(na);
		if (na instanceof IAirlineObservable) this.observables.add((IAirlineObservable) na);
		return na;
	}

	public void registerAirlineListenerAtAirlineObservable(IAirlineListener airlineListener, IAirlineObservable airlineObservable) {
		if (!(this.listeners.contains(airlineListener) && this.observables.contains(airlineObservable))) return;
		airlineObservable.registerListener(airlineListener);
	}

	public void addNotificationFilter(IAirlineListener airlineListener, IAirlineObservable airlineObservable, INotificationFilter<IFlight> filter) {
		if (!(this.listeners.contains(airlineListener) && this.observables.contains(airlineObservable))) return;
		airlineObservable.addNotificationFilter(airlineListener, filter);
	}
}
