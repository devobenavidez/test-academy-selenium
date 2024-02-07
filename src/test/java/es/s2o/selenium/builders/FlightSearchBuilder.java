package es.s2o.selenium.builders;

import es.s2o.selenium.domain.FlightSearchCriteria;
import java.time.LocalDate;

public final class FlightSearchBuilder {
    private String source;
    private String destination;
    private LocalDate flightDate;
    private int passengers;

    private FlightSearchBuilder() {
    }

    public static FlightSearchBuilder aFlightSearch() {
        return new FlightSearchBuilder();
    }

    public FlightSearchBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    public FlightSearchBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public FlightSearchBuilder withFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
        return this;
    }

    public FlightSearchBuilder withPassengers(int passengers) {
        this.passengers = passengers;
        return this;
    }

    public FlightSearchCriteria build() {
        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        flightSearchCriteria.setSource(source);
        flightSearchCriteria.setDestination(destination);
        flightSearchCriteria.setFlightDate(flightDate);
        flightSearchCriteria.setPassengers(passengers);
        return flightSearchCriteria;
    }
}
