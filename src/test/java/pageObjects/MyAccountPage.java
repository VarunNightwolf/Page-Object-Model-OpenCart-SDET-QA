package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//h1[text()='My Account']")
	WebElement msgHeading;

	@FindBy(xpath="//button[text()='Logout']")
	WebElement logOut;

	public boolean isMyAccountPageExists()
	{
		try
		{
			return	msgHeading.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public void logout()
	{
		logOut.click();
	}

}
