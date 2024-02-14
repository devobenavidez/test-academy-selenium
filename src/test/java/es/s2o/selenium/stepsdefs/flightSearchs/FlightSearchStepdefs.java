package es.s2o.selenium.stepsdefs.flightSearchs;

import es.s2o.selenium.domain.FlightSearchCriteria;
import es.s2o.selenium.pages.FlightSearchPage;
import es.s2o.selenium.services.FlightSearchService;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.serenitybdd.core.Serenity.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sacrists on 26.02.17.
 */
public class FlightSearchStepdefs {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String WEB_SEARCH_ROOT = "WEB_SEARCH_ROOT";
    private static final String HOME = "reservationList.html";

    @Steps
    private FlightSearchService flightService;

    private FlightSearchPage flightSearchPage;

    @Before
    public void beforeScenario() {
        LOGGER.debug("beforeScenario starts");
    }

    @After
    public void afterScenario() {
        LOGGER.debug("afterScenario starts");
        flightService.clean();
    }

    @Given("^I'm in the vueling page$")
    public void iMInTheVuelingPage() throws Throwable {
        LOGGER.debug("iMInTheVuelingPage starts");
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = variables.getProperty("WEB_SEARCH_ROOT");
        flightSearchPage.openAt(baseUrl);
        flightSearchPage.closeCookiesPopup();
    }

    @When("^I verify the existing flights:$")
    public void iVerifyTheExistingFlights(List<Map<String, String>> flightSearchDataList) {
        LOGGER.debug("iVerifyTheExistingFlights starts");

        FlightSearchCriteria flightSearchCriteria = flightService.setFlightSearchData(flightSearchDataList);

        flightSearchPage.enterOrigin(flightSearchCriteria.getSource());
        flightSearchPage.enterDestination(flightSearchCriteria.getDestination());
        flightSearchPage.selectOneWayTrip();
        flightSearchPage.navigateToCorrectMonth();
        flightSearchPage.selectFirstWeekendDate();
        //Aquí tengo comentado el método que setea la fecha del vuelo
        //es la otra forma de hacer el test, pero dejé en uso la forma que se mueve en el calendar
        //el problema es que el input es readonly y en el método explico lo que hago
        //flightSearchPage.SetDate(flightSearchCriteria.getFlightDate());
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
