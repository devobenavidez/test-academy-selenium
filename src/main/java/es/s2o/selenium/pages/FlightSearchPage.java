package es.s2o.selenium.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightSearchPage extends PageObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id="onetrust-reject-all-handler")
    private WebElementFacade cookiesPopup;
    @FindBy(id = "originInput")
    private WebElementFacade inputOrigin;

    @FindBy(id = "destinationInput")
    private WebElementFacade inputDestination;

    @FindBy(id = "popup-list")
    private WebElementFacade popupList;

    @FindBy(id = "onewayList")
    private WebElementFacade oneWayButton;

    @FindBy(id = "nextButtonCalendar")
    private WebElementFacade nextButtonCalendar;

    @FindBy(css = ".ui-datepicker-group.ui-datepicker-group-last")
    private WebElementFacade lastDatepickerGroup;

    @FindBy(id = "adultsIncrease")
    private WebElementFacade adultsIncreaseButton;

    @FindBy(id = "outboundDate")
    private WebElementFacade outboundDateInput;

    @FindBy(id = "btnSubmitHomeSearcher")
    private WebElementFacade submitButton;

    public void closeCookiesPopup ()
    {
        cookiesPopup.waitUntilVisible().click();
    }
    public void enterOrigin(String origin) {
        inputOrigin.type(origin);
        popupList.waitUntilVisible().then().find(By.cssSelector("vy-airports-li:first-child li")).click();
    }

    public void enterDestination(String destination) {
        inputDestination.type(destination);
        popupList.waitUntilVisible().then().findBy("vy-airports-li li.liStation").click();
    }

    public void selectOneWayTrip() {
        oneWayButton.click();
    }

    public void navigateToCorrectMonth() {
        nextButtonCalendar.click();
        nextButtonCalendar.click();
    }

    public void selectFirstWeekendDate() {
        lastDatepickerGroup.findBy(By.id("calendarDaysTable")).then()
                .findBy(By.cssSelector("tbody tr:first-child .ui-datepicker-week-end:not(.ui-datepicker-days-cell-over)")).click();
    }

    //Esta era una opción que quería usar para cambiar el valor del input pero es de solo lectura y no quise modificar
    //el comportamiento original del input
    public void setOutBoundDate(LocalDate outBoundDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = outBoundDate.format(formatter);

        outboundDateInput.type(formattedDate);

    }

    public void increaseAdultsPassengersIfNeeded(int passengers) {
        int clicksRequired = passengers - 1; // Since 1 adult is selected by default
        for (int i = 0; i < clicksRequired; i++) {
            adultsIncreaseButton.click();
        }
    }

    public void sendRequest(){
        submitButton.click();
    }

    public List<WebElementFacade> assertFlightCardsAreDisplayed() {
        // Espera explícitamente a que el contenedor de tarjetas de vuelo sea visible, si es necesario
        // waitFor("#flightCardsContainer").waitUntilVisible();

        List<WebElementFacade> flightCards = findAll(By.cssSelector("#flightCardsContainer [data-js-id='flightCard']"));
        return flightCards;
    }
}
