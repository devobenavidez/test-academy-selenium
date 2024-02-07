package es.s2o.selenium.domain;

public class FlightSearchCriteriaDTO {

    private String source;
    private String destination;
    private String flightDate;
    private int passengers;


    public FlightSearchCriteriaDTO() {

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "FlightSearchCriteriaDTO{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", passengers=" + passengers + '\'' +
                '}';
    }
}
