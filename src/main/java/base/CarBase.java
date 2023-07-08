package base;

import Utils.PlaywrightDriver;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Utils.PlaywrightDriver;

public class CarBase {

	public Page page;

	public CarBase(Page page) {

		this.page = page;
	}

	public String getCarTitle() {

		return page.locator(PlaywrightDriver.OR.getProperty("carTitle_XPATH")).innerText();

	}
	
	public void getCarBrandNameAndPrices() {
		
		
		Locator carNames = page.locator(PlaywrightDriver.OR.getProperty("carNames_XPATH"));
		Locator carPrices = page.locator(PlaywrightDriver.OR.getProperty("carPrices_XPATH"));
		
		
		for(int i=0; i<carPrices.count(); i++) {
			
			System.out.println(carNames.nth(i).innerText()+"----Car price is : "+carPrices.nth(i).innerText());
			
		}
		
	}

}
