package rbs.flight;

import java.util.Objects;

/**
 * This class is used to encapsulate the basic properties of a flight. In this
 * case a flight consists of four attributes: its name, its departure, its destination
 * and its price. This class is used as a common abstract superclass to concrete implementations
 * given further down in the hierarchy.
 * <p>
 * Attributes of the class:
 * <ul>
 * <li> id of the flight
 * <li> departure of the flight
 * <li> destination of the flight
 * <li> price of the flight (as a point number)
 * </ul>
 *
 */
public abstract class Flight implements IFlight {

    /** The id. */
    private String id;

    /** The departure. */
    protected String departure;

    /** The destination. */
    protected String destination;

    /** The price. */
    private float price;

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

    public Flight(String id, String departure, String destination, float price) {
        this.id = Objects.toString(id, "");
        this.departure = Objects.toString(departure, "");
        this.destination = Objects.toString(destination, "");
        this.price = price;
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
    public Flight(String id, String departure, String destination) {
        this(id, departure, destination, 100);
    }

    /**
     * Constructor that takes one argument (id) and initializes the
     * flight.
     * <p>
     * The passed string arguments can be null or an empty string. If the passed argument is
     * null the attribute is initialized with an empty string.
     *
     * @param id
     *            the id of the flight
     */
    protected Flight(String id) { this(id, "", ""); }

    /**
     * Returns the id of the flight.
     *
     * @return the id of the flight.
     */
    public String getFlightId() { return this.id; };

    /**
     * Returns the departure of the flight.
     *
     * @return the departure of the flight.
     */
    public String getDeparture() { return this.departure; };

    /**
     * Returns the destination of the flight.
     *
     * @return the destination of the flight.
     */
    public String getDestination() { return this.destination; };

    /**
     * Returns the price of the flight.
     *
     * @return the price of the flight.
     */
    public float getPrice() {
        return this.price;
    }

    @Override
    public void updatePrice(float price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the object.
     * <p>
     * The output is in the form:
     * <code>Flight[attribute1=Attribute1Value,attribute2=Attribute2Value...]</code>
     */
    public String toString() {
        return String.format("Flight[id=%s, departure=%s, destination=%s, price=%.2f]",
                getFlightId(), getDeparture(), getDestination(), getPrice());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Equality is determined using the id, departure and destination of the Flight.
     *
     * @param obj
     *            the object to compare
     * @return boolean returns true if the passed object is equal
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj instanceof Flight) {
            Flight flight = (Flight) obj;
            return this.getFlightId().equals(flight.getFlightId()) &&
                    this.getDeparture().equals(flight.getDeparture()) &&
                    this.getDestination().equals(flight.getDestination());
        }

        return false;
    }
}
