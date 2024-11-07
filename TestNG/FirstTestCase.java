package TestNG;

import org.testng.annotations.Test;

/*
  1. Open app
  2. Login
  3. Logout
 */
public class FirstTestCase {
	
	@Test(priority=1)
	void openapp()
	{
		System.out.println("opening application..");
	}
	@Test(priority=2)
	void login()
	{
		System.out.println("Login to application..");

	}
	@Test(priority=3)
	void logut()
	{
		System.out.println("Logout from application..");

	}
	

}
