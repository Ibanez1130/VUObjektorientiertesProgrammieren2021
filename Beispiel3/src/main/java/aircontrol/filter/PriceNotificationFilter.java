package aircontrol.filter;

import rbs.flight.IFlight;

public class PriceNotificationFilter implements INotificationFilter<IFlight> {
	private double priceLimit;
	
	public PriceNotificationFilter() {
		this.priceLimit = Double.MAX_VALUE;
	}
	
	public PriceNotificationFilter(double priceLimit) {
		this.priceLimit = priceLimit;
	}
	
	public boolean check(IFlight compareObject) {
		return compareObject.getPrice() <= this.priceLimit;
	}
}
