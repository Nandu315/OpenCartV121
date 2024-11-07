package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*
  Data is valid--login success-->test pass-logout
  Data is valid--login falied-->test fail
  
  Data is invalid --login success-->test fail-logout
  Data is invalid--login failed-->test passed
 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") // as the dataprovider present in different class need to mention dataproviderclass parameter
	public void verify_loginDDT(String email, String password,String expRes) throws InterruptedException
	{
		
		logger.info("******* starting TC003_LoginDDT*********");
		//HomePage
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage mc=new MyAccountPage(driver);
		boolean targetpage=mc.ismyAccountPageExists();
		
		if(expRes.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				mc.clicklogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(expRes.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				mc.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(2000);
		logger.info("******* Finished TC003_LoginDDT*********");

	}

}
