package winium;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class NotepadTest {

	@Test
	public void test() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe"); //если хотим сразу запускать какую-либо программу

		cap.setCapability("launchDelay","5"); //задержка после запуска программы

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap); //на этом порту по умолчанию висит Winium драйвер


		WebElement el = driver.findElement(By.id("15"));

		el.sendKeys("Hello from Notepad.\t");
		//el.sendKeys(Keys.ENTER);
		//el.sendKeys("1 2 3 4 5 6 7 8 9 10");



		Actions actions = new Actions(driver);

		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.ENTER).perform();
		
		actions.sendKeys(Keys.TAB).perform();



		//driver.quit();
	}
}
