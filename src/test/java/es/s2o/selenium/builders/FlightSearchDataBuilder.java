package es.s2o.selenium.builders;

import java.time.LocalDate;

public class FlightSearchDataBuilder {
    public static  FlightSearchBuilder defaultClient() {
        return FlightSearchBuilder
                .aFlightSearch()
                .withSource("Alicante")
                .withDestination("Madrid")
                .withFlightDate(LocalDate.of(2024, 6, 1))
                .withPassengers(1);
    }
}
