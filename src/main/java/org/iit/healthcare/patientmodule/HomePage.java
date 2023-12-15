package org.iit.healthcare.patientmodule;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	HashMap<String,String> actualHMap = new HashMap<String,String>();

	private WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}

	public HashMap<String, String> fetchPatientPortalValues()
	{
		String actualSym= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		actualHMap.put("sym", actualSym);
		String actualDate= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		actualHMap.put("date", actualDate);
		return actualHMap;
	}
	public void navigateToModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
}
