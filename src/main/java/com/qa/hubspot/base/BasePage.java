package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public  Properties prop;
	public OptionsManager optionsManager;
	
	/**
	 * ThreadLocal use to generate report in parallel mode it has two method get() and set()
	 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * Thread Local getMethod to get web driver value , as soon as it set it will 
	 * get the driver 
	 * @return
	 */
	public static WebDriver getDriver() {
		//System.out.println(tlDriver);
		return tlDriver.get();
	}
	
	
	
	/**
	 * This method is used to initialize the Webdriver on the basis of
	 * browser name
	 * @param browserName
	 * @return this method will return driver instance
	 */
	
	public WebDriver init_driver(Properties prop)
	{
		
		String browserName =prop.getProperty("browser");
		
		//boolean isHeadless =  Boolean.parseBoolean(prop.getProperty("headless"));
		optionsManager = new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			System.out.println(tlDriver);
			/*if(isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
			driver= new ChromeDriver(co);
			}else driver= new ChromeDriver();*/

			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			/*if(isHeadless) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
			driver= new FirefoxDriver(fo);
			}else driver = new  FirefoxDriver();*/
			
		}
		else
		{ 
			
			System.out.println(browserName + " Is not a valid browse name");
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		
		getDriver().get(prop.getProperty("url"));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return getDriver();
		
	}
	
	/**
	 * 
	 * @return this method returns properties - available in config file
	 */
	public Properties init_prop()
	{
		prop = new Properties();
		try {
			FileInputStream ip =  new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Config file not found...");
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return prop;
		
	}
	
	/**
	 * Take screen shot
	 * USE TakesScreenshot interface,
	 * Convert driver into TakesScreenshot and use getScreenshotAs method
	 * Set path and transfer it from source to destination
	 * @return
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	
}
