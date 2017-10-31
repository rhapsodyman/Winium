package winium;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class ExcelTest {

	@Test
	public void test() throws Exception{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Program Files\\Microsoft Office\\Office15\\EXCEL.EXE"); //если хотим сразу запускать какую-либо программу
		
		cap.setCapability("args", "D:\\Doc.xlsx"); //если хотим сразу запускать какую-либо программу
		
		cap.setCapability("launchDelay","5"); //задержка после запуска программы
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap); //на этом порту по умолчанию висит Winium драйвер


		WebElement el = driver.findElement(By.name("Вставка"));
//		System.out.println(el.getTagName());
		el.click();
		Thread.sleep(10000);
		
		
//		for (WebElement webElement : findElements) {
//			
//		}
		
		//System.out.println("Found " + findElements.size());
		//Assert.assertEquals(result.getAttribute("Name"), "6");
		driver.quit();
	}
}
