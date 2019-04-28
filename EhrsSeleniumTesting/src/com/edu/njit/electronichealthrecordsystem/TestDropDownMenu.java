package com.edu.njit.electronichealthrecordsystem;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class TestDropDownMenu {

	
	
WebDriver driver;
	
	public void CheckDropDown()
	
		{
		
	
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
					
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://localhost:3000/");
						
			String arr[] = {"Male" , "Female"};
			
			
			Select drpgender = new Select(driver.findElement(By.name("row")));
			
			//drpgender.selectByVisibleText("Male");
			List<WebElement> dropdownValues = drpgender.getOptions();
			System.out.println( dropdownValues.size());
			
			
			for(int  j=0 ; j<= dropdownValues.size(); j++ )
			{
				//Assert.assertEquals(arr[j], dropdownValues.size().getValue());
								
			}
		
			
			String at = driver.getTitle();
			String et = "Ehrs";
			
			
			driver.close();
			if(at.equalsIgnoreCase(et))
			{
				System.out.println("Test successful");
			}
			else
			{
				System.out.println("Test Unsuccessful");
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		}

	
	
	public static void main(String args[])
	{
		TestDropDownMenu testobj =new  TestDropDownMenu ();
		testobj.CheckDropDown();
	
	
	}

	
}
