package com.testautomation.actiondriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testautomation.base.BaseClass;



public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;
	
	public ActionDriver(WebDriver driver)
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("webdriver is initialized");
	}
	
	//ExplicitWait - Visibility of Element
	public void visibilityOfElement(By by)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("element is visible");
			
		} catch (Exception e) {
			logger.error("Element is not visible");
		}
	}
	
	public void waitForElementToClick(By by)
	{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			
		} catch (Exception e) {
			logger.error("Element is not clickable");
		}
	}
	
	//JSE
	
	public void clickJS(By by)
	{
		waitForElementToClick(by);
		try {
			WebElement element=driver.findElement(by);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			logger.error("Element is not clickable");
		}
	}
	
	public void scrollToElement(By by)
	{
		visibilityOfElement(by);
		WebElement element=driver.findElement(by);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	//SelectDropdowns
	
	public void selectByVisibleText(By by, String text)
	{
		try {
			Select select=new Select(driver.findElement(by));
			select.selectByVisibleText(text);
		} catch (Exception e) {
			logger.error("Text is not selected");
		}
	}
	
	public void selectByIndex(By by, int value)
	{
		try {
			new Select(driver.findElement(by)).selectByIndex(value);
		} catch (Exception e) {
			logger.error("Index is not selected");
		}
	}
	
	public void selectByValue(By by,String value)
	{
		try {
			new Select(driver.findElement(by)).selectByValue(value);
		} catch (Exception e) {
			logger.error("value is not selected");
		}
	}
	
	public void getAllOptionFromDropdown(By by)
	{
		List<String> allOptions=new ArrayList<String>();
		
		try {
			WebElement dropdownElement = driver.findElement(by);
			Select select = new Select(dropdownElement);
			for(WebElement options:select.getOptions())
			{
				allOptions.add(options.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Method to click an element
		public void click(By by) {
			
			try {
				
				visibilityOfElement(by);
				driver.findElement(by).click();
				
			} catch (Exception e) {
				
				logger.error("Unable to click element:" + e.getMessage());
				
			}
		}

		// Method to enter text into an input field --Avoid Code Duplication - fix the
		// multiple calling method
		public void enterText(By by, String value) {
			try {
				visibilityOfElement(by);
				WebElement element = driver.findElement(by);
				element.clear();
				element.sendKeys(value);
				
			} catch (Exception e) {
				logger.error("Unable to enter the value");
			}
		}
		
		//isDipslayed
		public boolean isDisplayed(By by)
		{
			try {
				visibilityOfElement(by);
				return driver.findElement(by).isDisplayed();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
		
		//compare text
		public boolean compareText(By by, String expectedText)
		{
			try {
				String actualText=driver.findElement(by).getText();
				if(expectedText.equals(actualText))
				{
					System.out.println("Text are equal");
				}
				else
				{
					System.out.println("Text are not equal");
				}
				return true;
			} catch (Exception e) {
				logger.error("Unable to compare text");
				return false;
			}
		}
		
		public void getWindowHandles()
		{
			//main window
			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows=driver.getWindowHandles();
			for(String handle:allwindows)
			{
				if(!handle.equals(mainWindow))
				{
					driver.switchTo().window(handle);
				}
			}
			System.out.println("new window title"+driver.getTitle());
			
		}
		
		public void getWindowHandles1()
		{
			//main window
			String mainWindow=driver.getWindowHandle();
			Set<String> allwindows=driver.getWindowHandles();
			for(String handle:allwindows)
			{
				if(!handle.equals(mainWindow))
				{
					driver.switchTo().window(handle);
				}
			}
			System.out.println("new window title"+driver.getTitle());
			
		}
	
	
	
	
	

}