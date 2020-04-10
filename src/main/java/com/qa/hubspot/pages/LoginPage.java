package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementActions elementactions;
	
	// 1. By locators - Page Objects

	By username = By.id("username");
	By password = By.id("password");
	By login = By.id("loginBtn");
	By signup = By.linkText("Sign up");
	By errMsg = By.cssSelector("h5.private-alert__title");
	
	//2. Constructor of page class:
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementactions = new ElementActions(this.driver);
		
	}
	
	//3. Page Actions / methods
	@Step("Getting page title")
	public String  getPageTitle()
	{
		return elementactions.doGetPageTitle(AppConstants.LOGIN_PAGE_TITLE);
	}
	@Step("Verifying signup link")
	public boolean verifySignUPLink()
	{
		elementactions.waitForElementPresent(signup);
		return elementactions.doIsDisplayed(signup);
	}
	
	@Step("Username is : {0} and password is: {1}")
	public HomePage doLogin(String email, String pwd)
	{
		elementactions.waitForElementPresent(username);
		elementactions.doSendKeys(username, email);
		elementactions.doSendKeys(password, pwd);
		elementactions.doClick(login);
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage()
	{
		return elementactions.doIsDisplayed(errMsg);
	}
	
}
