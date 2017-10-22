package winium;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriverRunner {

	@BeforeMethod
	public void beforeMethod(){
		System.out.println("beforeMethod");
	}

	@AfterMethod
	public void AfterMethod(){
		System.out.println("AfterMethod");
	}

	
	@Test
	public void test() throws IOException, InterruptedException {

		String driverPath = "D:\\Winium\\Winium.Desktop.Driver\\Winium.Desktop.Driver.exe";

		ProcessBuilder pro = new ProcessBuilder(driverPath);
		Process shell = pro.start();
		Thread.sleep(3000);
		
//		String string = IOUtils.toString(shell.getInputStream());
//		System.out.println(string);
		// <наш код>

		Thread.sleep(1000);


		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\calc.exe"); //если хотим сразу запускать какую-либо программу
		cap.setCapability("launchDelay","5"); //задержка после запуска программы
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"), cap); //на этом порту по умолчанию висит Winium драйвер


		driver.findElement(By.id("133"));

		//Enter 3
		WebElement thereButton = driver.findElement(By.id("133"));
		thereButton.click();
		Thread.sleep(1000);
		
		
		driver.quit();
		shell.destroy();
	}

}
