package pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

public class ClassificationPage extends TestBase {

	public WebDriver driver;
	public WebDriverWait wait;
	public DashboardPage dp;
	public CaselistPage cp;
	public CaseDetailPage cd;
	public Properties prop;

	By classification_documentsize = By.xpath("//li[@class='list-group-item'] //div[@role='button']");

	By document_folderSize = By.xpath("//div[@class='item'] //span[@class='badge badge-secondary badge-pill']");

	@FindBy(xpath = "//button[@class='border-0 btn btn-outline-info btn-sm']")
	WebElement refresh;

	@FindBy(xpath = "//div[@class='flexbox-container flexbox-flow-row align-items-center h-inherit flex-grow-1']/h6")
	WebElement assigndocname;

	@FindBy(xpath = "//a[@id='edit']")
	WebElement inreview_edit;

	@FindBy(xpath = "//i[@id='PopoverLegacy']")
	WebElement classification_popup;

	@FindBy(xpath = "//div[@class='popover-body'] /div[1] /span")
	WebElement classification_popup_filename;

	@FindBy(xpath = "//div[@class='popover-body'] /table /tbody /tr[1] /td[4]")
	WebElement classification_popup_casename;

	@FindBy(xpath = "//button[@id='documents']")
	WebElement classification_document;

	@FindBy(xpath = "//div[@class='item'] //span[@class='badge badge-secondary badge-pill']")
	WebElement classification_docno;

	@FindBy(xpath = "//button[@id='nextPage']")
	WebElement classification_nextpage;

	@FindBy(xpath = "//button[text()='Complete']")
	WebElement classification_complete;

	@FindBy(xpath = "//button[@id='split']")
	WebElement classification_split;

	@FindBy(xpath = "//button[text()='+ New Doc']")
	WebElement classification_split_newDoc;

	@FindBy(xpath = "//div[@class='modal-content']/div[2]/div[1]/ul/div[2]/li/div[1]/div[1]/div[1]/div[1]/div[2]")
	WebElement classification_splitPopup_arrow;

	@FindBy(xpath = "//div[@class='modal-content']/div[2]/div[1]/ul/div[2]/li/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div/input")
	WebElement classification_splitTextBoxElement;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement classification_continue;

	@FindBy(xpath = "//button[@id='merge']")
	WebElement classification_merge;

	@FindBy(xpath = "//div[@class='modal-content']/div[2]/div[1]/ul/div[1]/li")
	WebElement classification_merge_select_doc;

	@FindBy(xpath = "//button[text()='Merge']")
	WebElement classification_mergepopup_merge;

	@FindBy(xpath = "//div[@class='top-bar']/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]")
	WebElement classification_assigndoc_arrow;

	@FindBy(xpath = "//div[@class='top-bar']/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/input")
	WebElement classification_assignTextBoxElement;

	@FindBy(xpath = "//button[text()='Assign']")
	WebElement classification_assign;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement save_Button;

