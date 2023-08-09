package test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.Util.Constants;
import main.java.Util.Screenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeTest
    public void beforeTestMethode() {
        System.out.println("We are in before test methode");
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/TestNGExtentReport.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Selenium testNG");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Soumya");
        spark.config().setDocumentTitle("TestNG report");
        // Name of the report
        spark.config().setReportName("TestNG report");
        spark.config().setTheme(Theme.DARK);
    }

    @AfterTest
    public void afterTestMethode() {
        System.out.println("We are in after test methode");
        extent.flush();
    }

    @BeforeMethod
    @Parameters(value = {"browserName"})
    public void beforeMethode(String browserName) {
        System.out.println("We are in before methode");
        setup(browserName);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void afterMethode(ITestResult result) throws IOException {
        System.out.println("We are in after  methode");
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
           // result.setAttribute("Screenshotpath", System.getProperty("user.dir") + File.separator +"ScreenShots" +File.separator+ result.getMethod().getMethodName() + ".png");
            String screenshotPath = Screenshot.getScreenShot(driver, result.getName());
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }
        driver.quit();
    }

    public void setup(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("Please select appropriate  browser ");
        }
    }
}
