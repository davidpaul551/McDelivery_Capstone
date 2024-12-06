package McDelivery.pages;

import McDelivery.Base.baseClass;
import McDelivery.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Driver;
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
    //By enterMinimum = By.;


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
        //searchInput1.click();
        searchInput1.clear();
        //searchInput1.sendKeys(itemName);
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
            // Use JavaScript to retrieve text content
            String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", element);

            // Trim and validate the text
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

/*
    public String minimumMessage() throws InterruptedException {
        // Wait for the minimum message element to be visible
        //WebElement enterMinimumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(enterMinimum));
        // WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(enterMinimum));
        //System.out.println(Message.getText());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // JavaScript to find the element and get its text
        String cssSelectorForHost1 = "#ion-overlay-7";

// Wait for the shadow host to be visible
        WebElement shadowHost = driver.findElement(By.cssSelector(cssSelectorForHost1));
        Thread.sleep(1000);  // Optional: Wait for the host element to be ready

// Access the shadow root
        SearchContext shadow = shadowHost.getShadowRoot();
        Thread.sleep(1000);  // Optional: Wait for the shadow root to be ready

// Locate the element inside the shadow DOM (toast message in this case)
        WebElement toastMessageElement = shadow.findElement(By.cssSelector(".toast-message"));

// Get the text of the toast message
        String toastMessage = toastMessageElement.getText();

        return toastMessage;
    }

 */


    public WebElement clickItemSearchBar() throws InterruptedException {
        // Wait for the first search bar to be visible and click it
        WebElement itemSearchBarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar));
        itemSearchBarButton.click();
        System.out.println("Clicked first search");

        // Wait for the second search bar (or another element that appears after clicking the first) to be visible
        WebElement itemSearchBarButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(itemSearchBar1));
        Thread.sleep(3000);
        itemSearchBarButton1.click();

        // Optional: You can add an explicit wait for any further interaction if required
        return itemSearchBarButton1;
    }

    /*
    @DataProvider(name = "searchData")
    public Object[][] accountDataProvider() throws IOException {
        ExcelUtils excelUtils=new ExcelUtils(excelFilePath,"TEST");
        int noOFRows = excelUtils.getRowCount();
        int noOfColumns = excelUtils.getColumnCount(); // Fixed column count
        System.out.println("Rows: " + noOFRows + ", Columns: " + noOfColumns);
        // Validate minimum data requirements
//        if (rowCount < 2 || colCount < 11) {
//            throw new RuntimeException(
//                    "Insufficient data in Excel file: Minimum 1 row and 11 columns required. " +
//                            "Rows: " + rowCount + ", Columns: " + colCount
//            );
//        }
        // Initialize data array
        Object[][] data = new Object[noOFRows][noOfColumns];
        // Populate data array from Excel
        for (int i = 0; i < noOFRows-1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df;
                data[i][j] = excelUtils.getData(i+1, j);
            }
        }

        return data;
    }



     */



}
