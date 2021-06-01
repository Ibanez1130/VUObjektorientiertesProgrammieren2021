package aircontrol.airline;

import org.javatuples.Pair;
import rbs.flight.IFlight;
import aircontrol.filter.INotificationFilter;
import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Airline implements IAirline, IAirlineObservable {
	private String name;
	private Collection<IFlight> flights = new ArrayList<IFlight>();
	protected Collection<Pair<IListener<IFlight>, Collection<INotificationFilter<IFlight>>>> listeners = new ArrayList<>();

	public Airline(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean addFlight(IFlight flight) {
		if (flight == null) return false;
		if (this.flights.contains(flight)) return false;
		if (!(this.flights.add(flight))) return false;
		this.notifyListeners(ActionType.ADD, flight.deepCopy());
		return true;
	}
	
	public boolean addFlights(Collection<IFlight> flights) {
		for (IFlight f : flights) {
			if (!(this.addFlight(f))) return false;
		}
		return true;
	}
	
	public boolean removeFlight(IFlight flight) {
		if (flight == null) return false;
		this.flights = this.flights
			.stream()
			.filter(f -> !f.equals(flight))
			.collect(Collectors.toList());
		this.notifyListeners(ActionType.DELETE, flight.deepCopy());
		return true;
	}
	
	public boolean updateFlight(IFlight flight) {
		if (flight == null) return false;
		for (IFlight f : this.flights) {
			if (f.equals(flight)) {
				f.updatePrice(flight.getPrice());
				this.notifyListeners(ActionType.UPDATE, flight.deepCopy());
				return true;
			}
		}
		return false;
	}

	public void registerListener(IListener<IFlight> listener) {
		if (listener == null) return;
		for (Pair<IListener<IFlight>, Collection<INotificationFilter<IFlight>>> p : this.listeners) {
			if (p.getValue0().equals(listener)) return;
		}
		if (listener instanceof IAirlineListener) {
			for (IFlight f : this.flights) {
				((IAirlineListener) listener).flightAdded(f);
			}
		} else {
			for (IFlight f : this.flights) {
				listener.notifyChange(f);
			}
		}
		this.listeners.add(new Pair<IListener<IFlight>, Collection<INotificationFilter<IFlight>>>(listener, (Collection<INotificationFilter<IFlight>>) new ArrayList<INotificationFilter<IFlight>>()));
		
	}

	public void unregisterListener(IListener<IFlight> listener) {
		if (listener == null) return;
		this.listeners = this.listeners
				.stream()
				.filter(p -> !p.getValue0().equals(listener))
				.collect(Collectors.toList());
	}
	
	public void addNotificationFilter(IAirlineListener listener, INotificationFilter<IFlight> filter) {
		for (Pair<IListener<IFlight>, Collection<INotificationFilter<IFlight>>> p : this.listeners) {
			if (p.getValue0().equals(listener)) {
				p.getValue1().add(filter);
			}
		}
	}

	protected void notifyListeners(ActionType type, IFlight flight) {
		if (type == null || flight == null) throw new NullPointerException();
		for (Pair<IListener<IFlight>, Collection<INotificationFilter<IFlight>>> p : this.listeners) {
			IListener<IFlight> l = p.getValue0();
			if (l instanceof IAirlineListener) {
				IAirlineListener al = (IAirlineListener) l;
				if (type == ActionType.ADD) {
					for (INotificationFilter<IFlight> nf : p.getValue1()) {
						if (!(nf.check(flight))) return;
					}
					al.flightAdded(flight);
				} else if (type == ActionType.UPDATE) {
					for (INotificationFilter<IFlight> nf : p.getValue1()) {
						if (!(nf.check(flight))) return;
					}
					al.notifyChange(flight);
				} else if (type == ActionType.DELETE) {
					al.flightRemoved(flight);
				}
			} else {
				l.notifyChange(flight);
			}
		}
	}
}
