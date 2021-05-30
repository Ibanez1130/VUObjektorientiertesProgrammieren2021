package aircontrol;

import aircontrol.airline.IAirlineListener;
import aircontrol.airline.IAirlineObservable;

public interface IAirControlStation {
	public void addNotificationFilter(IAirlineListener airlineListener, IAirlineObservable airlineObservable);
	public void registerAirlineListenerAtAirlineObservable(IAirlineListener airlineListener, IAirlineObservable airlineObservable);
}
