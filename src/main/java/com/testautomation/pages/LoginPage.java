package com.testautomation.pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.testautomation.actiondriver.ActionDriver;
import com.testautomation.base.BaseClass;
import com.testautomation.utilities.LoggerManager;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	
	//Define locators using By class
	private By userNameField=By.xpath("//input[@name='username']");
	private By passwordField=By.xpath("//input[@placeholder='Password']");
	private By loginButton=By.xpath("//button[text()=' Login ']");
	private By verifyAdminText=By.xpath("//ul[@class='oxd-main-menu']/li/a[@href='/web/index.php/admin/viewAdminModule']");
	private By clickUsername=By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
	private By clickLogout=By.xpath("//a[contains(text(),'Logout')]");
	
	public LoginPage(WebDriver driver)
	{
		this.actionDriver=BaseClass.getActionDriver();
		
	}
	
	public void  loginMethod(String username,String password)
	{
		actionDriver.enterText(userNameField, username);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
		
	}
	public void verifyAdminTab()
	{
		boolean AdminText=actionDriver.isDisplayed(verifyAdminText);
		Assert.assertTrue(AdminText, "Admin tab is visible");
		
	}
	public void logout()
	{
		actionDriver.click(clickUsername);
		actionDriver.click(clickLogout);
	}
	

}
