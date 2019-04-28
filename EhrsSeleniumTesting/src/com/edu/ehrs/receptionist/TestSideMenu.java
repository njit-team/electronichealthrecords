package com.edu.ehrs.receptionist;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSideMenu {

	
	
	
WebDriver driver;
	
	public void invokeBrowser()
	
		{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
					
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://localhost:3000/login/");
						
			
			driver.findElement(By.name("ID")).sendKeys("2S81r");
			driver.findElement(By.name("password")).sendKeys("babatunde");
			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			//login button
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/input")).click();

			
			/*
			//click on side menu 
			
			driver.findElement(By.xpath("")).click();
	
			*/
			
			
			//Test Oracle
			
			String at = driver.getTitle();
			String et = "React App";
			//driver.close();
			if(at.equalsIgnoreCase(et))
			{
				System.out.println("Test successful");
			}
			else
			{
				System.out.println("Test Unsuccessful");
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		//	driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
	
		
public static void main(String args[])
	{
	TestResLogin testobj =new TestResLogin();
		testobj.invokeBrowser();
	
	
	}
	
	
	
}
