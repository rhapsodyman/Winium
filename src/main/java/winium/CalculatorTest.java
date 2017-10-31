package winium;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

	@Test
	public void test() throws Exception{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\calc.exe"); //если хотим сразу запускать какую-либо программу
		cap.setCapability("launchDelay","5"); //задержка после запуска программы
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap); //на этом порту по умолчанию висит Winium драйвер


		driver.findElement(By.id("133"));

		//Enter 3
		WebElement thereButton = driver.findElement(By.id("133"));
		thereButton.click();
		Thread.sleep(1000);

		//Enter +
		WebElement plusButton = driver.findElement(By.id("93"));
		plusButton.click();
		Thread.sleep(1000);

		//Enter 3
		thereButton.click();
		Thread.sleep(1000);

		//Enter =
		WebElement equalsButton = driver.findElement(By.id("121"));
		equalsButton.click();
		Thread.sleep(1000);

		WebElement result = driver.findElement(By.id("150"));

		Assert.assertEquals(result.getAttribute("Name"), "6");
		
		driver.executeScript("input: ctrl_click", thereButton);
		
		driver.quit();
	}
}
