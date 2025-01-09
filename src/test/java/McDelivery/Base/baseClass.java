package McDelivery.Base;

import McDelivery.pages.customizationPage;
import McDelivery.pages.itemSearchPage;
import McDelivery.pages.playStoreIconPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class baseClass {
    protected WebDriver driver;
    JavascriptExecutor js;
    protected WebDriverWait wait;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected itemSearchPage searchPageObj;



    public WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }




    @BeforeClass
    public void setup() throws IOException {

        String reportName = this.getClass().getSimpleName() + ".html"; // Report file name based on class
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("McDelivery Test Report - " + this.getClass().getSimpleName());
        spark.config().setDocumentTitle("Automation Test Report");

        extent.attachReporter(spark);



    }

    @BeforeMethod
    @Parameters({"URL"})
    public void initialize(@Optional("https://mcdelivery.co.in/") String URL) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        playStoreIconPage playStoreObj = new playStoreIconPage(driver);
        customizationPage customObj = new customizationPage(driver);
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
    @AfterMethod
    public void endTest(ITestResult result, Method method) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "The test is passed");
        } else {
            test.log(Status.SKIP, "The test is skipped");
        }
        driver.quit();
    }
        protected String captureScreenshot(String screenshotName) {
            String projectPath = System.getProperty("user.dir");
            String screenshotPath = projectPath + File.separator + "screenshots" + File.separator + screenshotName + ".jpg";
            try {
                File screenshotsDir = new File(projectPath + File.separator + "screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdirs();
                }
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destination = new File(screenshotPath);
                FileUtils.copyFile(screenshot, destination);
            } catch (IOException e) {
                System.err.println("Error while capturing screenshot: " + e.getMessage());
            }
            return screenshotPath;
        }



    }
