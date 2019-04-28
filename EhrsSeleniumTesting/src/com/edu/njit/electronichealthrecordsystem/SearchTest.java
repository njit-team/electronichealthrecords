package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
	WebDriver driver;
	
	public void invokeBrowser()
	
		{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			
			driver.get("http://localhost:3000/receptionist/");
			
			driver.findElement(By.id("search")).sendKeys("abc123");
			driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/body/header/nav/div/ul/li[2]/nav/div/form/div/label/i")).click();
							
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
			
	}
	
		
public static void main(String args[])
	{
		SearchTest testobj =new SearchTest();
		testobj.invokeBrowser();
	
	
	}
	
		
	
}
