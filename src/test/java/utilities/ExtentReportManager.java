package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter spartReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext textcontext)
	{
		/* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt=new Date();
		  String currentdatetimestamp=df.format(dt);
		 */
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+ timestamp + ".html";
		spartReporter =new ExtentSparkReporter(".\\reports\\" +repName);// specify location of the report
		
		spartReporter.config().setDocumentTitle("opencart Automation Report");
		spartReporter.config().setReportName("opencart Functional Testing");
		spartReporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(spartReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module", "Customers" );
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=textcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=textcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups= textcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}	
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); // to display classname in report
		test.assignCategory(result.getMethod().getGroups());  //to display groups in report
		test.log(Status.PASS, result.getName()+"gotsuccessfully executed");
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.FAIL, result.getThrowable().getMessage()); //to display error message
		
		try 
		{
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testcontext)
	{
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentreport=new File(pathOfExtentReport);
		
		try 
		{
		Desktop.getDesktop().browse(extentreport.toURI());
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	/*
	 try
	 {
	URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	ImageHtmlEmail email=new ImageHtmlEmail();
	email.setDataSourceResolver(new DataSourceUrlResolver(url));
	email.setHostName("stmp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("nandinig@gmaul.com", "password"));
	email.setSSLOnConnect(true);
	email.setFrom("nandini@gmail.com");//sender
	email.setSubject("TestResults");
	email.setMsg("Please find attached reports");//email body
	email.addTo("abc@gmail.com");//receiver
	email.attach(url, "extent report", "please check report");
	email.send();// send an email
    }
    catch(Exception e)
    {
	e.printStackTrace();
    }
	 */
	

	
	
	

}
