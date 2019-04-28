package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDoctorSearch {

	
	WebDriver driver;
	
	public void invokeBrowser()
	
		{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			
			driver.get("http://localhost:3000/doctor");
			
			
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
			driver.get("http://localhost:3000/doctor/");
			driver.manage().window().maximize();
			driver.findElement(By.id("first_name")).sendKeys("122222");
			driver.findElement(By.className("text-field")).click();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
			
	}
	
		
public static void main(String args[])
	{
	TestDoctorSearch testobj =new TestDoctorSearch();
		testobj.invokeBrowser();
	
	
	}
	
				
	
}
