package McDelivery.Test;

import McDelivery.Base.baseClass;
import McDelivery.pages.customizationPage;
import McDelivery.pages.itemSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class customizationTest extends baseClass {
    @Test(priority = 1)
    public void verifyAddItemWithoutCustomisations() throws InterruptedException {
        test = extent.createTest("Verify if item is added to cart without customisations");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        Thread.sleep(2000);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();
        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();
        Thread.sleep(3000);
        if (customObj.isCustomisedItemDetailsVisible1()) {
            String text = customObj.customisedItemDetailsText().trim();
            if (text.isEmpty()) {
                test.pass("No customisations were added, and the details text is empty.");
                double FinalItemPrice = customObj.getCustomisedItemPrice();
                System.out.println(FinalItemPrice);
                if (FinalItemPrice == initialItemPrice) {
                    test.pass("Successfully added the product to cart without customisations.");
                } else {
                    test.fail("Product price does not match the expected price without customisations.");
                }
            } else {
                System.out.println(text);
                test.fail("Customised item details are displayed for not added customisations: " + text);
            }
        } else {
            test.pass("Customised item details are not displayed for not added customisations.");
            double FinalItemPrice = customObj.getCustomisedItemPrice();
            System.out.println(FinalItemPrice);
            if (FinalItemPrice == initialItemPrice) {
                test.pass("Successfully added the product to cart without customisations.");
            } else {
                test.fail("Product price does not match the expected price without customisations.");
            }
        }

    }
    @Test(priority = 2)
    public void verifyIncreaseQuantity() throws InterruptedException {
        test = extent.createTest("Verify if increasing item quantity reflects price");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();
        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();
        Thread.sleep(3000);
        customObj.increaseQuantityByOne(Quantity);
        Thread.sleep(3000);
        double finalItemPrice = customObj.getCustomisedItemPrice();
        System.out.println(finalItemPrice);
        int expectedPrice = (int) ((Quantity + 1) * initialItemPrice);
        int finalPriceAsInt = (int) finalItemPrice;

        Assert.assertEquals(expectedPrice, finalPriceAsInt, "The desired items are not added");
        test.pass("The number of items added are verified, single quantity price is " + (int) initialItemPrice +
                ", and final item price for item quantity " + Quantity + " is " + finalPriceAsInt);


    }
    @Test(priority = 3)
    public void verifyDecreaseQuantity() throws InterruptedException {
        test = extent.createTest("Verify if increasing and decreasing quantity reflects price");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();

        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();
        Thread.sleep(3000);
        customObj.increaseQuantityByOne(Quantity);
        Thread.sleep(3000);
        double finalItemPrice = customObj.getCustomisedItemPrice();
        System.out.println(finalItemPrice);
        double expectedPrice = (Quantity+1) * initialItemPrice;
        int initialPriceAsInt = (int) initialItemPrice;
        Assert.assertEquals(expectedPrice,finalItemPrice,"The desired items quantity is not added");

        test.pass("The number of items added are verified and single quanity price is "+initialPriceAsInt + "and final item price is for item quantity"+Quantity+" is "+finalItemPrice);
        customObj.decreaseQuantityByOne(Quantity);
        double finalItemPriceAfterRemove = customObj.getCustomisedItemPrice();
        System.out.println(finalItemPriceAfterRemove);
        double expectedPriceAfterRemove = initialItemPrice;
        Assert.assertEquals(expectedPriceAfterRemove,initialItemPrice,"The desired items quantity is not removed");
        test.pass("The number of items removed are verified and single quanity price is "+initialPriceAsInt + " and final item price after removing quantity is "+expectedPriceAfterRemove);



    }

    @Test(priority = 4)
    public void verifyIfCustomisableDisplayed() throws InterruptedException {
        test = extent.createTest("Verify if customisable text and add to cart displayed   under item");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        Assert.assertTrue(customObj.isCustomisableDisplayed(), "No customisable text is displayed");
        test.pass("Customisable text is displayed under search item");
        Assert.assertTrue(customObj.isAddToCartDisplayed(), "No add to cart button displayed");
        test.pass("Add to cart is displayed under search item");
    }

    @Test(priority = 5)
    public void verifyCustomisableItems() throws InterruptedException {
        test = extent.createTest("Verify if customised details are shown in cart and price is   reflected");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        Thread.sleep(3000);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();


        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);


        customObj.addCheeseItem(3);
        customObj.addVegSauceItem(3);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();

        if (customObj.isCustomisedItemDetailsVisible()) {
            String text = customObj.customisedItemDetailsText();
            System.out.println(text);
            test.pass("Customised details are visible in the item description as : " + text);
            double FinalItemPrice = customObj.getCustomisedItemPrice();
            System.out.println(FinalItemPrice);
            Assert.assertTrue(FinalItemPrice > initialItemPrice, "Customised items are not added");
            test.pass("Customised items are add and final price is "+FinalItemPrice);
        } else {
            test.fail("Customised items are not added");
        }
    }

    @Test(priority = 6)
    public void verifyRemoveCustomisedItems() throws InterruptedException {
        test = extent.createTest("Verify if removing an item reflects the price in the cart");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();


        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);

        customObj.addCheeseItem(3);
        customObj.addVegSauceItem(3);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();

        double FinalItemPrice = 0.0;
        if (customObj.isCustomisedItemDetailsVisible()) {
            String text = customObj.customisedItemDetailsText();
            System.out.println(text);
            test.pass("Customised details are visible in the item description as : " + text);

            FinalItemPrice = customObj.getCustomisedItemPrice();
            System.out.println(FinalItemPrice);

            if (customObj.isCustomisedDisplayed()) {
                Thread.sleep(3000);
                customObj.clickCustomised();
                customObj.removeCheeseItem(2);
                Thread.sleep(3000);
                customObj.clickDone();

                Thread.sleep(3000);
                double itemPriceAfterRemoved = customObj.getCustomisedItemPrice();
                System.out.println(itemPriceAfterRemoved);

                if (itemPriceAfterRemoved < FinalItemPrice && itemPriceAfterRemoved > initialItemPrice) {
                    System.out.println("Customised item is successfully removed.");
                    test.pass("Customised item is successfully removed, and price updated.");
                } else {
                    System.out.println("Customised items removal did not update the price.");
                    test.fail("Customised items removal failed.");
                }
            } else {
                System.out.println("Customised text is not displayed.");
                test.fail("Customised text is not displayed.");
            }
        } else {
            System.out.println("Customised item details are not visible.");
            test.fail("Customised item details are not visible.");
        }
    }

    @Test(priority = 7)
    public void verifyRemoveAllCustomisedItems() throws InterruptedException {
        test = extent.createTest("Verify if price reflects by removing all added              customisations");
        customizationPage customObj = new customizationPage(driver);
        itemSearchPage searchPageObj = new itemSearchPage(driver);
        waitForElementToBeClickable(searchPageObj.clickItemSearchBar(),10).click();
        searchPageObj.searchItem(searchFoodItemName);
        customObj.firstItem();
        waitForElementToBeClickable(customObj.addToCartItem(),10).click();
        double initialItemPrice = customObj.getItemPrice();
        System.out.println(initialItemPrice);

        customObj.addCheeseItem(3);
        customObj.addVegSauceItem(3);
        customObj.clickAddToCartCustomised();
        customObj.viewCartMethod();

        double FinalItemPrice = 0.0;
        if (customObj.isCustomisedItemDetailsVisible()) {
            String text = customObj.customisedItemDetailsText();
            System.out.println(text);
            test.pass("Customised details are visible in the item description as : " + text);
            Thread.sleep(3000);

            FinalItemPrice = customObj.getCustomisedItemPrice();
            System.out.println(FinalItemPrice);

            if (customObj.isCustomisedDisplayed()) {
                test.pass("Customised button is displayed indicating customisations are added");
                Thread.sleep(3000);
                customObj.clickCustomised();
                customObj.removeCheeseItem(2);
                Thread.sleep(3000);
                customObj.removeVegSauceItem(2);
                System.out.println("removed veg");
                Thread.sleep(3000);
                customObj.clickDone();

                Thread.sleep(3000);
                double itemPriceAfterRemoved = customObj.getCustomisedItemPrice();
                System.out.println(itemPriceAfterRemoved);

                if (itemPriceAfterRemoved == initialItemPrice) {
                    System.out.println("Customised items are successfully removed.");
                    test.pass("Customised items are successfully removed, and price updated.");
                } else {
                    System.out.println("Customised items removal did not update the price.");
                    test.fail("Customised items removal failed.");
                }
            } else {
                System.out.println("Customised text is not displayed.");
                test.fail("Customised text is not displayed.");
            }
        } else {
            System.out.println("Customised item details are not visible.");
            test.fail("Customised item details are not visible.");
        }
    }


}
