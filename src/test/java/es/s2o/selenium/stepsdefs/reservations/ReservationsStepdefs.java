package es.s2o.selenium.stepsdefs.reservations;

import es.s2o.selenium.builders.FlightSearchBuilder;
import es.s2o.selenium.domain.FlightSearchCriteria;
import es.s2o.selenium.domain.ReservationDTO;
import es.s2o.selenium.pages.FlightSearchPage;
import es.s2o.selenium.pages.ReservationListPage;
import es.s2o.selenium.pages.ReservationPage;
import es.s2o.selenium.services.ReservationService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.serenitybdd.core.Serenity.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sacrists on 26.02.17.
 */
public class ReservationsStepdefs {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String WEB_ROOT = "WEB_ROOT";
    private static final String REDIRECT_URL = "REDIRECT_URL";
    private static final String HOME = "reservationList.html";

    @Steps
    private ReservationService reservationService;

    private ReservationListPage reservationListPage;
    private ReservationPage reservationPage;

    private List<ReservationDTO> reservations;

    private FlightSearchPage flightSearchPage;

    @Before
    public void beforeScenario() {
        LOGGER.debug("beforeScenario starts");
        reservationService.addReservations(2);
    }

    @After
    public void afterScenario() {
        LOGGER.debug("afterScenario starts");
        reservationService.clean();
    }

    @Given("^I'm in the vueling page$")
    public void iMInTheVuelingPage() throws Throwable {
        LOGGER.debug("iMInTheVuelingPage starts");
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = variables.getProperty("WEB_ROOT");
        reservationPage.openAt(baseUrl);
        reservationPage.find(By.id("onetrust-reject-all-handler")).waitUntilVisible().click();

    }

    @When("^I verify the existing flights:$")
    public void iVerifyTheExistingFlights(List<Map<String, String>> flightSearchDataList) {
        LOGGER.debug("iVerifyTheExistingFlights starts");

        Map<String, String> flightSearchData = flightSearchDataList.get(0); // Tomar solo el primer elemento
        FlightSearchCriteria flightSearchCriteria = FlightSearchBuilder.aFlightSearch()
                .withSource(flightSearchData.get("source"))
                .withDestination(flightSearchData.get("destination"))
                .withFlightDate(LocalDate.parse(flightSearchData.get("flightDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .withPassengers(Integer.parseInt(flightSearchData.get("passengers")))
                .build();

        flightSearchPage.enterOrigin(flightSearchCriteria.getSource());
        flightSearchPage.enterDestination(flightSearchCriteria.getDestination());
        flightSearchPage.selectOneWayTrip();
        flightSearchPage.navigateToCorrectMonth();
        flightSearchPage.selectFirstWeekendDate();
        flightSearchPage.increaseAdultsPassengersIfNeeded(flightSearchCriteria.getPassengers());
        flightSearchPage.sendRequest();
    }

    @Then("^I should see flights available that match my specified origin, destination, and travel date$")
    public void iShouldSeeFlights() {
        LOGGER.debug("iShouldSeeFlights starts");

        String originalWindowHandle = getDriver().getWindowHandle();
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        String newTabHandle = allWindowHandles.stream()
                .filter(handle -> !handle.equals(originalWindowHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New tab not found"));
        getDriver().switchTo().window(newTabHandle);
        List<WebElementFacade> flightCards = flightSearchPage.assertFlightCardsAreDisplayed();
        assertThat(flightCards).as("Flight cards should be shown").isNotEmpty();
    }


}
