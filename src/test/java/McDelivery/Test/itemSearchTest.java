package McDelivery.Test;

import McDelivery.Base.baseClass;
import McDelivery.pages.itemSearchPage;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class itemSearchTest extends baseClass {
    itemSearchPage searchPageObj;
    private By enterMinimum;

    @Test(priority = 1)
    public void VerifySearchItemVisible() {
        test = extent.createTest("verify Search Bar Visibility");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Assert.assertTrue(searchPageObj.isSearchBarVisible(), "Search Bar is not visible");
        test.pass("Search Bar is Visible");

    }

    @Test(priority = 2)
    public void enterValidItemsVerifyResults() throws InterruptedException {
        test = extent.createTest("To validate search results with valid name");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
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


    @Test(priority = 3)
    public void enterPartialSearchInput() throws InterruptedException {
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

    @Test(priority = 4)
    public void enterInvalidSearchItemName() throws InterruptedException {
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
    @Test(priority = 5)
    public void verifyInCaseSensitiveSearchInput() throws InterruptedException {
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
    @Test(priority = 6)
    public void searchInputByCategory() throws InterruptedException {
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
    @Test(priority = 7)
    public void enterSpecialCharacters() throws InterruptedException {
        test = extent.createTest("To validate search results with special characters ");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        searchPageObj.searchItem(searchFoodBySpecialChar);
        List<WebElement> results = searchPageObj.getSearchResults();
        Thread.sleep(3000);

       String screenshotPath = captureScreenshot("enterSpecialCharacters");
       test.fail("The invalid search input is displaying the default items ").addScreenCaptureFromPath(screenshotPath);
        //MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build();

    }


    /*
    @Test
    public void enterLessThan2characters() throws InterruptedException {
        test = extent.createTest("To validate search results with Category ");
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(3000);

        searchPageObj.clickItemSearchBar();
        String searchItemInput = "ve";
        searchPageObj.searchItem(searchItemInput);
        String toastMessage = searchPageObj.minimumMessage();
        System.out.println(toastMessage);
        Assert.assertTrue(toastMessage.contains("Please enter minimum 3 characters"),
                "Message does not contain the expected text: " + toastMessage);
        test.pass("Error message displayed : "+toastMessage);

    }


*/





}
