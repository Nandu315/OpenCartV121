package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression", "Master"})
	void verify_account_registration()
	{
		
		logger.info("******* Starting TC001_AccountRegistrationTest ********");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("clicked on myaccount");

		hp.clickRegister();
		logger.info(" clicked on Resister Link ");

		
		logger.info(" providing customer details ");

		AccountRegistrationPage ac=new AccountRegistrationPage(driver);
		ac.setFirstName(randomString().toUpperCase());
		ac.setLastName(randomString().toUpperCase());
		ac.setEmail(randomString()+"@gmail.com");
		ac.setTelephone(randomNumber());
		String password=randomAlphanumeric();
		ac.setPassword(password);
		ac.setconfirmPassword(password);
		ac.clicksubscibe();
		ac.clickcheckpolicy();
		ac.clickbtn();
		
		logger.info("Validating expected message ");

		String confmsg=ac.getConfirmation();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
	}
	
	

}
