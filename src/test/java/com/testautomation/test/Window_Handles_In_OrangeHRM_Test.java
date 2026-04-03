package com.testautomation.test;

import java.util.Set;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testautomation.base.BaseClass;
import com.testautomation.pages.LoginPage;
import com.testautomation.pages.Verify_Window_Handles_In_OrangeHRM;
import com.testautomation.utilities.DataProviders;

public class Window_Handles_In_OrangeHRM_Test extends BaseClass {
	
	private LoginPage login;
	private Verify_Window_Handles_In_OrangeHRM windows;
	@BeforeMethod
	public void setPages()
	{
		this.login=new LoginPage(getDriver());
		this.windows=new Verify_Window_Handles_In_OrangeHRM(getDriver());
		
	}
	
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void windowsTest(String username, String password)
	{
		login.loginMethod(username, password);
		staticWait(3);
		windows.getWindows();
		String mainWindow=getDriver().getWindowHandle();
		Set<String> allWindows=getDriver().getWindowHandles();
		for(String handles:allWindows)
		{
			if(!handles.equals(mainWindow))
			{
				getDriver().switchTo().window(handles);
			}
		}
		
		getDriver().switchTo().window(mainWindow);
		windows.mainWindow();
		
			}

}
