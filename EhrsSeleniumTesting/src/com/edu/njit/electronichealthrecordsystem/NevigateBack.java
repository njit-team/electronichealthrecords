package com.edu.njit.electronichealthrecordsystem;


import org.seleniumhq.jetty9.util.annotation.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NevigateBack {
	
WebDriver driver;
	
	public void invokeBrowser()
	{		
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get("http://localhost:3000/receptionist");
		
		driver.findElement(By.id("search")).sendKeys("abc123");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/body/header/nav/div/ul/li[2]/nav/div/form/div/label ")).click();
						
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
		//-------------------
		// navigate back and open different page
		
		driver.navigate().back();
		
		String title= driver.getTitle();
		System.out.println("Page Title is :"+ title);
		driver.navigate().to("http://localhost:3000/login");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.name("ID")).sendKeys("doc17");
		driver.findElement(By.name("password")).sendKeys("anju");
		
		driver.findElement(By.name("login")).click();
		
		//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			
		driver.quit();
			
		
	}
	
	public static void main(String[] args) 
	{
		NevigateBack testobj = new NevigateBack();
		testobj.invokeBrowser();
		
	}

}
