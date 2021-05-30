package rbs.flight;

/**
 * Implementation of a flight that only consists of a single flight with an id,
 * a departure, a destination and a price. It extends the abstract {@link Flight} class.
 *
 *
 */
public class OneWayFlight extends Flight{
    /**
     * Constructor that takes four arguments (id, departure, destination and price) and initializes the
     * flight.
     * <p>
     * The passed string arguments can be null or an empty string. If the passed argument is
     * null the attribute is initialized with an empty string.
     *
     * @param id
     *            the id of the flight
     * @param departure
     *            the departure of the flight
     * @param destination
     *            the destination of the flight
     * @param price
     *            the price of the flight
     */
    public OneWayFlight(String id, String departure, String destination, float price) {
        super(id, departure, destination, price);
    }

    /**
     * Constructor that takes three arguments (id, departure, destination) and initializes the
     * flight.
     * <p>
     * The passed string arguments can be null or an empty string. If the passed argument is
     * null the attribute is initialized with an empty string. The price is set to a default value of 100.
     *
     * @param id
     *            the id of the flight
     * @param departure
     *            the departure of the flight
     * @param destination
     *            the destination of the flight
     */
    public OneWayFlight(String id, String departure, String destination) {
        super(id, departure, destination);
    }


    @Override
    public OneWayFlight deepCopy() {
        return new OneWayFlight(this.getFlightId(), this.getDeparture(), this.getDestination(), this.getPrice());
    }
}
