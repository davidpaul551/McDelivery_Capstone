package McDelivery.Test;

import McDelivery.Base.baseClass;
import McDelivery.pages.playStoreIconPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.HashSet;

public class playStoreIconTest extends baseClass {



    @Test(priority = 1)
    public void verifyIfDisplayed() {

        test = extent.createTest("verify Search Bar Visibility");
        playStoreIconPage playStoreObj = new playStoreIconPage(driver);
        playStoreObj.isDownloadVisible();
        test.pass("Download now button is visible");

    }

    @Test(priority = 2)
    public void isDownloadClickable() throws InterruptedException {
        test = extent.createTest("verify if download button is clickable");
        playStoreIconPage playStoreObj = new playStoreIconPage(driver);

        playStoreObj.clickDownload();
        Thread.sleep(3000);
        String instext = playStoreObj.isInstallVisible();
        String exptext = "Install";
        Assert.assertEquals(instext, exptext, "No text is available");
        test.pass("Install is displayed");

    }

    @Test(priority = 3)
    public void verifyWindowSwitch() throws InterruptedException {
        test = extent.createTest("verify tab switch");
        playStoreIconPage playStoreObj = new playStoreIconPage(driver);
        String mainUrl = playStoreObj.getMainUrl();
        String mainWindowHandle = driver.getWindowHandle();
        playStoreObj.clickDownload();
        Thread.sleep(3000);



        Set<String> allWindowHandles = driver.getWindowHandles();

        if (allWindowHandles.size() == 1) {
            String screenshotPath = captureScreenshot("verifyTabSwitchFailure");
            System.out.println("No new tab opened, or the action reused the main tab.");
            test.fail("New tab did not open, the main tab was overwritten , Windows opened are :"+allWindowHandles.size()).addScreenCaptureFromPath(screenshotPath);
        } else {
            for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    String newUrl = driver.getCurrentUrl();
                    System.out.println("New tab opened successfully. Current URL: " + newUrl);
                    test.pass("New tab opened with a link to the Play Store.");
                    driver.close();
                    driver.switchTo().window(mainWindowHandle);
                }
            }
        }}
}
