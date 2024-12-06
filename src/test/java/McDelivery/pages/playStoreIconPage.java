package McDelivery.pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class playStoreIconPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;



    By playStoreIcon = By.xpath("//button[@class='app-btn version-download-app']");
    By install = By.xpath("//span[normalize-space()='Install']");

    public playStoreIconPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean isDownloadVisible(){
        WebElement playStoreIconElement = driver.findElement(playStoreIcon);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", playStoreIconElement);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(playStoreIcon)).isDisplayed();
    }

    public void clickDownload() throws InterruptedException {
        WebElement playStoreIconElement = driver.findElement(playStoreIcon);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", playStoreIconElement);
        Thread.sleep(3000);
        System.out.println("scrolled");
        playStoreIconElement.click();
        System.out.println("download clicked");
    }
    public String isInstallVisible(){
        WebElement installElement = wait.until(ExpectedConditions.visibilityOfElementLocated(install));
        String installText = installElement.getText();
        System.out.println(installText);
        return installText;
    }
    public @Nullable String getMainUrl(){
        return driver.getCurrentUrl();
    }

    public String windowSwitch(){
        String mainUrl = driver.getCurrentUrl();
        String mainWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();
        for(String i : allWindowHandles) {
            if (!i.equals(mainWindowHandle)) {
                driver.switchTo().window(i);
                String currentUrl = driver.getCurrentUrl();
                driver.close();
                driver.switchTo().window(mainWindowHandle);
                return currentUrl;

            }
        }
        return mainUrl;
    }


}
