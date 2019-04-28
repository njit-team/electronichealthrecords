package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestLabTech {

	
	
	
	
WebDriver driver;
	
	public void invokeBrowser()
	{
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();	
			driver.get("http://localhost:3000/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			insertData();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
					
	}
	
	
	public void insertData ()
	{
	try {
		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get("http://localhost:3000/lab_technician");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/div[2]/ul/li[1]")).click();
		driver.findElement(By.name("medicineName")).sendKeys("Paracetmol");
		Select drpdosage = new Select(driver.findElement(By.name("dosage")));
		drpdosage.selectByVisibleText("1 Daily");
		driver.findElement(By.name("additionalComments")).sendKeys("Take after lunch");
		driver.findElement(By.name("active")).sendKeys("Yes");
		
		//Test Oracle
		String at = driver.getTitle();
		String et = "React App";
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
	
	
	
	

	
	public static void main(String args[])
	{
		TestLabTech testobj =new TestLabTech();
		testobj.invokeBrowser();
		

	}

	
	
	
	
}
