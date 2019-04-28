package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGetCommand {
	WebDriver driver;
	
	public void invokeBrowser()
	{
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();	

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			
			getCommands();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
					
	}
	
	public void getCommands ()
	{
	try {
		
		driver.get("http://localhost:3000/");
		String tittleofthepage =driver.getTitle();
		System.out.println("Title of the page is:" +tittleofthepage );
		driver.findElement(By.linkText("Today's Deals")).click();
		String currentUrl =driver.getCurrentUrl();
		System.out.println("The current URL is"+ currentUrl);
		

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		
		driver.quit();
			
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	}
			
	public static void main(String[] args) 
	{
	
		TestGetCommand testobj =new TestGetCommand();
		testobj.invokeBrowser();
		

	}

}
