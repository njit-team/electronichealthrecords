package AA;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CrossBrowserTesting {
	
		
public static WebDriver driver;
public static int browser;
public static String BrowserName;

public static void main(String[] args) {

for (browser = 1; browser <= 2; browser++){
if (browser == 1) {
	
	
System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");
driver = new FirefoxDriver();
BrowserName = "Mozilla Firefox Browser: ";




}
else if (browser == 2) {
System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe" );
driver = new ChromeDriver();
BrowserName = "Google Chrome Browser: ";
}

driver.get("http://localhost:3000/login/");


driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);



String PageTitle = driver.getTitle();

if (PageTitle.equals("React App")){
System.out.println(BrowserName + " - Ehrs Application Launched - Passed");
}
else {
System.out.println(BrowserName + " - Ehrs Application Not Launched -Failed"); 
}
driver.close();
}
}

	

}
