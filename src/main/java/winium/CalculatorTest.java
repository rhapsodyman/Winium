package winium;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

	@Test
	public void test() throws Exception{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\calc.exe");
		cap.setCapability("launchDelay","5");
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap);


		driver.findElement(By.id("133"));

		//Enter 3
		WebElement thereButton = driver.findElement(By.id("133"));
		thereButton.click();

		//Enter +
		WebElement plusButton = driver.findElement(By.id("93"));
		plusButton.click();

		//Enter 3
		thereButton.click();

		//Enter =
		WebElement equalsButton = driver.findElement(By.id("121"));
		equalsButton.click();

		WebElement result = driver.findElement(By.id("150"));

		Assert.assertEquals(result.getAttribute("Name"), "6");
		
		
		// make screenshot
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\screenshot.png"));
		driver.quit();
	}
}
