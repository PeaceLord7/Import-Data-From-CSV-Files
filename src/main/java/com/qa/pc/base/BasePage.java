package com.qa.pc.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage{
	
		/* === FOR DEBUGGING === */
	private String classname;
	
		/* === GLOBAL VARIABLES === */
	protected WebDriver driver;
	private WebDriverWait waitDriver;

	public BasePage(WebDriver driver) throws Exception{
		this.driver = driver;
		this.waitDriver = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	//GETTER AND SETTERS
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public WebDriverWait waitDriver() {
		return this.waitDriver;
	}
	
	public String getClassName() {
		return classname;
	}
	
	//SERVICES CREATED TO GET THE ELEMENTS PRESENT IN THE PAGE
	
	public WebElement elementPresent(By locator) {
		return waitDriver.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public List<WebElement> elementsPresent(By locator){
		return waitDriver.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public WebElement elementVisible(By locator) {
		return waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement elementClickable(By locator) {
		return waitDriver.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean elementStale(WebElement element) {
		return waitDriver.until(ExpectedConditions.stalenessOf(element));
	}
	
	public boolean elementExist(By locator) {
		try {
			driver.findElement(locator);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	public abstract boolean wait4PageToLoad();
	
	protected void wait4Frame(String frameName) {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
}
