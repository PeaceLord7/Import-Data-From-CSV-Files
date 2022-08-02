package com.qa.pc.driver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.naming.NameNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.constants.Constants;

public abstract class Driver{
	
	protected WebDriver driver = null;
	private Map<String,String> scenario;
	
	public Driver(Map<String,String> scenario) {
		this.scenario = scenario;
	}
	
	public Map<String,String> getscenario(){
		return scenario;
	}
	
	String driverPath = "D:\\Selenium\\chromedriver.exe";
	@BeforeMethod(alwaysRun = true)
	public void init() throws NameNotFoundException, IOException{
			typeOfWebDriver();
			driver.manage().window().maximize();
			String baseURL = null;
			baseURL = "https://www.google.com/";
			driver.navigate().to(baseURL);
		}
	
	@AfterMethod
	public void teardown() {
		if (driver != null) {
		driver.close();
		}
	}
	
	public void typeOfWebDriver() throws MalformedURLException{
		driver = getMyDriver();
	}
	
	public WebDriver getMyDriver() {
		switch(BrowserTypes.valueOf(getscenario().get(Constants.BROWSER_NAME))) {
		case INTERNET_EXPLORER:
			return getBraveDriver();
		case CHROME:
			return getChromeDriver();
		case BRAVE:
			return getBraveDriver();
		default:
			return getChromeDriver();
		}
	}
	
	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-extensions");
		return driver = new ChromeDriver(options);
	}
	
	public WebDriver getBraveDriver() {
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-extensions");
		return driver = new ChromeDriver(options);
	}
	
	enum BrowserTypes{
		INTERNET_EXPLORER("internet explorer"),
		CHROME("chrome"),
		FIREFOX("firefox"),
		SAFARI("safari"),
		BRAVE("brave");
		
		String browserName;
		
		BrowserTypes(String browserName){
			this.browserName = browserName;
		}
		
		public String getBrowserType() {
			return browserName;
		}
	}
}
