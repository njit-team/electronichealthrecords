package com.edu.njit.electronichealthrecordsystem;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class testScreen {
	
WebDriver driver;
	
	public void invokeBrowser()
	
		{
		
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
					
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("http://localhost:3000/login");
						
			
			driver.findElement(By.name("ID")).sendKeys("bJpv0");
			driver.findElement(By.name("password")).sendKeys("anju");
			
			driver.findElement(By.name("login")).click();
			
			
			//driver.save_screenshot('screenie.png')
			
		//	driver.get("http://www.google.com/");
			//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			//FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
			
			
			
			
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
			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		}	
	
	public static void main(String args[])
	{
		testScreen testobj =new  testScreen();
		testobj.invokeBrowser();
	
	}
	
	
}
