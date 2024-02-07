package es.s2o.selenium.domain;

import java.time.LocalDate;

public class FlightSearchCriteria {
    private String source;
    private String destination;
    private LocalDate flightDate;
    private int passengers;


    // Constructor vacío
    public FlightSearchCriteria() {
        // Este constructor está vacío y es útil si necesitas inicializar la clase sin establecer campos inmediatamente
    }

    // Constructor con parámetros
    public FlightSearchCriteria(String source, String destination, LocalDate flightDate, int passengers) {
        this.source = source;
        this.destination = destination;
        this.flightDate = flightDate;
        this.passengers = passengers;
    }

    // Getters y setters
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

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
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
        return "FlightSearchCriteria{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", flightDate=" + flightDate +
                ", passengers=" + passengers +
                '}';
    }
}