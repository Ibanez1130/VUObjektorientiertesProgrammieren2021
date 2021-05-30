package aircontrol;

import aircontrol.airline.IAirline;
import aircontrol.airline.AirlineAlreadyExists;

public interface IAirlineFactory {
	public IAirline createAirline(String name) throws AirlineAlreadyExists;
}
