package Testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.ISOSignatureSpi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.TestBase;
import pageObject.DashboardPage;
import pageObject.LoginPage;

public class DashboardTest extends TestBase {

	public WebDriver driver;
	public String Url;
	public LoginPage lp;
	public DashboardPage dp;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeClass
	// Intialize Driver
	public void intialization() throws IOException {
		driver = intialize();
	}

	@BeforeMethod
	// login
	public void login() throws InterruptedException, IOException {
		lp = new LoginPage(driver);
		lp.email.sendKeys(prop.getProperty("Email"));
		lp.password.sendKeys(prop.getProperty("Password"));
		lp.login.click();
	}

	@Test
	// Validate overview and caselist tab
	public void dashboard_overView() throws InterruptedException, IOException {
		dp = new DashboardPage(driver);
		dp.validate_dashboardPage();
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}

}
