package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DataDrivenTestCase extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class  )
	public void verify_loginDDT(String email, String password, String exp)
	{
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
		
		// Data is Valid and login is successful --> Test passed
		// Data is Valid and login is unsccuessful --> Test failed
		// Data is Invalid and login is successful --> Test failed
		// Data is Invalid and login is unsuccessful --> Test passed

		if (exp.equalsIgnoreCase("Valid"))
			if(TargetPage==true)
			{
				accountPage.logout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}

		if (exp.equalsIgnoreCase("Invalud"))
			if(TargetPage==true)
			{
				accountPage.logout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}


	} 

}
