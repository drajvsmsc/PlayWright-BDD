package base;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.SelectOption;


import Utils.PlaywrightDriver;

public class BasePage {


    public static Page page;



    public BasePage() {

        page = PlaywrightDriver.getPage();


    }


    public void click(String locatorKey) {

        try {
            page.locator(locatorKey).click();
        } catch (Throwable t) {

            Assert.fail(t.getMessage());
        }
    }


    public void mouseHover(String locatorKey) {

        try {
            page.hover(locatorKey);
        } catch (Throwable t) {

            Assert.fail(t.getMessage());
        }
    }

    public boolean isElementPresent(String locatorKey) {

        try {
            page.waitForSelector(locatorKey, new WaitForSelectorOptions().setTimeout(2000));

            return true;
        } catch (Throwable t) {


            return false;
        }

    }

    public void type(String locatorKey, String value) {
        try {
            page.locator(locatorKey).fill(value);
        } catch (Throwable t) {

            Assert.fail(t.getMessage());
        }
    }


    public void select(String locatorKey, String value) {
        try {
            page.selectOption(locatorKey,new SelectOption().setLabel(value));
        } catch (Throwable t) {

            Assert.fail(t.getMessage());

        }
    }
    public void selectOption(String locatorKey, String value) {   
        try {
            page.selectOption(locatorKey,new SelectOption().setLabel(value));
        } catch (Throwable t) {

            Assert.fail(t.getMessage());
        }

    }
    public void goBack() {
        try {
            page.goBack();
        } catch (Throwable t) {

            Assert.fail(t.getMessage());
        }


    }
}
