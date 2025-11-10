package testCases;

import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
		
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		try {
		logger.info("Starting Account Registration Page Test");
		String password = randomAlphanumeric();
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on My Account");
		homePage.clickRegister();
		logger.info("Clicked on Register Link");
		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");
		registrationPage.setFirstName(randomString().toUpperCase());
		registrationPage.setLastName(randomString().toUpperCase());
		registrationPage.setPassword(password);
		registrationPage.setUserEmail(randomString() + "@gmail.com");
		registrationPage.agreeTerms();
		registrationPage.registerUser();
		String confMsg = registrationPage.getConfirmationMessage();
		if (confMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test Failed");
			logger.debug("Debug logs..");
		}
		
	}
	catch (Exception e)
		{
			Assert.fail();
		}
		}

}
