package com.qa.pc.search;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.qa.pc.base.BasePage;

public class SearchGoogle extends BasePage{

	private static final Logger LOGGER = Logger.getLogger(SearchGoogle.class);
	private static final String PAGE = SearchGoogle.class.getName();
	
	public SearchGoogle(WebDriver driver) throws Exception {
		super(driver);
	}
	@Override
	public boolean wait4PageToLoad() {
		boolean pageLoaded = false;
		try {
			elementsPresent(ElementsInPage.SEARCH.getLocator());
			pageLoaded = true;
		}catch(NoSuchElementException ex) {
			LOGGER.error(ex + "\n" + PAGE);
		}
		return pageLoaded;
	}
	
	private void typeIntoSearchBox(String searchString) {
		WebElement element = elementClickable(ElementsInPage.SEARCH.getLocator());
		element.clear();
		element.sendKeys(searchString);
	}
	
	private void clickSearchBox() {
		elementClickable(ElementsInPage.SEARCH_BUTTON.getLocator()).click();
	}
	
	public void goRun(String searchString) {
		typeIntoSearchBox(searchString);
		clickSearchBox();
	}
	
	private enum ElementsInPage {
		SEARCH(By.xpath("//input[@name='q']")),
		SEARCH_BUTTON(By.xpath("//input[@value='Google Search']"));
		
		private final By by;
		private ElementsInPage(By by) {
			this.by = by;
		}
		
		public By getLocator() {
			return (by);
		}
	}

}
