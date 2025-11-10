package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstName;
	
	@FindBy(id="input-lastname")
	WebElement lastName;
	
	@FindBy(id="input-email")
	WebElement userEmail;
	
	@FindBy(id="input-password")
	WebElement userPassword;
	
	@FindBy(name="agree")
	WebElement terms;

	@FindBy(css=".btn.btn-primary")
	WebElement registerUser;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMessage;
	
	public void setFirstName(String userName)
	{
		firstName.sendKeys(userName);
	}
	
	public void setLastName(String userLastName)
	{
		lastName.sendKeys(userLastName);
	}
	
	public void setUserEmail(String email)
	{
		userEmail.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		userPassword.sendKeys(password);
	}
	
	public void agreeTerms()
	{
		terms.click();
	}
	
	public void registerUser()
	{
		registerUser.submit();
	}
	
	public String getConfirmationMessage()
	{
		try {
			return (confirmationMessage.getText());
		} catch (Exception e)
		{
			return (e.getMessage());
		}
	}
	
	
}

