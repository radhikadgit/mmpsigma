package org.iit.healthcare.mmpsigma;

import org.iit.healthcare.patientmodule.HomePage;
import org.iit.healthcare.patientmodule.LoginPage;
import org.iit.healthcare.util.AppLibrary;
import org.iit.healthcare.util.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileTests extends BaseClass {
	
	@Test
	public void validatefirstName() {
		
		LoginPage lPage = new LoginPage(driver);
		//System.out.println("userName: "+prop.getProperty("patient_username"));
		lPage.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage homePage = new HomePage(driver);
		homePage.navigateToModule("Profile");
		//driver.findElement(By.xpath("//span[contains(text(),'Profile')] ")).click();
		driver.findElement(By.id("Ebtn")).click();
		AppLibrary appLibrary = new AppLibrary();
		String expectedFname = appLibrary.generateRandomString("FNAME");
		WebElement fnameWE = driver.findElement(By.id("fname"));
		fnameWE.clear();
		fnameWE.sendKeys(expectedFname);
		String actualFname = fnameWE.getAttribute("value");
		driver.findElement(By.id("Sbtn")).submit();
		//achieve pop up message with Alert class
		//driver.switchTo().alert();
		Alert alert = driver.switchTo().alert();
		String actualMsg = alert.getText();
		String expectedMsg = "Your Profile has been updated.";
		alert.accept();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualFname, expectedFname);
		sa.assertEquals(actualMsg, expectedMsg);
		sa.assertAll();
	}
	

}
