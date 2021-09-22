package pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	
	public WebDriver driver;
	public WebDriverWait wait ;
	public Properties prop;

	@FindBy(id = "email")
	public WebElement email;
	
	@FindBy(id = "password")
	public	WebElement password;
	
	@FindBy(xpath = "//button[text()='Login']")
	public WebElement login;
	
	@FindBy(id = "UserPopover")
	public WebElement popover;
	
	@FindBy(xpath ="//div[@class='popover-body']/div/h6")
	public WebElement username;
	
	
	public LoginPage(WebDriver driver) throws IOException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);

	}
	
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public String verifyUsername() throws InterruptedException{
		Thread.sleep(5000);
		popover.click();
		wait = new WebDriverWait(driver,3);
		wait.until(ExpectedConditions.visibilityOf(username));
		String usernamePopover = username.getText();	
		return usernamePopover;
	}

}
