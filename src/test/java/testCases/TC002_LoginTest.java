package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;


public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("****** Starting login pagetest*******");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));// getting data from properties file
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mc=new MyAccountPage(driver);
		boolean targetpage=mc.ismyAccountPageExists();
		Assert.assertEquals(targetpage, true, "Login failed"); //or Assert.assertTrue(targetpage)
		}
		catch(Exception e)
		{
			Assert.fail();
		}		
		logger.info("****** Finished login pagetest*******");

	}
	
	
	

}