	public ClassificationPage(WebDriver driver) throws IOException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);
	}

	public String get_classification_documentsize() {

		int size = driver.findElements(classification_documentsize).size();
		String sizeofpage = String.valueOf(size);
		return sizeofpage;
	}

	public int get_document_folderSize() {

		int size = driver.findElements(document_folderSize).size();
		return size;
	}

	public void split_document() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		classification_split.click();
		classification_split_newDoc.click();
		wait.until(ExpectedConditions.visibilityOf(classification_splitPopup_arrow));
		classification_splitPopup_arrow.click();
		wait.until(ExpectedConditions.visibilityOf(classification_splitTextBoxElement));
		WebElement textbox = classification_splitTextBoxElement;
		textbox.sendKeys(prop.getProperty("Splitdoc"), Keys.ENTER);
		Thread.sleep(3000);
		textbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(classification_continue));
		classification_continue.click();
		Thread.sleep(3000);
	}

	public void merge_document() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		classification_merge.click();
		classification_merge_select_doc.click();
		wait.until(ExpectedConditions.visibilityOf(classification_mergepopup_merge));
		classification_mergepopup_merge.click();
		classification_continue.click();
		Thread.sleep(3000);

	}

	public String assign_document() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		classification_assigndoc_arrow.click();
		wait.until(ExpectedConditions.visibilityOf(classification_assignTextBoxElement));
		WebElement textbox = classification_assignTextBoxElement;
		textbox.sendKeys(prop.getProperty("Assigndoc"), Keys.ENTER);
		classification_assign.click();
		String newassigndocname = assigndocname.getText();
		Assert.assertEquals(newassigndocname, prop.getProperty("Assigndoc"));
		Thread.sleep(3000);
		return newassigndocname;
	}

	public String beforeSplit() {
		classification_document.click();
		int sizebeforesplit = get_document_folderSize();
		int finalsizesplit = sizebeforesplit + 1;
		String sizeofbeforesplit = String.valueOf(finalsizesplit);
		classification_document.click();
		return sizeofbeforesplit;

	}

	public String afterSplit() {
		classification_document.click();
		int sizeaftermerge = get_document_folderSize();
		String sizeofaftermerge = String.valueOf(sizeaftermerge);
		classification_document.click();
		return sizeofaftermerge;

	}

	public String afterMerge() {
		classification_document.click();
		int sizeaftermerge = get_document_folderSize();
		String sizeofaftermerge = String.valueOf(sizeaftermerge);
		classification_document.click();
		return sizeofaftermerge;

	}

	public String beforeMerge() {
		classification_document.click();
		int sizebeforemerge = get_document_folderSize();
		int finalsizemerge = sizebeforemerge - 1;
		String sizeofbeforemerge = String.valueOf(finalsizemerge);
		classification_document.click();
		return sizeofbeforemerge;

	}

	public String caseProcess() throws InterruptedException, IOException {
		cp = new CaselistPage(driver);
		cd = new CaseDetailPage(driver);
		dp = new DashboardPage(driver);
		wait = new WebDriverWait(driver, 10);
		driver.navigate().to(prop.getProperty("RedirectUrl"));
		Thread.sleep(3000);
		dp.caselist.click();
		String createdcasename = cp.createCaseforProcess();
		String under_caselist_casename = cp.caselistcasename.getText();
		Assert.assertEquals(createdcasename, under_caselist_casename);
		String filename = prop.getProperty("Filename");
		String processclassname = prop.getProperty("ProcessingClass");
		cp.uploadlocalfile.click();
		String filenameString = cp.getselectLocalfile();
		Assert.assertEquals(filenameString, filename);
		String loancount = cp.getloancount();
		Assert.assertEquals(loancount, "1");
		cp.caselistproces.click();
		cd.processpopup_dropdown.click();
		wait.until(ExpectedConditions.visibilityOf(cd.selectprocessclass));
		cd.selectprocessclass.sendKeys(processclassname, Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(cp.popup_process));
		cp.popup_process.click();
		return under_caselist_casename;
	}

	public void caseSearchProcess(String createCase) throws IOException, InterruptedException {
		cp = new CaselistPage(driver);
		dp = new DashboardPage(driver);
		cd = new CaseDetailPage(driver);
		wait = new WebDriverWait(driver, 10);
		driver.navigate().to(prop.getProperty("RedirectUrl"));
		Thread.sleep(3000);
		dp.caselist.click();
		wait.until(ExpectedConditions.visibilityOf(cp.caselistsearch));
		cp.caselistsearch.sendKeys(createCase);
		wait.until(ExpectedConditions.visibilityOf(cp.createdcase));
		cp.createdcase.click();
		wait.until(ExpectedConditions.visibilityOf(cd.inreview));
		cd.inreview.click();
		Thread.sleep(3000);
		for (int i = 0; i < 20; i++) {
			refresh.click();
			int result = driver.findElements(By.xpath("//a[@id='edit']")).size();
			if (result != 0) {
				inreview_edit.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOf(classification_popup));
		classification_popup.click();
		wait.until(ExpectedConditions.visibilityOf(classification_popup_filename));
		String class_filename = classification_popup_filename.getText();
		Assert.assertEquals(class_filename, prop.getProperty("Filename"));
		String class_caseName = classification_popup_casename.getText();
		Assert.assertEquals(class_caseName, createCase);
		classification_popup.click();
	}

	public void splitProcess(String createCase) throws InterruptedException, IOException {
		caseSearchProcess(createCase);
		wait.until(ExpectedConditions.visibilityOf(classification_document));
		classification_document.click();
		String sizeofpage = get_classification_documentsize();
		String doc_no = classification_docno.getText();
		Assert.assertEquals(doc_no, sizeofpage);
		wait.until(ExpectedConditions.visibilityOf(classification_document));
		classification_document.click();
		String beforesplit = beforeSplit();
		classification_nextpage.click();
		split_document();
		String aftersplit = afterSplit();
		Assert.assertEquals(aftersplit, beforesplit);
		String saveUrl = driver.getCurrentUrl();
		save_Button.click();
		driver.navigate().to(prop.getProperty("RedirectUrl"));
		Thread.sleep(3000);
		dp.caselist.click();
		wait.until(ExpectedConditions.visibilityOf(cp.caselistsearch));
		cp.caselistsearch.sendKeys(createCase);
		wait.until(ExpectedConditions.visibilityOf(cp.createdcase));
		cp.createdcase.click();
		wait.until(ExpectedConditions.visibilityOf(cd.inreview));
		cd.inreview.click();
		inreview_edit.click();
		wait.until(ExpectedConditions.visibilityOf(classification_popup));
		classification_popup.click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(saveUrl, currentUrl);
		String splitvalue = afterSplit();
		Assert.assertEquals(splitvalue, aftersplit);
		classification_complete.click();
	}

	public void mergeProcess(String createCase) throws InterruptedException, IOException {
		caseSearchProcess(createCase);
		wait.until(ExpectedConditions.visibilityOf(classification_document));
		classification_document.click();
		String sizeofpage = get_classification_documentsize();
		String doc_no = classification_docno.getText();
		Assert.assertEquals(doc_no, sizeofpage);
		wait.until(ExpectedConditions.visibilityOf(classification_document));
		classification_document.click();
		classification_nextpage.click();
		split_document();
		String beforemerge = beforeMerge();
		classification_nextpage.click();
		merge_document();
		String aftermerge = afterMerge();
		Assert.assertEquals(beforemerge, aftermerge);
		String saveUrl = driver.getCurrentUrl();
		save_Button.click();
		driver.navigate().to(prop.getProperty("RedirectUrl"));
		Thread.sleep(3000);
		dp.caselist.click();
		wait.until(ExpectedConditions.visibilityOf(cp.caselistsearch));
		cp.caselistsearch.sendKeys(createCase);
		wait.until(ExpectedConditions.visibilityOf(cp.createdcase));
		cp.createdcase.click();
		wait.until(ExpectedConditions.visibilityOf(cd.inreview));
		cd.inreview.click();
		inreview_edit.click();
		wait.until(ExpectedConditions.visibilityOf(classification_popup));
		classification_popup.click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(saveUrl, currentUrl);
		String mergevalue = afterMerge();
		Assert.assertEquals(mergevalue, aftermerge);
		classification_complete.click();
	}

	public void assignProcess(String createCase) throws InterruptedException, IOException {
		caseSearchProcess(createCase);
		String assignDocName = assign_document();
		String saveUrl = driver.getCurrentUrl();
		save_Button.click();
		driver.navigate().to(prop.getProperty("RedirectUrl"));
		Thread.sleep(3000);
		dp.caselist.click();
		wait.until(ExpectedConditions.visibilityOf(cp.caselistsearch));
		cp.caselistsearch.sendKeys(createCase);
		wait.until(ExpectedConditions.visibilityOf(cp.createdcase));
		cp.createdcase.click();
		wait.until(ExpectedConditions.visibilityOf(cd.inreview));
		cd.inreview.click();
		inreview_edit.click();
		wait.until(ExpectedConditions.visibilityOf(classification_popup));
		classification_popup.click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(saveUrl, currentUrl);
		String docname = assigndocname.getText();
		Assert.assertEquals(docname, assignDocName);
		classification_complete.click();

	}

}
