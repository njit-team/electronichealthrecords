package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAddPatient {
	
	
	WebDriver driver;
	

	public void invokeBrowser()
	{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();	
			driver.get("http://localhost:3000/add_patient");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			addPatientPage();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}					
	}
	
	
	
	public void addPatientPage ()
	{
	try {			
		
		driver.findElement(By.id("first_name")).sendKeys("Anju");
		driver.findElement(By.id("last_name")).sendKeys("More");
		driver.findElement(By.id("email")).sendKeys("@njit.edu");
		driver.findElement(By.name("lastname")).sendKeys("xxx");
		driver.findElement(By.name("phoneNumber")).sendKeys("9902929299");
		driver.findElement(By.name("apartment")).sendKeys("03");
		driver.findElement(By.name("street")).sendKeys("summit avenue");
		driver.findElement(By.name("city")).sendKeys("Newark");
		driver.findElement(By.name("state")).sendKeys("New Jersey");
		driver.findElement(By.name("zipCode")).sendKeys("07029");
		driver.findElement(By.name("country")).sendKeys("USA");
		
		driver.findElement(By.id("date_of_birth")).sendKeys("02/01/2000");
		
		
		Select drpgender = new Select(driver.findElement(By.name("")));
		drpgender.selectByIndex(1);
		
	
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.className("text-field")).click();
				
		String at = driver.getTitle();
		
		//test Oracle
		String et = "React App";
		driver.close();
		
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
				
		
		TestAddPatient testobj =new TestAddPatient();
		testobj.invokeBrowser();

		
			} 

	
}
