package Testcases;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import config.TestBase;
import pageObject.LoginPage;
import utilities.ExtentReportNG;
import utilities.Movefile;

public class loginPageTest extends TestBase {

	public WebDriver driver;
	public Movefile mf;
	public String Url;
	public LoginPage lp;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeClass
	//Initialize Driver
	public void intialization() throws IOException {
		mf = new Movefile();
		mf.baseMoveFile();
		log.info("screenshot is moved to backup folder");
		driver = intialize();

	}

	@Test
	// login
	public void login() throws InterruptedException, IOException {
		lp = new LoginPage(driver);
		lp.email.sendKeys(prop.getProperty("Email"));
		lp.password.sendKeys(prop.getProperty("Password"));
		lp.login.click();
	}

	@Test
	// Validate login
	public void loginUserValidate() throws InterruptedException, IOException {
		lp = new LoginPage(driver);
		String username = lp.verifyUsername();
		Assert.assertEquals(prop.getProperty("Email"), username);
	}

	@Test
	// validate title
	public void loginPageTitle() throws IOException {
		lp = new LoginPage(driver);
		String title = lp.validateLoginPageTitle();
		Assert.assertEquals("Pontingfish", title);
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}

}
