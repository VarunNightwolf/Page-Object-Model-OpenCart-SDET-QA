package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccount;

	@FindBy(xpath="//a[text()='Register']")
	WebElement registration;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement clickLoginPage;


	public void clickMyAccount()
	{
		myAccount.click();
	}

	public void clickRegister()
	{
		registration.click();
	}
	
	public void clickLogin()
	{
		clickLoginPage.click();
	}

}
