package es.s2o.selenium.services;

import es.s2o.selenium.builders.FlightSearchBuilder;
import es.s2o.selenium.domain.FlightSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class FlightSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public FlightSearchCriteria setFlightSearchData(List<Map<String, String>> flightSearchDataList)
    {

        Map<String, String> flightSearchData = flightSearchDataList.getFirst();

        return FlightSearchBuilder.aFlightSearch()
                .withSource(flightSearchData.get("source"))
                .withDestination(flightSearchData.get("destination"))
                .withFlightDate(LocalDate.parse(flightSearchData.get("flightDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .withPassengers(Integer.parseInt(flightSearchData.get("passengers")))
                .build();

    }

    public void clean() {
        LOGGER.debug("clean");

        // TODO interact with the API of the application (in this case this app is an example)
    }

}
