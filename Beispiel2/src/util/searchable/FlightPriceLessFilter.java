package util.searchable;

import rbs.flight.Flight;
import rbs.flight.IFlight;

public class FlightPriceLessFilter implements ISearchFilter {
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		if (originalObject == null || compareObject == null) return false;
		if (!(originalObject instanceof Flight && compareObject instanceof Flight)) return false;
		IFlight o = (IFlight) originalObject;
		IFlight c = (IFlight) compareObject;
		return o.getPrice() < c.getPrice();
	}
}
