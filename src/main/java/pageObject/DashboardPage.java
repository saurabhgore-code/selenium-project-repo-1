package pageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

import org.bouncycastle.asn1.esf.CrlValidatedID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardPage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Properties prop;

	@FindBy(xpath = "//span[text()='Cases']")
	public WebElement casename;

	@FindBy(xpath = "//span[text()='No. of Loans']")
	public WebElement loan;

	@FindBy(xpath = "//span[text()='In Progress']")
	public WebElement inProgress;

	@FindBy(xpath = "//span[text()='Completed']")
	public WebElement completed;

	@FindBy(xpath = "//a[text()='Case List']")
	public WebElement caselist;

	@FindBy(xpath = "//button[text()='Create Case']")
	public WebElement button;

	public DashboardPage(WebDriver driver) throws IOException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);
	}

	public void validate_dashboardPage() throws InterruptedException {

		String caseName = casename.getText();
		Assert.assertEquals(caseName, "Cases");
		String loanname = loan.getText();
		Assert.assertEquals(loanname, "No. of Loans");
		String progress = inProgress.getText();
		Assert.assertEquals(progress, "In Progress");
		String complete = completed.getText();
		Assert.assertEquals(complete, "Completed");
		caselist.click();
		String buttonname = button.getText();
		Assert.assertEquals(buttonname, "Create Case");

	}

}
