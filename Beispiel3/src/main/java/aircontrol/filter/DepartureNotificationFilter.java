package aircontrol.filter;

import rbs.flight.IFlight;

public class DepartureNotificationFilter implements INotificationFilter<IFlight> {
	private String pattern;
	
	public DepartureNotificationFilter(String pattern) {
		this.pattern = (pattern != null) ? pattern : ".*";
	}
	
	public boolean check(IFlight compareObject) {
		return compareObject.getDeparture().matches(this.pattern);
	}
}
