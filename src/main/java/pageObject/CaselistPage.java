package pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.TestBase;

public class CaselistPage extends TestBase{

	public WebDriver driver;
	public WebDriverWait wait;
	public DashboardPage dp;
	public CaseDetailPage cd;
	public Properties prop;
	
	@FindBy(xpath = "//input[@id='casename']")
	public WebElement casevalue;
	
	@FindBy(xpath = "//button[text()='Create']")
	public WebElement Casecreatebutton;
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[2]")
	public WebElement createdcase;
	
	@FindBy(xpath = "//div[@class='flex-grow-1 h-inherit'] /h6/b[1]")
	public WebElement caselistcasename;
	
	@FindBy(xpath = "//div[@class='flex-grow-1 h-inherit'] /h6/b[2]")
	public WebElement caseloancount;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement caselistsearch;
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[6] /span")
	public WebElement priority;
	
	@FindBy(xpath = "//button[text()=' Upload Local File']")
	public WebElement uploadlocalfile;
	
	@FindBy(xpath = "//div[@class='react-fine-uploader-file-input-container react-fine-uploader-gallery-file-input-container']")
	public WebElement selectfile;
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[4] /span")
	public WebElement processclassname;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[5]")
	public WebElement loan;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[7] /span")
	public WebElement uploaded;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[8] /span")
	public WebElement Inqueue;

	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[9] /span")
	public WebElement classification;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[10] /span")
	public WebElement extraction;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[11] /span")
	public WebElement qa;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[12] /span")
	public WebElement completed;
	
	@FindBy(xpath ="//input[@class='pl-4 mt-3 float-right pr-4 ml-2 btn btn-primary']")
	public WebElement submit;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed'] /tbody /tr[1] /td[1]")
	public WebElement caselist_filename;
	
	@FindBy(xpath ="//div[@class='flex-grow-1 h-inherit']/h6/a")
	public WebElement backtocaselist;
	
	@FindBy(xpath ="//*[@id='process']")
	public WebElement caselistproces;
	
	@FindBy(xpath ="//button[@class='btn btn-primary']")
	public WebElement popup_process;
	
	@FindBy(xpath ="//tbody//tr[1]//button[2]")
	public WebElement delete_button;
	
	@FindBy(xpath ="//button[@class='btn btn-danger']")
	public WebElement delete_popup;
	
	@FindBy(xpath ="//input[@id='search-bar-0']")
	public WebElement searchpopup;
	
	@FindBy(xpath ="//td[@class='selection-cell'] /input[@type='checkbox']")
	public WebElement popup_checkbox;
	
	@FindBy(xpath ="//button[text()='Upload']")
	public WebElement minio_upload;
	
	@FindBy(xpath ="//table[@class='table table-striped table-hover table-condensed']/tbody/tr/td/div")
	public WebElement no_datafound;
	
	@FindBy(xpath ="//div[@class='file-upload']/div/div/input")
	public WebElement inputFile;
	
	
	public CaselistPage(WebDriver driver) throws IOException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);

	}
	
	public String getselectLocalfile() throws InterruptedException, IOException {
		wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(selectfile));
		//selectfile.click();
		//Thread.sleep(3000);
		//Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\FileUploadnew.exe");
		Thread.sleep(3000);
		inputFile.sendKeys(prop.getProperty("Externalfilepath"));
		submit.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(caselist_filename));
		String filenameString=	caselist_filename.getText();
		return filenameString;
	}
	
	public String getselectMiniofile() throws InterruptedException, IOException {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(searchpopup));
		searchpopup.sendKeys(prop.getProperty("Filename"));		
		Thread.sleep(3000);
		popup_checkbox.click();
		minio_upload.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(caselist_filename));
		String filenameString=caselist_filename.getText();
		return filenameString;
	}
	
	public String getloancount() {

		String[] loansplit1 = caseloancount.getText().split("\\(");
		String loantrim1 = loansplit1[1].trim();
		String[] loansplit2 = loantrim1.split("\\s+");
		String loantrim2 = loansplit2[0].trim();
		return loantrim2;
	}


	public String splitcreatedcase() {
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(createdcase));
		String[] createcasename = createdcase.getText().split("U");
		String formatedname = createcasename[0].trim();
		return formatedname;
	}

	public String withoutsplitcreatedcase() {
		String createcasename = createdcase.getText();
		return createcasename;
	}

	public void createcasebutton() throws InterruptedException, IOException {
		dp = new DashboardPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(dp.caselist));
		Thread.sleep(3000);
		dp.caselist.click();
		dp.button.click();
	}

	public String userCreateCase() throws InterruptedException, IOException {
		createcasebutton();
		casevalue.clear();
		String userenter_casename="kartikautomation"+timestamp();
		casevalue.sendKeys(userenter_casename);
		Casecreatebutton.click();
		return userenter_casename;
	}

	public String defaultCreateCase() throws InterruptedException, IOException {
		createcasebutton();
		String casename = casevalue.getAttribute("value");
		Casecreatebutton.click();
		return casename;
	}

	public String createCaseforProcess() throws InterruptedException, IOException {
		createcasebutton();
		Casecreatebutton.click();
		Thread.sleep(3000);
		String createdcasename = createdcase.getText();
		createdcase.click();
		return createdcasename;
	}

	public String createCaselistforsearch() throws InterruptedException, IOException {
		createcasebutton();
		Casecreatebutton.click();
		Thread.sleep(3000);
		String createdcasename = createdcase.getText();
		caselistsearch.sendKeys(createdcasename);
		return createdcasename;
	}
	
	public void deleteCaseFromCaseList() throws InterruptedException {
		delete_button.click();
		Thread.sleep(3000);
		delete_popup.click();
		String nodata=no_datafound.getText();
		Assert.assertEquals("No data found!!!", nodata);
	}

	public String selectPriority() {
		priority.click();
		WebElement staticdropdown = driver.findElement(By.id("priority"));
		org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(staticdropdown);
		dropdown.selectByValue(prop.getProperty("Priority"));
		String getpriority = priority.getText();
		return getpriority;
	}
	
	public void fileProcess() throws InterruptedException, IOException {
		wait = new WebDriverWait(driver, 10);
		cd=new CaseDetailPage(driver);
		caselistproces.click();
		cd.processpopup_dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(cd.selectprocessclass));
		cd.selectprocessclass.sendKeys(prop.getProperty("ProcessingClass"),Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(popup_process));
		popup_process.click();	
		
	}
	
	public void defautcaselistcheck() throws InterruptedException, IOException {
		
		String createdcasename = defaultCreateCase();
		String processingclassname =processclassname.getText();
		Assert.assertEquals(processingclassname, "-");
		String getloan = loan.getText();
		Assert.assertEquals(getloan, "-");
		String getpriority =priority.getText();
		Assert.assertEquals(getpriority, "5");
		String getuploaded = uploaded.getText();
		Assert.assertEquals(getuploaded, "-");
		String getInqueue = Inqueue.getText();
		Assert.assertEquals(getInqueue, "-");
		String getclassification =classification.getText();
		Assert.assertEquals(getclassification, "-");
		String getextraction = extraction.getText();
		Assert.assertEquals(getextraction, "-");
		String getqa =qa.getText();
		Assert.assertEquals(getqa, "-");
		String getcompleted =completed.getText();
		Assert.assertEquals(getcompleted, "-");
	}
	
}
