package aircontrol.filter;

import rbs.flight.IFlight;

public class DestinationNotificationFilter implements INotificationFilter<IFlight> {
	private String pattern;
	
	public DestinationNotificationFilter(String pattern) {
		this.pattern = (pattern != null) ? pattern : ".*";
	}
	
	public boolean check(IFlight compareObject) {
		return compareObject.getDestination().matches(this.pattern);
	}
}
