package McDelivery.Base;

import McDelivery.pages.customizationPage;
import McDelivery.pages.itemSearchPage;
import McDelivery.pages.playStoreIconPage;
import McDelivery.utils.ExcelUtils;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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

    protected ExcelUtils excelUtils;
    protected String URL;
    protected String searchFoodItemName;
    protected String searchFoodByUPPER;
    protected String searchFoodByLOWER;
    protected String searchFoodByCategory;
    protected String searchFoodByPartial;
    protected String searchFoodByInvalidName;
    protected String searchFoodBySpecialChar;
    protected double Quantity;
    protected String excelFilePath = "C:\\Users\\david.doggala\\IdeaProjects\\McDelivery\\src\\test\\java\\McDelivery\\testData\\testDataMcD.xlsx";

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
        //WebDriverManager.chromedriver().setup();  // Set up WebDriver
        // Excel file path
        excelFilePath = "C:\\Users\\david.doggala\\IdeaProjects\\McDelivery\\src\\test\\java\\McDelivery\\testData\\testDataMcD.xlsx";
        String sheetName = "TEST"; // Specify the sheet name here
        excelUtils = new ExcelUtils(excelFilePath, sheetName);

        // Read data from Excel
        URL = excelUtils.getData(1, 0);
        searchFoodItemName = excelUtils.getData(1, 1);
        searchFoodByUPPER = excelUtils.getData(1,2);
        searchFoodByLOWER = excelUtils.getData(1,3);
        searchFoodByCategory = excelUtils.getData(1,4);
        searchFoodByPartial = excelUtils.getData(1,5);
        searchFoodByInvalidName = excelUtils.getData(1,6);
        Quantity = (int) Double.parseDouble(excelUtils.getData(1, 7));
        searchFoodBySpecialChar = excelUtils.getData(1,8);

        String reportName = this.getClass().getSimpleName() + ".html"; // Report file name based on class
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("McDelivery Test Report - " + this.getClass().getSimpleName());
        spark.config().setDocumentTitle("Automation Test Report");

        extent.attachReporter(spark);



    }

    @BeforeMethod
    public void initialize() throws InterruptedException {
        WebDriverManager.chromedriver().setup();  // Set up WebDriver
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
        // If needed, quit the driver after all tests are executed
        //driver.quit();
        extent.flush();
    }
    @AfterMethod
    public void endTest(ITestResult result, Method method) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log the failure status along with the throwable cause

            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Log the success status
            test.log(Status.PASS, "The test is passed");
        } else {
            // Log the skipped status
            test.log(Status.SKIP, "The test is skipped");
        }

        driver.quit();

    }
        protected String captureScreenshot(String screenshotName) {
            // Relative path to the screenshots folder from project root
            String projectPath = System.getProperty("user.dir");
            String screenshotPath = projectPath + File.separator + "screenshots" + File.separator + screenshotName + ".jpg";

            try {
                // Ensure the screenshots directory exists
                File screenshotsDir = new File(projectPath + File.separator + "screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdirs(); // Create directories if they do not exist
                }

                // Capture the screenshot
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destination = new File(screenshotPath);
                FileUtils.copyFile(screenshot, destination);
            } catch (IOException e) {
                System.err.println("Error while capturing screenshot: " + e.getMessage());
            }

            return screenshotPath; // Return the absolute path for further use
        }


    }
