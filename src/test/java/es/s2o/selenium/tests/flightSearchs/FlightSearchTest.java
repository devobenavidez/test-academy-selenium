package es.s2o.selenium.tests.flightSearchs;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/flightSearchs",
        glue = "es.s2o.selenium.stepsdefs.flightSearchs")
public class FlightSearchTest {


}
