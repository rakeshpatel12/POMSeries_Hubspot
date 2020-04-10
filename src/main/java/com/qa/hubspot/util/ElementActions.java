package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	WebDriver driver;
	WebDriverWait wait;

	/**
	 * Constructor to init driver and wait
	 * 
	 * @param driver
	 */
	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, AppConstants.DEFAULT_EXPLICIT_TIMEOUT);
	}

	/**
	 * Get WebElement on the basic of locator
	 * 
	 * @param locator
	 * @return web element
	 */

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some Exception Occured while creating web element : " + locator);
		}
		return element;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);

	}

	public boolean doIsDisplayed(By locator) {
		waitForElementPresent(locator);
		return getElement(locator).isDisplayed();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String doGetPageTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	/**
	 * Java Script Click 
	 * @param locator
	 */
	public void clickElementByJS(By locator)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",getElement(locator));
	}

	
}
