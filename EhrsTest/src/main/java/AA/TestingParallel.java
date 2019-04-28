package AA;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
public class TestingParallel {
	

	@Test
	public void test1() {
				
		//System.out.println("(1)Helloo");
	
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
				
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get("http://localhost:3000/login/");
		
		
		driver.findElement(By.name("ID")).sendKeys("01fb4");
		driver.findElement(By.name("password")).sendKeys("babatunde");
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/input")).click();
				
	}

	@Test
	public void test2() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
				
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/login/");
					
		
		driver.findElement(By.name("ID")).sendKeys("doc18");
		driver.findElement(By.name("password")).sendKeys("babatunde");
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/input")).click();
		
		
	}

		
}
