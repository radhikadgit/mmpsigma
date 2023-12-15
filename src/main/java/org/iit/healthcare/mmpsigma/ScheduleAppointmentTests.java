package org.iit.healthcare.mmpsigma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests {

	WebDriver driver;
	@Test
	public void validateScheduleAppointment()
	{
		 
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		navigateToModule("Schedule Appointment");
		driver.findElement(By.cssSelector("input[value='Create new appointment']")).click();
		//driver.findElement(By.xpath("//h4[contains(text(),'Charlie')]/ancestor::ul/following-sibling::button")).click();
		selectDoctorModule("Charlie");
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("11/17/2023");
		driver.findElement(By.cssSelector("a.ui-state-default.ui-state-active")).click();
	    driver.findElement(By.id("time")).click();
		Select selDropDown = new Select(driver.findElement(By.id("time")));
		selDropDown.selectByValue("10Am");
		driver.findElement(By.id("time")).click();
		driver.findElement(By.id("ChangeHeatName")).click();
		
		
	}
	public void navigateToModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
	
	public void selectDoctorModule(String doctorName) {
		driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button")).click();
	}
	

	
	
	
	
}	
	