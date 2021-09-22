package Testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.TestBase;

import pageObject.ClassificationPage;

import pageObject.LoginPage;

public class ClassificationTest extends TestBase {

	public String caseName;
	public LoginPage lp;
	public ClassificationPage cf;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeClass
	// Initialize Driver
	public void intialization() throws IOException {
		driver = intialize();
	}

	@BeforeClass
	// Login
	public void login() throws InterruptedException, IOException {
		lp = new LoginPage(driver);
		lp.email.sendKeys(prop.getProperty("Email"));
		lp.password.sendKeys(prop.getProperty("Password"));
		lp.login.click();
	}

	@Test(priority = 1)
	// Validate Split Functionality
	public void splitClassification() throws InterruptedException, IOException {
		cf = new ClassificationPage(driver);
		caseName = cf.caseProcess();
		cf.splitProcess(caseName);
	}

	/*@Test(priority = 2)
	// Validate Merge Functionality
	public void mergeClassification() throws InterruptedException, IOException {
		cf = new ClassificationPage(driver);
		caseName = cf.caseProcess();
		cf.mergeProcess(caseName);
	}

	@Test(priority = 3)
	// Validate AssignDocType Functionality
	public void assignClassification() throws InterruptedException, IOException {
		cf = new ClassificationPage(driver);
		caseName = cf.caseProcess();
		cf.assignProcess(caseName);
	}*/

	@AfterClass
	// Close the Browser
	public void tearDown() {

		driver.close();
	}

}
