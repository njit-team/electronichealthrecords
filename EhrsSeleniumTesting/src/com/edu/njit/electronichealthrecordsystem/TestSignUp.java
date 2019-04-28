package com.edu.njit.electronichealthrecordsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestSignUp {
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
			
			
		/*	
			driver.findElement(By.tagName("html")).sendKeys(
					Keys.chord(Keys.CONTROL, Keys.ADD));
			driver.findElement(By.tagName("html")).sendKeys(
					Keys.chord(Keys.CONTROL, Keys.ADD));
			driver.findElement(By.tagName("html")).sendKeys(
					Keys.chord(Keys.CONTROL, Keys.ADD));
			driver.findElement(By.tagName("html")).sendKeys(
					Keys.chord(Keys.CONTROL, Keys.ADD));
			
			*/
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
		
		
		//Select drpgender = new Select(driver.findElement(By.name("row")));
		//drpgender.selectByVisibleText("Male");
		
		//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
		
		
	// Select seli= new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/select")));
	//	seli.selectByValue("Male");
		 
	//	WebElement ee=driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input"));
	//	ee.click();
		
		
		//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
		
	//	driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
		
		 //Select seli= new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/select")));
		//seli.selectByIndex(2);
		
		
		//ee.findElement(By.xpath("//*[@id=\"select-options-669db1ce-2246-822d-5226-2138de90c7b11\"]/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"select-options-669db1ce-2246-822d-5226-2138de90c7b11\"]/span")).click();
		
		
		/*
		List<WebElement> genderList= driver.findElements(By.cssSelector("#select-options-6b6e04cf-1df2-24a3-a28c-12499792ef5c"));
for(WebElement gender:genderList ){
if( gender.getText().trim().equals("Male"))
 gender.click(); */

//		driver.findElement(By.id("select-options-fa984ff3-5409-59d0-21b1-e8770af43b5b"));

		//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
//driver.findElement(By.xpath("//*[@id=\"select-options-f1ce163a-daa0-9f05-4b06-e8a50e5134191\"]")).click();
		
		 //Select seli= new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/select")));
			//seli.selectByVisibleText("Male");
					
			
			//staff
			//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[2]/div/input ")).click();
			
			
		//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
		 //Select seli= new Select(driver.findElement(By.id("drop_down")));
		//seli.selectByVisibleText("Male");
		
		
		
		
		
		//*[@id="drop_down"]/option[2]
		//*[@id="drop_down"]/option[3]
		
		 
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();
		
	//	Select drpCountry = new Select(driver.findElement(By.id("drop_down")));
		//drpCountry.selectByVisibleText("Male");

		
		WebElement drop=driver.findElement(By.xpath("//*[@id=\"drop_down\"]"));
		
		drop.findElement(By.xpath("//*[@id=\"drop_down\"]/option[2]")).click();
		
		
		
		//driver.findElement(By.xpath("//*[@id=\"root\"]/div/body/header/div/form/div[3]/div[1]/div/input")).click();




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
		
		TestSignUp testobj =new TestSignUp();
		testobj.invokeBrowser();
		
			} 
	
}