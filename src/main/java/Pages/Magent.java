package Pages;

import Utils.PlaywrightDriver;
import Utils.ScreenshotHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;

public class Magent {
    public Page page = PlaywrightDriver.getPage();




    public Locator loc = page.locator("(//span[text()='Women'])");
    public Locator loc1 = page.locator("(//span[text()='Tops'])").first();
    public Locator loc2 = page.locator("(//span[text()='Jackets'])").first();


    public  void ui() throws InterruptedException {

        Thread.sleep(1000);
        loc.hover();
//        Thread.sleep(1000);
        loc1.hover();
//        Thread.sleep(1000);
        loc2.click();


    }


}
