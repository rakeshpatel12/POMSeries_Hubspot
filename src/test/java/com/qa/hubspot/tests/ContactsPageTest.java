package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	WebDriver driver;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	BasePage basepage;
	ContactsPage contactspage;
	
	@BeforeTest
	public void setUp()
	{
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage=new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactspage = homepage.goToContactPage();
	}
	
	@Test(priority=1)
	public void verifyContactPageTitleTest(){
	 String title = contactspage.getContactsPageTitle();
	 System.out.println("Contact Page Title is - " + title);
	 Assert.assertEquals(title, AppConstants.CONTACT_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsData() {
		Object[][] data =ExcelUtil.getTestData(AppConstants.CONTACT_SHEET_NAME);
		return data;
	}
	
	@Test(priority=2, dataProvider="getContactsData")
	public void createNewContactTest(String email, String firstname, String lastname, String jobtitle) {
		contactspage.createContact(email, firstname, lastname, jobtitle);
	}

	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
