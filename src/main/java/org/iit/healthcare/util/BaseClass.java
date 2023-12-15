package org.iit.healthcare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	protected Properties prop;
	@BeforeSuite
	public void loadProperties() throws IOException
	{
		readProperties("mmp.properties");
	}
	@BeforeClass
	public void instantiateDriver() throws IOException
	{
		if(prop.getProperty("browserType").equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browserType").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		System.out.println("environment_if :"+ prop.getProperty("environment"));
		if(prop.getProperty("environment").equals("qa"))
		{
			readProperties("qa.properties");
			System.out.println(prop.getProperty("patient_url"));
		}
		else if(prop.getProperty("environment").equals("dev"))
		{
			readProperties("qa.properties");
			//readProperties("dev.properties");
		}
		
		System.out.println("Environment :"+prop.getProperty("environment"));

	    driver.get(prop.getProperty("patient_url"));
		System.out.println("patientUrl_1 :"+ prop.getProperty("patient_url"));
		driver.manage().window().maximize();
		System.out.println("after maximize");         
		
		
	}
	public void readProperties(String fileName) throws IOException
	{
		File propFile = new File(System.getProperty("user.dir")+"//src//main//java//org//iit//healthcare//mmpsigma//mmpsigma//config//"+fileName);
		FileInputStream fis = new FileInputStream(propFile);
		prop = new Properties();
		prop.setProperty("environment", "qa");
		prop.load(fis);
		System.out.println("fileName :"+fileName);
	}

}
