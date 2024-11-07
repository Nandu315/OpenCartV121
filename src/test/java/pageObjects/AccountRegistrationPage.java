package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlasttname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']")WebElement txtpassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmpassword;
	@FindBy(xpath="//input[@value='0']") WebElement subscribe;
	@FindBy(xpath="//input[@name='agree']") WebElement checkpolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgconfirmation;
	
	public void setFirstName(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		txtlasttname.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void setTelephone(String tele)
	{
		txtTelephone.sendKeys(tele);
	}
	public void setPassword(String pswd)
	{
		txtpassword.sendKeys(pswd);
	}
	public void setconfirmPassword(String pswd)
	{
		txtconfirmpassword.sendKeys(pswd);
	}
	public void clicksubscibe()
	{
		subscribe.click();
	}
	public void clickcheckpolicy()
	{
		checkpolicy.click();
	}
	public void clickbtn()
	{
		btncontinue.click();
//		//sol2
//		btncontinue.submit();
//		
//		//sol3Exc
//		Actions act=new Actions(driver);
//		act.moveToElement(btncontinue).click().perform();
//		
//		//sol4
//		JavascriptExecutor js= (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();",btncontinue);
//		
//		//sol5
//		WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		mywait.until(ExpectedConditions.elementToBeClickable(btncontinue)).click();
	}
	
	public String getConfirmation()
	{
		try
		{
			return (msgconfirmation.getText());
			
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}

	

	

}
