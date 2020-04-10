package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("EPIC - 101 : define login feature for hubspot application")
@Feature("User 101 : Create feature for login with signup, title and login")
public class LoginPageTest {
	
	BasePage basepage;
	HomePage homePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginpage;

	
	@BeforeTest
	//@Parameters({"browser"})
	public void setUp() {
		
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		}
	
	@Test(priority=2)
	@Description("Verify Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest()
	{
		String title = loginpage.getPageTitle();
		System.out.println("Page title is -" + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=1)
	@Description("Verify SignUP Link Title Test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLink()
	{
		
	Assert.assertTrue(loginpage.verifySignUPLink());
	}
	
	@Test(priority=3)
	@Description("Verify App Login Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginTest()
	{
		homePage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String loggedUserName = homePage.isUserLoggedIn();
		System.out.println(prop);
		System.out.println("Logged in user name is - " + loggedUserName);
		Assert.assertEquals(loggedUserName, prop.getProperty("accountname"));
	}
	
	@DataProvider
	public Object[][] getInvalidLoginData()
	{
		Object data[][] = {
				{"test@yahoo.com" ,"testyahoo1"},
//				{"test@outlook.com", "     " },
//				{" " , "password1234"},
				
		};
		return data;
	}
	
	@Test(priority =4 , dataProvider = "getInvalidLoginData" , enabled = false)
	public void login_InvalidTestCases(String emailId , String pwd)
	{
		loginpage.doLogin(emailId, pwd);
		Assert.assertTrue(loginpage.checkLoginErrorMessage());
	}

	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
