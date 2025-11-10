package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement userName;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement loginUser;
	
	public void setUserName(String user)
	{
		userName.sendKeys(user);
	}
	
	public void setPassword(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void loginUser()
	{
		loginUser.click();
	}

}
