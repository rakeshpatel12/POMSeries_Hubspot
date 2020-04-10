package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;

public class HomePage extends BasePage{
	
WebDriver driver;	
ElementActions elementactions;

//1. By locators	
By header = By.cssSelector("h1.private-page__title");
By accname = By.cssSelector("span.account-name");

By parentContact = By.id("nav-primary-contacts-branch");
By secondaryContact  = By.id("nav-secondary-contacts");


//2.Create constructor

public HomePage(WebDriver driver)
{
	this.driver=driver;
	elementactions = new ElementActions(this.driver);
}
	
public String getHomePageTitle()
{
	return elementactions.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
}

public String isHomePageHeaderExist()
{
	elementactions.waitForElementPresent(header);
 if (elementactions.doIsDisplayed(header)) {
	 return elementactions.doGetText(header);
 } else
	 return null;

}

public String isUserLoggedIn()
{
	elementactions.waitForElementPresent(accname);
	if(elementactions.doIsDisplayed(accname))
	{
		return elementactions.doGetText(accname);
	}else
		return null;
}

public ContactsPage goToContactPage() {
	clickOnContacts();
	return new ContactsPage(driver);
}

public void clickOnContacts() {
	elementactions.waitForElementPresent(parentContact);
	elementactions.doClick(parentContact);
	
	elementactions.waitForElementPresent(secondaryContact);
	elementactions.doClick(secondaryContact);
}


}
