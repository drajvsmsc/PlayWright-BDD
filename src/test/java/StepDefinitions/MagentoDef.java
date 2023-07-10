package StepDefinitions;

import Pages.MMTpage;
import Pages.Magent;
import Utils.PlaywrightDriver;

import base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

import static StepDefinitions.DefaultStepDefinition.currentIterationMap;


public class MagentoDef {
    static Magent mag = new Magent();
    MMTpage mmt = new MMTpage();



    @Given("User navigates to Website")
    public void userNavigatesToWebsite() {
        PlaywrightDriver.openPage(PlaywrightDriver.config.getProperty("testsiteurl"));

    }



    @Then("use selects Jackets")
    public void useSelectsJackets() throws InterruptedException, IOException {
        mmt.ui();
        Thread.sleep(2000);
       // Assert.assertEquals(mag.loc2.innerText(),"Jackets");


    }

   
}
