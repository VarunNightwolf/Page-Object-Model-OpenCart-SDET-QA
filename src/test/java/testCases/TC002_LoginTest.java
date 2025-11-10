package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void loginUser()
	{
		logger.info("Starting TC_002_LoginTest");
		
		try {
		//   Home Page
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		// Login Page
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(prop.getProperty("username"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.loginUser();
		
		// My Account
		MyAccountPage accountPage = new MyAccountPage(driver);
		boolean TargetPage = accountPage.isMyAccountPageExists();
		Assert.assertEquals(TargetPage, true, "Login failed");
		Assert.assertTrue(TargetPage);
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished TC_002_LoginTest");
		
	}
}
