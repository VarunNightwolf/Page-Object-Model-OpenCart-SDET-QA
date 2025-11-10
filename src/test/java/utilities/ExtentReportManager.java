package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.List;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;



	public void onStart(ITestContext testContext) {

		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		// Date dt = new Date();
		// String currentdatetimestamp = dateFormat.format(dt);
		// the below is a representation of the above



		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName); // specify location of the report
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of the report
		sparkReporter.config().setReportName("opencart Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String browser = ((ITestContext) testContext).getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os); 

		List<String> includedGroups = (List<String>) testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.PASS, result.getName() + " got successfully executed");

	}


	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " has failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}

	}


	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());


	}



	public void onFinish(ITestContext context) 
	{
		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") +"\\reports\\" +reportName;
		File extentReport = new File(pathOfExtentReport);

		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	/*	try {
			
			URL url = new URL("file:///" + System.getProperty("user.dir")+"\\reports\\" +reportName);
			
			// Create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.google.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("minato.namikaze@gmail.com","Minato123!"));
			email.setSSLOnConnect(true);
			email.setFrom("minato.namikaze@gmail.com"); // Sender
			email.setSubject("Test Results");
			email.setMsg("Please find Attached Report");
			email.addTo("IceFrog@gmail.com"); // Receiver
			email.attach(url, "extent Report", "please check report....");
			email.send(); // send the email
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
*/
		}

	}






