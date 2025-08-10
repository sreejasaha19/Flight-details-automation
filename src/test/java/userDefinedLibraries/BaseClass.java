package userDefinedLibraries;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	public static WebDriver driver;	
	public static Properties p;
	
	// File path for storing screenshots
	public static String filepath = ".\\ScreenShot\\";

	// Method to capture screenshot
	public String captureScreen(String tname) throws IOException {
		// Takes a screenshot and stores it in sourceFile
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		// Defines the target file path and renames the source file to the target file
		String targetFilePath=System.getProperty("user.dir")+filepath + tname +".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
			
		// Returns the path of the screenshot file
		return targetFilePath;
	}
	
	// Method to scroll to a specific WebElement
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void ClickElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	// Method to instantiate the WebDriver based on the browser type
	public static WebDriver driverInstantiate(String browser) throws IOException {
		// Loading properties file
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);

		// Switch case to handle different browser types
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
 
		switch(browser.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(options); break;
		case "edge": driver=new EdgeDriver(); break;
		default: System.out.println("No matching browser..");
		}
		// Maximizing the browser window and deleting all cookies
		driver.manage().window().maximize();	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


		// Navigating to the URL specified in the properties file
		driver.get(p.getProperty("URL"));
		return driver;
	}
	

	// Method to close the WebDriver
	public static void driverTearDown() {
		driver.quit();
	}

}
