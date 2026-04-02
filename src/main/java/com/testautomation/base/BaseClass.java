package com.testautomation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.testautomation.base.BaseClass;
import com.testautomation.utilities.LoggerManager;
import com.testautomation.actiondriver.ActionDriver;

public class BaseClass {
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	private static ThreadLocal<ActionDriver> actionDriver=new ThreadLocal<ActionDriver>();
	public static final Logger logger = LoggerManager.getLogger(BaseClass.class);
	public static  Properties prop;
	
	
	//load config file
	@BeforeSuite
	public static void loadConfig() throws IOException
	{
		
			prop=new Properties();
			FileInputStream fis=new FileInputStream("src/main/resources/config.properties");
			prop.load(fis);
		
		
		
	}
	@BeforeMethod
	public void setup()
	{
		System.out.println("Setting up webdriver");
		launchBrowser();
		browserConfiguration();
		staticWait(2);
		
		// Initialize ActionDriver for the current Thread
				actionDriver.set(new ActionDriver(getDriver()));
				logger.info("ActionDriver initlialized for thread: " + Thread.currentThread().getId());
		
	}
	//get driver method for driver initialization
	public static WebDriver getDriver()
	{
		if(driver.get()==null)
		{
			System.out.println("Webdriver is not initialized");
			throw new IllegalArgumentException("Webdriver is not initialized");
		}
		return driver.get();
		
	}
	
	public static ActionDriver getActionDriver()
	{
		if(actionDriver.get()==null)
		{
			System.out.println("Actiondriver is not initialized");
			throw new IllegalArgumentException("Actiondriver is not initialized");
		}
		return actionDriver.get();
	}
	
	private void launchBrowser() {
		
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver.set(new ChromeDriver());
			logger.info("chrome driver is initialized");
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver.set(new FirefoxDriver());
			logger.info("firefox driver is initialized");
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver.set(new EdgeDriver());
			logger.info("Edge driver is initialized");
		}
		else
		{
			throw new IllegalArgumentException("Browser is not initialized"+browser);
			
		}
	}
	
	private void browserConfiguration()
	{
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
	}
	
	
	@AfterMethod
	public static void tearDown()
	{
		if(getDriver()!=null)
		{
			try {
				getDriver().quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			logger.info("Browser is not closed");
		}
	}
	// Static wait for pause
		public void staticWait(int seconds) {
			LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
		}

}
