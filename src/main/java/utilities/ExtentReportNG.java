package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.TestBase;

public class ExtentReportNG extends TestBase {
	public static ExtentReports extents;

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\" + timestamp() + "_" + "TestReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().thumbnailForBase64(true);
		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("Web Automation Result");
		extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester", "Kartik Goyal");
		return extents;

	}

}
