package Utils;

import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class ScreenshotHelper {
    ThreadLocal<Scenario> sc = new ThreadLocal<>();
   static  Page page = PlaywrightDriver.getPage();
    public static byte[] takeScreenshot() {
        return  page.screenshot();
    }
    public static void getss(Scenario scenario){
        page =PlaywrightDriver.getPage();
        byte[] ScreenShot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshot/screenshot.png")));
        scenario.attach(ScreenShot,"image/png","screenshot");

    }

}
