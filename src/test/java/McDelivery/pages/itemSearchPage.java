package McDelivery.pages;

import McDelivery.Base.baseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;


public class itemSearchPage extends baseClass{

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;





    By itemSearchBar = By.xpath("//div[@class='input-container']");
    By itemSearchBar1 = By.xpath("/html/body/app-root/ion-app/ion-content/ion-router-outlet/app-search/ion-content/div[1]/div[2]/div/form/div/input");
    By searchResults = By.xpath("//div[@class='desktop-view-menus']");
    By searchResultFirstItem = By.xpath("//*[@id=\"659\"]/div");


    public itemSearchPage(WebDriver driver)  {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSearchBarVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar)).isDisplayed();
    }
    public void searchItem1(String itemName) throws InterruptedException {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar));
        searchInput.click();
        Thread.sleep(3000);
        WebElement searchInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar1));
        searchInput1.click();
        searchInput1.clear();
        searchInput1.sendKeys(itemName);
    }

    public void searchItem(String itemName) throws InterruptedException {
        //WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar));
        // searchInput.click();
        WebElement searchInput1 = wait.until(ExpectedConditions.elementToBeClickable(itemSearchBar1));
        searchInput1.click();
        searchInput1.clear();
        //searchInput1.sendKeys(itemName);
        searchInput1.sendKeys(itemName);
    }
    public void upperLower(String itemName){
        WebElement searchInput1 = wait.until(ExpectedConditions.elementToBeClickable(itemSearchBar1));
        searchInput1.clear();
        searchInput1.sendKeys(itemName);

    }

    public List<WebElement> getSearchResults() {
        //WebElement searchResultsElementTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(SearchItemResultTitle));
        //js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchResultsElementTitle);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResults));
    }


    public List<String> ResultTitles(String searchTerm) throws InterruptedException {
        WebElement searchResultFirstItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultFirstItem));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchResultFirstItemElement);

        By resultTitleXPath = By.xpath("//div[contains(@class, 'menu__title-bar')]/h4[contains(@class, 'menu__title')]");
        List<WebElement> titleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultTitleXPath));

        List<String> titles = new ArrayList<>();
        for (WebElement element : titleElements) {
            String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", element);

            if (text != null) {
                text = text.trim();
                if (!text.isEmpty()) {
                    titles.add(text);
                } else {
                    System.out.println("Empty text found for element: " + element.getAttribute("outerHTML"));
                }
            } else {
                System.out.println("Null text found for element: " + element.getAttribute("outerHTML"));
            }
        }

        System.out.println("Number of valid titles found: " + titles.size());
        for (String title : titles) {
            System.out.println("Valid title: " + title);
        }

        return titles;
    }



    public WebElement clickItemSearchBar() throws InterruptedException {
        WebElement itemSearchBarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar));
        itemSearchBarButton.click();
        System.out.println("Clicked first search");

        WebElement itemSearchBarButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar1));
        Thread.sleep(3000);
        itemSearchBarButton1.click();

        return itemSearchBarButton1;
    }



}
