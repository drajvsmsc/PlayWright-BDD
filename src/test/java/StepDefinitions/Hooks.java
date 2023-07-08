package StepDefinitions;

import Utils.PlaywrightDriver;
import Utils.ScreenshotHelper;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class Hooks {

    public Page page;

    @Before
    public void setUp() {

        PlaywrightDriver.setupDriver();

    }
//    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        // Take the screenshot.
        byte[] screenshot = ScreenshotHelper.takeScreenshot();

        // Attach the screenshot to the Extent report.
        scenario.attach(screenshot, "image/png", scenario.getName());
    }


    @After
    public void Quit(Scenario scenario){
        if (scenario.isFailed()){
            page =PlaywrightDriver.getPage();
            byte[] ScreenShot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshot/screenshot.png")));
            scenario.attach(ScreenShot,"image/png","screenshot");

        }

        PlaywrightDriver.closeBrowser();
        PlaywrightDriver.quitPlaywright();
    }
}
