package Pages;

import Utils.PlaywrightDriver;
import base.BasePage;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import extentlisteners.ExtentListeners;
import extentlisteners.ExtentManager;
import io.cucumber.java.eo.Se;

import java.io.IOException;

public class MMTpage extends BasePage {
    Page page = PlaywrightDriver.getPage();

    public String ClosePopPAge ="//*[@class='close']";
    public String Pageon ="//img[@src='https://promos.makemytrip.com/notification/xhdpi/Common-Roadblock-Set2-TickThatBucketList-4Jul.jpeg']";
    public String Flighticon ="//*[text()='Flights']";
    public String FromCity ="//label[@for='fromCity']";
    public String EnterFromCity ="//input[@placeholder='From']";
    public String SelectCity ="//ul[@role='listbox']/li";
    public String TOCity="//label[@for='toCity']";
    public String EnterToCity ="//input[@placeholder='To']";
    public String NextMonth ="//span[@aria-label='Next Month']";





    public void ui() throws InterruptedException, IOException {
        Thread.sleep(1000);
        page.click(FromCity);
        clickElement(EnterFromCity,"EnterFromCity");
        page.fill(EnterFromCity,"BOM");
        page.locator(SelectCity).first().click();
        Thread.sleep(1000);
        page.click(TOCity);
        page.click(EnterToCity);
        page.fill(EnterToCity,"VTZ");
        Thread.sleep(1000);
        page.locator(SelectCity).first().click();
        Thread.sleep(1000);



    }
}
