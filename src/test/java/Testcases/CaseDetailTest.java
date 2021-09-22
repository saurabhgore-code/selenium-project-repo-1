package Testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.TestBase;
import pageObject.CaseDetailPage;
import pageObject.CaselistPage;
import pageObject.DashboardPage;
import pageObject.LoginPage;

public class CaseDetailTest extends TestBase {

	public WebDriver driver;
	public LoginPage lp;
	public CaselistPage cp;
	public CaseDetailPage cd;
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
	// After create case,casename and loancount verify
	public void caseDetail() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaseforProcess();
		String under_caselist_casename = cp.caselistcasename.getText();
		Assert.assertEquals(createdcasename, under_caselist_casename);

		String loancount = cp.getloancount();
		Assert.assertEquals(loancount, "0");

	}

	@Test
	// verify all tabs like uploaded,inprogress,inreview,completed and failed
	public void caseDetail_allTabs() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaseforProcess();
		String under_caselist_casename = cp.caselistcasename.getText();
		Assert.assertEquals(createdcasename, under_caselist_casename);
		cd = new CaseDetailPage(driver);
		cd.uploaded_tab();
		cd.inProgress_tab();
		cd.inReview_tab();
		cd.completed_tab();
		cd.failed_tab();
	}

	@Test
	// verify Uploadlocalfile process through case detail page
	public void caseDetail_localCaseProcess() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaseforProcess();
		String under_caselist_casename = cp.caselistcasename.getText();
		Assert.assertEquals(createdcasename, under_caselist_casename);
		cd = new CaseDetailPage(driver);
		cd.caseDetail_localFileProcess();
	}

	@Test
	// verify Uploadminiofile process through case detail page
	public void caseDetail_minioCaseProcess() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		String createdcasename = cp.createCaseforProcess();
		String under_caselist_casename =cp.caselistcasename.getText();
		Assert.assertEquals(createdcasename, under_caselist_casename);
		cd = new CaseDetailPage(driver);
		cd.caseDetail_minioFileProcess();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
