package org.iit.healthcare.mmpsigma;

import java.util.Arrays;
import java.util.HashMap;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewInformationPage {

	WebDriver driver;
	String expected;
	String actual = "";
	HashMap<String, String> expectedHMap = new HashMap<String, String>();
	HashMap<String, String> actualHMap = new HashMap<String, String>();

	@Test
	public void validateInformationPage() {

		driver = new ChromeDriver();
		// open patient URL
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();

		// validate patient information
		navigateToModule("Information");
		driver.findElement(By.cssSelector("div.panel-title"));
		String expected = "Manage My Patient (MMP) is a medical practice management"
				+ " solution that boosts productivity by automating the day-to-day "
				+ "tasks that can slow an office manager down. Central delivers much"
				+ " more than medical billing software. Sure, it has the tools to "
				+ "help generate cleaner claims and reduce denials, but our "
				+ "easy-to-use practice management software also streamlines "
				+ "your workflow to deliver seamless handoffs across departments. "
				+ "Manage My Patient (MMP) becomes your practice’s command center, "
				+ "delivering robust, real-time analytics through customizable reports and"
				+ " dashboards to ensure you know how your business is performing on the metrics that matter most.";

		String[] expectedList = expected.split(" ");
		
		String actual = (driver.findElement(By.cssSelector("div.panel-title")).getText()).trim();
		String[] actualTextList = actual.split("\n");
		String actualText = "";
		for (int i = 0; i<actualTextList.length; i++) {
			actualTextList[i] = actualTextList[i].trim();
			if (!(actualTextList[i].isEmpty())) {
				actualText = actualText + " " + actualTextList[i];
			}
		}
		System.out.println(actualText);
		actualText = actualText.trim();
		String[] actualList = actualText.split(" ");
	    Arrays.equals(expectedList, actualList);		
	}

	@Test
	public void validateSymptoms() {

		// validate patient symptoms
		navigateToModule("Search Symptoms");
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		String expectedSym = "headache";
		givenSymptoms(expectedSym);
		expectedHMap.put("search", expectedSym);		
		String symptomLoc = "headache";
		WebElement e = waitforElementToBeDisplayed(By.xpath("//td[contains(text(),'" + symptomLoc + "')][1]"), 5);
		String actualSym = e.getText();
		actualHMap.put("search", actualSym);
		System.out.println(actualSym);
		Assert.assertEquals(actualHMap, expectedHMap);

	}

	public void navigateToModule(String moduleName) {
		driver.findElement(By.xpath("//span[contains(text(),'" + moduleName + "')]")).click();
	}

	public void givenSymptoms(String symptom) {
		driver.findElement(By.id("search")).sendKeys(symptom);
	}

	public WebElement waitforElementToBeDisplayed(By locator, int secs) {
		Duration duration = Duration.ofSeconds(secs);
		WebDriverWait wait = new WebDriverWait(driver, duration);
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return e;
	}

}
