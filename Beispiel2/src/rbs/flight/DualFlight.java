package rbs.flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides the means to specify a Dual-Flight that is a collection of flights.
 * Dual-Flights have, besides an id, a list of {@link Flight}s of length two.
 * The price of the Dual-Flight is specified by the list of flights contained in the Dual-Flight.
 * <p>
 * Attributes of the class:
 * <ul>
 * <li>id of the flight
 * <li>list of {@link IFlight}s
 * </ul>
 *
 */
public class DualFlight extends Flight {
    private List<IFlight> flights = new ArrayList<>();

    /**
     * Constructor that takes two arguments (id and list of flights) and initializes the
     * dual flight.
     *
     * If the passed id is NULL the flight id will be initialized with an empty
     * string.
     *
     * If the passed list of flights has length greater than two, two DualFlights containing
     * the whole list (recursive split) are created. If the passed list of flights is null,
     * destination and departure are both set to "".
     *
     * @param id
     *            the id of the flight
     * @param flights
     *            the list of contained flights
     */

    public DualFlight(String id, List<IFlight> flights) {
        super(id);
        if (flights == null)
            return;

        if (flights.size() > 0){
            super.departure = flights.get(0).getDeparture();
            super.destination = flights.get(flights.size()-1).getDestination();
        }

        if (flights.size() >= 1 && flights.size() <= 2) {
            for (IFlight f : flights)
                this.flights.add((IFlight) f.deepCopy());
        } else if (flights.size() > 2) {
            this.flights.add(new DualFlight(id+"L", flights.subList(0, flights.size()/2)));
            this.flights.add(new DualFlight(id+"R", flights.subList(flights.size()/2, flights.size())));
        }
    }

    /**
     * Constructor that takes two arguments (id and a flights) and initializes the
     * dual flight.
     *
     * If the passed id is NULL the flight id will be initialized with an empty
     * string.
     *
     *
     * @param id
     *            the id of the flight
     * @param flight
     *            the contained flight
     */

    public DualFlight(String id, IFlight flight) {
        this(id, Arrays.asList(flight));
    }

    /**
     * Calculates the total price of the flight.
     * <p>
     * The price of a {@link DualFlight} is calculated as the sum of prices of all flights
     * in the flight list.
     * </p>
     *
     * @return the price of the {@link DualFlight}
     */
    public float getPrice() {
        float ans = 0.0f;
        for (IFlight f : this.flights) {
            ans += f.getPrice();
        }
        return ans;
    }

    /**
     * Returns a string representation of the object.
     * <p>
     * The output is in the form:
     * <code>DualFlight:
     *      Flight[attribute1=Attribute1Value,attribute2=Attribute2Value...]\n
     *      ...</code>
     */
    public String toString() {
        String ans = "DualFlight: " + this.getFlightId() + " Departure: " + this.getDeparture() + " Destination: " +
                this.getDestination() + "\n";
        for (IFlight f : flights) {
            ans += "\t" + f.toString();
        }
        return ans;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>NOTE:</b> Keep in mind that for all referenced {@link Flight}s in this
     * {@link DualFlight}, e.g. the elements of the private {@link List}, a
     * copy has to be created as well.
     */
    @Override
    public DualFlight deepCopy() {
        List<IFlight> flightsCopy = new ArrayList<>();
        for (IFlight f : this.flights)
            flightsCopy.add((IFlight) f.deepCopy());
        return new DualFlight(this.getFlightId(), flightsCopy);
    }

    /**
     * Returns the list of flights.
     *
     * @return the list of flights
     */
    public List<IFlight> getFlights() {
        return flights;
    }
}