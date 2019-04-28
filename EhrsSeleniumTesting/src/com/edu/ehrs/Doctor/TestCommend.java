package com.edu.ehrs.Doctor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCommend {
	

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
			
			driver.findElement(By.id("first_name")).sendKeys("01fb4");
			
			
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/button")).click();
			
			//click on commend
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/div[2]/div[2]/ul/li[5]")).click();
			//write test
			
			driver.findElement(By.id("textarea1")).sendKeys(" 30 min walk every day. ");
			
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/div[2]/div[3]/form/button")).click();	
			
			//Test Oracle
			 
			String at = driver.getTitle();
			String et = "React App";
		//	driver.close();
			if(at.equalsIgnoreCase(et))
			{
				System.out.println("Test successful");
			}
			else
			{
				System.out.println("Test Unsuccessful");
			}
			
			//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
	driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
	
		
public static void main(String args[])
	{
	TestCommend testobj =new TestCommend();
		testobj.invokeBrowser();
	
	
	}

}
