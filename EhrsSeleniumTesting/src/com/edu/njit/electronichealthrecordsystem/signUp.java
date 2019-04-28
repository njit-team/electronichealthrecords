package com.edu.njit.electronichealthrecordsystem;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



//import org.openqa.selenium.support.ui.Select;



public class signUp {

	
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
			
			getSignupPage();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}					
	}
	
	public void getSignupPage ()
	{
	try {			
		
		driver.findElement(By.id("first_name")).sendKeys("Anju");
		driver.findElement(By.id("last_name")).sendKeys("More");
		driver.findElement(By.id("email")).sendKeys("@njit.edu");
			
		driver.findElement(By.name("lastname")).sendKeys("xxx");
		
	
	
	
	//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
	
	 //Select seli= new Select(driver.findElement(By.id("select-options-fa984ff3-5409-59d0-21b1-e8770af43b5b")));
	//seli.selectByValue("Male");
	
	
	
	String searchText = "Male";
	WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input"));
	dropdown.click(); // assuming you have to click the "dropdown" to open it
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(searchText))
	    {
	        option.click(); // click the desired option
	        break;
	    }
	}
	
	
	
	
	

	driver.findElement(By.id("date_of_birth")).sendKeys("02/01/2000");
	driver.findElement(By.name("phoneNumber")).sendKeys("9902929299");
	driver.findElement(By.name("apartment")).sendKeys("03");
	driver.findElement(By.name("street")).sendKeys("summit avenue");
	driver.findElement(By.name("city")).sendKeys("Newark");
	driver.findElement(By.name("state")).sendKeys("New Jersey");
	driver.findElement(By.name("zipCode")).sendKeys("07029");
	driver.findElement(By.name("country")).sendKeys("USA");
	
	
	
//elect drpStaffType = new Select(driver.findElement(By.name("staffType")));
//rpStaffType.selectByVisibleText("Doctor");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.className("text-field")).click();
			
	String at = driver.getTitle();
	
	//test Oracle
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
	
	
	//driver.quit();
		
} catch (Exception e) {

	e.printStackTrace();
}
}
	

//-------------------------
public static void main(String[] args) {
// TODO Auto-generated method stub
	
	 signUp testobj =new  signUp();
	testobj.invokeBrowser();
	
		} 

	
	
	
}
