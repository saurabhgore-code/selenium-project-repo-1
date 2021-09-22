package pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.bouncycastle.jcajce.provider.symmetric.Serpent.TECB;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.TestBase;

public class CaseDetailPage extends TestBase {

	public WebDriver driver;
	public WebDriverWait wait;
	public DashboardPage dp;
	public CaselistPage cp;
	public Properties prop;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[6]")
	public WebElement up_uploadedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[7]")
	public WebElement up_modifiedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[8]")
	public WebElement up_completed;

	@FindBy(xpath = "//a[text()='In Progess']")
	public WebElement inprogress;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[8]")
	public WebElement ip_submitedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[9]")
	public WebElement ip_modifiedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[10]")
	public WebElement ip_completed;

	@FindBy(xpath = "//a[text()='In Review']")
	public WebElement inreview;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[6]")
	public WebElement ir_submitedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[7]")
	public WebElement ir_status;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[8]")
	public WebElement ir_Action;

	@FindBy(xpath = "//a[text()='Completed']")
	public WebElement completed;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[10]")
	public WebElement cm_finishedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[11]")
	public WebElement cm_Action;

	@FindBy(xpath = "//button[@class='pl-3 pr-3 ml-2 btn btn-primary btn-sm disabled']")
	public WebElement cm_Movedata;

	@FindBy(xpath = "//a[text()='Failed']")
	public WebElement failed;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[6]")
	public WebElement fl_submittedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[7]")
	public WebElement fl_finishedon;

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /thead /tr[1] /th[8]")
	public WebElement fl_completed;

	@FindBy(xpath = "//div[@class=' css-2b097c-container'] /div[1] /div[2] /div")
	public WebElement processpopup_dropdown;

	@FindBy(xpath = "//div[@class=' css-2b097c-container'] /div /div[1] /div[2] /div /input")
	public WebElement selectprocessclass;
	
	@FindBy(xpath = "//button[@class='pl-3 pr-3 ml-2 minio-btn btn btn-primary btn-sm']")
	public WebElement uploadfromminio;

	public CaseDetailPage(WebDriver driver) throws IOException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);
	}

	public void uploaded_tab() {

		String uploadedon = up_uploadedon.getText();
		Assert.assertEquals(uploadedon, "Uploaded On");
		String modifiedon = up_modifiedon.getText();
		Assert.assertEquals(modifiedon, "Modified On");
		String completed = up_completed.getText();
		Assert.assertEquals(completed, "Completed");

	}

	public void inProgress_tab() {

		inprogress.click();
		String submitedon = ip_submitedon.getText();
		Assert.assertEquals(submitedon, "Submitted On");
		String modifiedon = ip_modifiedon.getText();
		Assert.assertEquals(modifiedon, "Modified On");
		String completed = ip_completed.getText();
		Assert.assertEquals(completed, "Completed");

	}

	public void inReview_tab() {

		inreview.click();
		String submitedon = ir_submitedon.getText();
		Assert.assertEquals(submitedon, "Submitted On");
		String status = ir_status.getText();
		Assert.assertEquals(status, "Status");
		String Action = ir_Action.getText();
		Assert.assertEquals(Action, "Action");

	}

	public void completed_tab() {

		completed.click();
		String finishedon = cm_finishedon.getText();
		Assert.assertEquals(finishedon, "Finished On");
		String Action = cm_Action.getText();
		Assert.assertEquals(Action, "Action");
		Assert.assertFalse(cm_Movedata.isEnabled());

	}

	public void failed_tab() {

		failed.click();
		String submittedon = fl_submittedon.getText();
		Assert.assertEquals(submittedon, "Submitted On");
		String finishedon = fl_finishedon.getText();
		Assert.assertEquals(finishedon, "Finished On");
		String completed = fl_completed.getText();
		Assert.assertEquals(completed, "Completed");

	}

	public void caseDetail_localFileProcess() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		wait = new WebDriverWait(driver, 10);
		String filename=prop.getProperty("Filename");
		String processclassname=prop.getProperty("ProcessingClass");
		cp.uploadlocalfile.click();
		String filenameString = cp.getselectLocalfile();
		Assert.assertEquals(filenameString, filename);
		String loancount = cp.getloancount();
		Assert.assertEquals(loancount, "1");
		cp.caselistproces.click();
		processpopup_dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(selectprocessclass));
		selectprocessclass.sendKeys(processclassname, Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(cp.popup_process));
		cp.popup_process.click();
		String under_caselist_casename = cp.caselistcasename.getText();
		driver.navigate().to("http://evolve.47billion.com/admin/dashboard/cases");
		wait.until(ExpectedConditions.visibilityOf(cp.processclassname));
		cp.caselistsearch.sendKeys(under_caselist_casename);
		String processingclassname = cp.processclassname.getText();
		Assert.assertEquals(processingclassname, processclassname);
	}

	public void caseDetail_minioFileProcess() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		wait = new WebDriverWait(driver, 10);
		uploadfromminio.click();
		String filenameString = cp.getselectMiniofile();
		Assert.assertEquals(filenameString,prop.getProperty("Filename"));
		String loancount = cp.getloancount();
		Assert.assertEquals(loancount, "1");
		cp.caselistproces.click();
		processpopup_dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(selectprocessclass));
		selectprocessclass.sendKeys(prop.getProperty("ProcessingClass"), Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(cp.popup_process));
		cp.popup_process.click();
		String under_caselist_casename = cp.caselistcasename.getText();
		driver.navigate().to("http://evolve.47billion.com/admin/dashboard/cases");
		wait.until(ExpectedConditions.visibilityOf(cp.processclassname));
		cp.caselistsearch.sendKeys(under_caselist_casename);
		String processingclassname = cp.processclassname.getText();
		Assert.assertEquals(processingclassname, prop.getProperty("ProcessingClass"));
	}

}
