package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewStaffs {
	
	
WebDriver driver;
	
	public void invokeBrowser()
	
		{
				
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
					
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			
			driver.get("http://localhost:3000");
			//for menu
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/nav/div/ul/li[2]/a/i")).click();
			
			//for selecting receptionist 
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/nav/div/a[1]/i")).click();
			
			
			//selecting view appointments
			driver.findElement(By.xpath("//*[@id=\"slide-out\"]/li[6]/a")).click();
						
			
					
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
			String title= driver.getTitle();
			System.out.println("Page Title is :"+ title);
			
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
	}
					
	}
	
public static void main(String args[])
	{
	ViewStaffs testobj =new ViewStaffs();
		testobj.invokeBrowser();
	}
	

}
