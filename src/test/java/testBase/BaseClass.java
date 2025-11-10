 package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
public Logger logger;
public Properties prop;

	public static WebDriver driver;
	
	@BeforeClass(groups= {"Sanity","master","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws InterruptedException, IOException
	{
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		// Selenium Grid example
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			// passing OS parameter
			if (os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			
			// passing browser parameter
			
			switch(browser.toLowerCase())
			{
			case  "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("edge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); break;
			}
			
			
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			
			
		}
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			
			// passing browser parameter
			
			switch(browser.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge"   : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "safari" : driver = new SafariDriver(); break;
			default : System.out.println("Invalid browser name");
			}
			
						
		}
		
		// Loading multiple browser drivers as per properties file
		
	/*	switch(browser.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge"   : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		case "safari" : driver = new SafariDriver(); break;
		default : System.out.println("Invalid browser name");
		} */
		

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	//	driver.get(prop.getProperty("appURL")); <----- gets the property value of URL from properties file
		driver.get("http://localhost/opencart/upload/index.php");

	}
	
	@AfterClass(groups= {"Sanity","master","Regression"})
	public void tearDown() throws InterruptedException
	{
	
		driver.quit();
	}

	public String randomString()
	{
		String fname = RandomStringUtils.randomAlphabetic(6);
		return fname;
		
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphanumeric()
	{
		String fname = RandomStringUtils.randomAlphabetic(6);
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return (fname+"@"+generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir"+"\\screenshots\\" +tname+"_" + timeStamp);
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
	
}
