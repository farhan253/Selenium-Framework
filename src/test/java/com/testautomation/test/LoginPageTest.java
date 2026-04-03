package com.testautomation.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testautomation.utilities.DataProviders;
import com.testautomation.utilities.ExtentManager;
import com.testautomation.base.BaseClass;
import com.testautomation.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginPage;
	@BeforeMethod
	public void setPages()
	{
		loginPage=new LoginPage(getDriver());
	}
	@Test(dataProvider="validLoginData", dataProviderClass = DataProviders.class)
	public void loginFunctionality(String username, String password)
	{
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.loginMethod(username, password);
		ExtentManager.logStep("Verifying Admin tab is visible or not");
		loginPage.verifyAdminTab();
		loginPage.logout();
		ExtentManager.logStep("Logged out Successfully!");
		staticWait(2);
	}
	@Test(dataProvider="inValidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginFunctionality(String username, String password)
	{

		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.loginMethod(username, password);
		loginPage.verifyErrorMessage();
		ExtentManager.logStep("Error message is displayed");
		
	}

}
