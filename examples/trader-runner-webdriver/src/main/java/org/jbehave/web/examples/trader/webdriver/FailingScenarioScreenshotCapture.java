package org.jbehave.web.examples.trader.webdriver;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.web.webdriver.PerScenarioWebDriverSteps;
import org.jbehave.web.webdriver.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class FailingScenarioScreenshotCapture extends PerScenarioWebDriverSteps {

    public FailingScenarioScreenshotCapture(WebDriverFactory driverFactory) {
        super(driverFactory);
    }

    @Override
    @AfterScenario(uponOutcome = Outcome.FAILURE)
    public void afterScenario() throws Exception {

        String home = System.getenv("HOME");
        // home+"/failedScenario.png"
        WebDriver webDriver = driverFactory.get();
        if (webDriver instanceof TakesScreenshot) {
            File sShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot at: " + sShot.getAbsolutePath());
        } else {
            System.out.println("Screenshot cannot be taken: driver " + webDriver.getClass().getName() + " does not support Screenshotting");
        }
        super.afterScenario();
    }

}