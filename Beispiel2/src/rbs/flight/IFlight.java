package rbs.flight;

import rbs.copy.IDeepCopy;

/**
 * Specifies the behavior of an element that represents a flight.
 */
public interface IFlight extends IDeepCopy {

    /**
     * Returns the flightId of the flight.
     *
     * @return the flightId
     */
    public abstract String getFlightId();

    /**
     * Returns the price of the flight.
     *
     * @return the price
     */
    public abstract float getPrice();

    /**
     * Returns the departure of the flight.
     *
     * @return the departure
     */
    public abstract String getDeparture();

    /**
     * Returns the destination of the flight.
     *
     * @return the destination
     */
    public abstract String getDestination();
}
