package config;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.NEW;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.reporter.configuration.util.IOUtil;

import utilities.ExcelUtils;
import utilities.Movefile;

public class TestBase {
	public static WebDriver driver;
	public Properties prop;

	public WebDriver intialize() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//config//data.properties");
		prop.load(file);

		// jenkins parameter we need to add these two line
		// mvn test -Dbrowser="chrome"
		// String browsername = System.getProperty("browser");

		String browsername = prop.getProperty("Browser");
		String OSname = prop.getProperty("OS");
		String ChromeBrowserpath = prop.getProperty("ChromeBrowserpath");
		String FirefoxBrowserpath = prop.getProperty("FirefoxBrowserpath");
		String EdgeBrowserpath = prop.getProperty("EdgeBrowserpath");

		if (OSname.equals("windows")) {

			if (browsername.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", ChromeBrowserpath);
				// headless mode we need to add these 2 line
				  ChromeOptions options=new ChromeOptions();
				 options.addArguments("--headless");
                                options.addArguments("--no-sandbox");
                                options.addArguments("--disable-dev-shm-usage");
				// options.addArguments("headless");
				 options.addArguments("window-size=1400,600");
				 driver = new ChromeDriver(options);
				 driver.manage().window().maximize();

			}

			else if (browsername.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + FirefoxBrowserpath);
				driver = new FirefoxDriver();
				driver.manage().window().maximize();

			} else if (browsername.equals("edge")) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + EdgeBrowserpath);
				driver = new EdgeDriver();
				driver.manage().window().maximize();

			}

		}

		else if (OSname.equals("linux")) {

			if (browsername.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + ChromeBrowserpath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

			else if (browsername.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + FirefoxBrowserpath);
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}

		}

		else if (OSname.equals("Mac")) {

			if (browsername.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + ChromeBrowserpath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

			else if (browsername.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + FirefoxBrowserpath);
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}


		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;

	}

	public String getscreenshot(String testcasename, WebDriver driver) throws IOException { // Screenshot function

		TakesScreenshot tS = (TakesScreenshot) driver;
		File sourceFile = tS.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + timestamp() + "_" + testcasename
				+ ".png";
		FileUtils.copyFile(sourceFile, new File(destination));

		byte[] image64 = IOUtils.toByteArray(new FileInputStream(destination));
		return Base64.getEncoder().encodeToString(image64);

	}

	public static String timestamp() {

		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());

	}

	public Object[][] Testdata(String excelPath, String sheetname) throws IOException // Data Driven function
	{

		ExcelUtils ex = new ExcelUtils(excelPath, sheetname);
		int rowCount = ex.getRowCount();
		int colCount = ex.getColCount();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {

				String celldata = ex.getCellDataString(i, j);
				data[i - 1][j] = celldata;

			}

		}

		return data;
	}

	@DataProvider // Data provider Driven function
	public Object[][] getdata() throws IOException {
		String excelPath = excelpath();
		String sheetname = excelsheetname();

		Object[][] data = Testdata(System.getProperty("user.dir") + excelPath, sheetname);

		return data;

	}

	public String excelpath() throws IOException { // excelpath function
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\config\\data.properties");
		prop.load(file);
		String excelpath = prop.getProperty("Excelpath");

		return excelpath;

	}

	public String excelsheetname() throws IOException { // excelsheetname function
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\config\\data.properties");
		prop.load(file);
		String sheetname = prop.getProperty("Excelsheetname");

		return sheetname;

	}

}
