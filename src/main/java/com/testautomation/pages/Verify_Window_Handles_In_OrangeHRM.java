package com.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.testautomation.actiondriver.ActionDriver;
import com.testautomation.base.BaseClass;

public class Verify_Window_Handles_In_OrangeHRM {
	
	private ActionDriver actionDriver;
	
	//Locators
	
	private By ClickOnOrangeHRMLink=By.xpath("//a[contains(text(),'OrangeHRM, Inc')]");
	private By clickOnDennyButton =By.xpath("//a[contains(text(),'OrangeHRM, Inc')]");
	private By clickUsername=By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
	private By clickLogout=By.xpath("//a[contains(text(),'Logout')]");
	
	public Verify_Window_Handles_In_OrangeHRM(WebDriver driver)
	{
		this.actionDriver=BaseClass.getActionDriver();
	}
	
	public void getWindows()
	{
		actionDriver.scrollToElement(ClickOnOrangeHRMLink);
		actionDriver.click(ClickOnOrangeHRMLink);
		
		
	}
	
	public void mainWindow()
	{
		actionDriver.click(clickUsername);
		actionDriver.click(clickLogout);
	}
	

}
