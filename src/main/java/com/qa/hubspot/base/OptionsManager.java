package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager{

	public ChromeOptions co;
	public FirefoxOptions fo;
	Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if((Boolean.parseBoolean(prop.getProperty("headless")))==true) {
			co.addArguments("--headless");
		}
		
		if((Boolean.parseBoolean(prop.getProperty("incognito")))==true) co.addArguments("--incognito");	
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))); fo.addArguments("--headless");
		//if(Boolean.parseBoolean(prop.getProperty("incognito"))); fo.addArguments("--incognito");
		return fo;
		
	}
	
}