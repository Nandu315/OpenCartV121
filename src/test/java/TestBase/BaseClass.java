package TestBase;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression","Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities dc=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				dc.setPlatform(Platform.WIN11);

			}
			else if(os.equalsIgnoreCase("mac"))
			{
				dc.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				dc.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
				case "chrome": dc.setBrowserName("chrome");break;
				case "edge": dc.setBrowserName("MicrosoftEdge");break;
				case "firefox": dc.setBrowserName("firefox");break;
				default:System.out.println("No matching browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://192.168.216.185:4444/wd/hub"),dc);			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			switch(br.toLowerCase())
			{
				case "chrome": driver=new ChromeDriver();break;
				case "edge": driver=new EdgeDriver(); break;
				case "firefox": driver=new FirefoxDriver();break;
				default : System.out.println("Invalid browser name..");return;
			}
		}
						
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appUrl")); //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity", "Regression","Master"})
	void teardown()
	{
		driver.close();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
//	public String randomAlphanumeric()
//	{
//		String generatedalphanumeric=RandomStringUtils.randomAlphanumeric(10);
//		return generatedalphanumeric;
//	}
	//or
	public String randomAlphanumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return (generatedstring+"$*"+generatednumber);//if password required special character

	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourcefile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+ timestamp;
		File targetfile=new File(targetFilePath);
		
		sourcefile.renameTo(targetfile);
		
		return targetFilePath;
	}

}
