package com.qa.hubspot.tests;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;

public class HomePageTest {

Properties prop;
LoginPage loginpage;
BasePage basepage;
HomePage homepage;
WebDriver driver;

@BeforeTest
public void setUp()
{
	
	basepage = new BasePage();
	prop = basepage.init_prop();
	driver =basepage.init_driver(prop);
	loginpage=new LoginPage(driver);
	//homepage = new HomePage(driver);
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
}
@Test(priority=2)
public void verifyHomePageTitle()
{
	String title = homepage.getHomePageTitle();
	System.out.println("Home Page Title is - " + title);
	Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);

}

@Test(priority=1)
public void verifyHomePageHeader()
{
	String header = homepage.isHomePageHeaderExist();
	System.out.println("HomePage Header is - " + header);
	Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
}

@Test(priority=3)
public void verifyLoggedInUserTest()
{
	String loggedUserName = homepage.isUserLoggedIn();
	System.out.println("Logged in user name is - " + loggedUserName);
	Assert.assertEquals(loggedUserName, prop.getProperty("accountname"));
}

@AfterTest
public void tearDown()
{	
driver.quit();
}

}
