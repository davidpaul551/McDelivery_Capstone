package McDelivery.Test;

import McDelivery.Base.baseClass;
import McDelivery.utils.excelData;
import McDelivery.pages.itemSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class itemSearchTest extends baseClass {
    private By enterMinimum;
    itemSearchPage searchPageObj;

    @Test( priority = 1)
    public void VerifySearchItemVisible() {
        test = extent.createTest("verify Search Bar Visibility");
        searchPageObj = new itemSearchPage(driver);
        Assert.assertTrue(searchPageObj.isSearchBarVisible(), "Search Bar is not visible");
        test.pass("Search Bar is Visible");

    }

    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 2)
    public void enterValidItemsVerifyResults( String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with valid name");
        searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);
        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodItemName);
        System.out.println("Searched items for burger");
        List<String> resultTitles = searchPageObj.ResultTitles(searchFoodItemName);

        Assert.assertFalse(resultTitles.isEmpty(), "No results found for the search term: burger");
        for (String title : resultTitles) {
            if (!title.toLowerCase().contains(searchFoodItemName)) {
                System.out.println("Invalid title: " + title);
                Assert.fail("Title '" + title + "' does not contain the search term 'burger'.");
            }
        }
        test.pass("Items are shown for valid search input");
        System.out.println("All result titles contain the search term 'burger'.");
    }


    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 3)
    public void enterPartialSearchInput(String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with partial valid name");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);
        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodByPartial);
        System.out.println("Searched items for burg");
        List<String> resultTitles = searchPageObj.ResultTitles(searchFoodByPartial);

        Assert.assertFalse(resultTitles.isEmpty(), "No results found for the search term: burg");

        for (String title : resultTitles) {
            if (!title.toLowerCase().contains(searchFoodByPartial)) {
                System.out.println("Invalid title: " + title);
                Assert.fail("Title '" + title + "' does not contain the search term 'burg'.");
            }
        }
        test.pass("Search items are shown for partial search input");

        System.out.println("All result titles contain the search term 'burg'.");
    }

    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 4)
    public void enterInvalidSearchItemName(String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with invalid name");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodByInvalidName);
        System.out.println("searched for biryani");
        List<WebElement> results = searchPageObj.getSearchResults();
        Thread.sleep(3000);
        Assert.assertFalse(results.isEmpty(),"The invalid is not  displaying items");
        String screenshotPath = captureScreenshot("enterInvalidSearchItemName");
        test.fail("The invalid is displaying the default items").addScreenCaptureFromPath(screenshotPath);
    }
    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 5)
    public void verifyInCaseSensitiveSearchInput(String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with case sensitive inputs");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodByUPPER);
        List<WebElement> upperCaseResults = searchPageObj.getSearchResults();
        Thread.sleep(3000);


        searchPageObj.searchItem(searchFoodByLOWER);
        List<WebElement> lowerCaseResults = searchPageObj.getSearchResults();

        Assert.assertEquals(upperCaseResults,lowerCaseResults,"The results differ for both case sensitive searches");
        test.pass("The search results are same for both case sensitive inputs");

    }
    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 6)
    public void searchInputByCategory(String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with Category ");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodByCategory);
        List<String> resultTitles = searchPageObj.ResultTitles(searchFoodByCategory);

        Assert.assertFalse(resultTitles.isEmpty(), "No results found for the search term: "+searchFoodByCategory);

        for (String title : resultTitles) {
            if (!title.toLowerCase().contains(searchFoodByCategory)) {
                System.out.println("Invalid title: " + title);
                Assert.fail("Title '" + title + "' does not contain the search term"+searchFoodByCategory+".");
            }

        }

        test.pass("The search results are shown for the search input by category : "+searchFoodByCategory);


        System.out.println("All result titles contain the search term "+searchFoodByCategory+".");


    }
    @Test(dataProvider = "testDataDetails",dataProviderClass = excelData.class,priority = 7)
    public void enterSpecialCharacters(String URL ,String searchFoodItemName, String searchFoodByUPPER, String searchFoodByLOWER, String searchFoodByCategory, String searchFoodByPartial, String searchFoodByInvalidName, Integer Quantity, String searchFoodBySpecialChar) throws InterruptedException {
        test = extent.createTest("To validate search results with special characters ");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodBySpecialChar);
        List<WebElement> results = searchPageObj.getSearchResults();
        Thread.sleep(3000);
        if(!results.isEmpty()){
            String screenshotPath = captureScreenshot("enterSpecialCharacters");
            test.fail("The invalid search input is displaying the default items ").addScreenCaptureFromPath(screenshotPath);

        }else{
            test.pass("No items displayed for invalid search input.");
        }

    }
}
