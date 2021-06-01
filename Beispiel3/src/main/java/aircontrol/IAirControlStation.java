package aircontrol;

import rbs.flight.IFlight;
import aircontrol.filter.INotificationFilter;
import aircontrol.airline.IAirlineListener;
import aircontrol.airline.IAirlineObservable;

public interface IAirControlStation {
	public void addNotificationFilter(IAirlineListener airlineListener, IAirlineObservable airlineObservable, INotificationFilter<IFlight> filter);
	public void registerAirlineListenerAtAirlineObservable(IAirlineListener airlineListener, IAirlineObservable airlineObservable);
}
