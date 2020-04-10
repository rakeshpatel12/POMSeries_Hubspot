package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementActions elementActions;
	
	ContactsPage(WebDriver driver)
	{
		this.driver = driver;
		elementActions = new ElementActions(this.driver);
		
	}
	
	By createContactButton = By.xpath("//button[@type='button']//span[text()='Create contact']");
	By createContactFormButton = By.xpath("//button[@type='button']/span[text()='Create contact']");
			
	By email = By.cssSelector("input[type='text'][name='textInput']");
	By firstName = By.cssSelector("input[data-field='firstname']");
	By lastName = By.cssSelector("input[data-field='lastname']");
	By jobTitle = By.cssSelector("input[data-field='jobtitle']");
	
	By linkContact = By.xpath("(//i18n-string[text()='Contacts'])[2]");
	
	public String getContactsPageTitle()
	{
		return elementActions.doGetPageTitle(AppConstants.CONTACT_PAGE_TITLE);
	}
	
	public void createContact(String email, String firstname, String lastname, String jobtitle)
	{
		elementActions.waitForElementPresent(createContactButton);
		elementActions.doClick(createContactButton);
		
		elementActions.waitForElementPresent(this.email);
		elementActions.doSendKeys(this.email, email);
		
		elementActions.doSendKeys(this.firstName, firstname);
		elementActions.doSendKeys(this.lastName, lastname);
		
		elementActions.waitForElementVisible(this.jobTitle);
		elementActions.waitForElementVisible(jobTitle);
		elementActions.doSendKeys(this.jobTitle, jobtitle);
		
		elementActions.clickElementByJS(createContactFormButton);
		
		elementActions.waitForElementVisible(linkContact);
		elementActions.doClick(linkContact);
	
	}
	
	
}
