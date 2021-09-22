package Testcases;


import java.io.IOException;

import org.apache.bcel.generic.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.*;

import config.TestBase;
import lombok.Value;
import pageObject.CaselistPage;
import pageObject.DashboardPage;
import pageObject.LoginPage;

public class CaselistTest extends TestBase {

	public WebDriver driver;
	public LoginPage lp;
	public DashboardPage dp;
	public CaselistPage cp;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeMethod
	 // Initialize Driver
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
	// create case using default casename
	public void createCase_defaultCaseName() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String casename = cp.defaultCreateCase();
		Thread.sleep(3000);
		String createdcasename = cp.splitcreatedcase();
		Assert.assertEquals(casename, createdcasename);

	}

	@Test
	// create case using userenter casename
	public void createCase_userenterCaseName() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String userentercasename=cp.userCreateCase();
		Thread.sleep(3000);
		String createdcasename = cp.createdcase.getText();
		Assert.assertEquals(userentercasename,createdcasename);

	}

	@Test
	// caseprocess through the caselist page
	public void caseProcess_caseList() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaseforProcess();
		cp.uploadlocalfile.click();
		String filenameString=cp.getselectLocalfile();
		Assert.assertEquals(filenameString, prop.getProperty("Filename"));
		cp.backtocaselist.click();
		Thread.sleep(3000);
		cp.caselistsearch.sendKeys(createdcasename);
		cp.fileProcess();
		Thread.sleep(5000);
		String processingclassname =cp.processclassname.getText();
		Assert.assertEquals(processingclassname,prop.getProperty("ProcessingClass"));
	}

	@Test
	// without upload a loan file deafult status of caselist page
	public void detailCheck_caseList() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		cp.defautcaselistcheck();
	}

	@Test
	// case search in caselist page
	public void search_caseList() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaselistforsearch();
		String casecreatenameString = cp.withoutsplitcreatedcase();
		Assert.assertEquals(casecreatenameString, createdcasename);
	}

	@Test
	// case priority change
	public void changePriority_caseList() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaselistforsearch();
		String priority = cp.selectPriority();
		Assert.assertEquals(priority, prop.getProperty("Priority"));
	}

	@Test
	//delete case from caselist
	public void deleteCase_caseList() throws InterruptedException, IOException{
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaselistforsearch();
		Thread.sleep(3000);
		cp.deleteCaseFromCaseList();
	}
	
	@AfterMethod
	public void tearDown() {

		driver.close();
	}

}
