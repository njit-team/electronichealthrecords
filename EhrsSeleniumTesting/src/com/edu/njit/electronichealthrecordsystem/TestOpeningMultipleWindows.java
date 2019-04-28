package com.edu.njit.electronichealthrecordsystem;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestOpeningMultipleWindows {

	WebDriver driver;
	
	public void invokeBrowser()
	{
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();	
					
			 OpeningWindow();		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
					
	}
	
	public void OpeningWindow ()
	{
	try {
		
		driver.get("http://localhost:3000/");
		String tittleofthepage =driver.getTitle();
		System.out.println("Title of the page is:" +tittleofthepage );
		
			
		WebElement clickElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/nav/div/a[2]"));
		
		for(int i=0 ; i<=3; i++)
		{
			clickElement.click();
		 Thread.sleep(3000);
			
		}
		
		driver.quit();
			
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	}
			
	public static void main(String[] args) 
	{	
		TestOpeningMultipleWindows testobj =new TestOpeningMultipleWindows();
		testobj.invokeBrowser();
	
	}
		
}
