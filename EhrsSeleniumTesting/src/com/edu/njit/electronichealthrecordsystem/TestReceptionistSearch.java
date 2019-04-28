package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestReceptionistSearch {

		
	
WebDriver driver;
	
	public void invokeBrowser()
	{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			//driver.manage().deleteAllCookies();
			//driver.manage().window().maximize();	
			//driver.get("http://localhost:3000/");
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			testSearch();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}					
	}
	
	public void testSearch ()
	{
	try {			
		
						
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
	
		
		driver.get("http://localhost:3000/login/");
		
		
		driver.findElement(By.name("ID")).sendKeys("2S81r");
		driver.findElement(By.name("password")).sendKeys("babatunde");
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.findElement(By.name("login")).click();
		
		
		driver.findElement(By.id("search")).sendKeys("aj5ob");
		driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/body/header/nav/div/ul/li[2]/nav/div/form/div/label/i")).click();
						
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
			
		
		
		
		
		
		String at = driver.getTitle();
		
		//test Oracle0
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
	
		
		
		driver.quit();
			
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	}
		
	
	//-------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		
		TestReceptionistSearch testobj =new TestReceptionistSearch();
		testobj.invokeBrowser();

		
			} 
	
	
	
	
	
	
	
}
