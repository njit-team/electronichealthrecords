package com.edu.ehrs.Admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddStaff {
	
WebDriver driver;
	
	public void invokeBrowser()
	
		{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://localhost:3000/login/");
						
			
			driver.findElement(By.name("ID")).sendKeys("doc18");
			driver.findElement(By.name("password")).sendKeys("babatunde");
			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/input")).click();
			/*
			
			// click on side menu
			driver.findElement(By.xpath("//*[@id="root"]/div/body/header/div/nav/div/a[1]/i").click();
			
			//click on add staff
			driver.findElement(By.xpath("//*[@id="slide-out"]/li[4]/a/i")).click();
			
			driver.findElement(By.id("date_of_birth")).sendKeys("02/01/2000");
			driver.findElement(By.name("phoneNumber")).sendKeys("9902929299");
			driver.findElement(By.name("apartment")).sendKeys("03");
			driver.findElement(By.name("street")).sendKeys("summit avenue");
			driver.findElement(By.name("city")).sendKeys("Newark");
			driver.findElement(By.name("state")).sendKeys("New Jersey");
			driver.findElement(By.name("zipCode")).sendKeys("07029");
			driver.findElement(By.name("country")).sendKeys("USA");
			
			
			*/
			
			
			
			//Test Oracle
			
			String at = driver.getTitle();
			String et = "Ehrs";
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
	TestAdminLogin  testobj =new TestAdminLogin ();
		testobj.invokeBrowser();
	
	
	}
	
	

	
}
