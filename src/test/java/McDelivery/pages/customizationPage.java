package McDelivery.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class customizationPage {

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;


    By searchResultFirstItem = By.xpath("//*[@id=\"659\"]/div");
    By customisable = By.xpath("//*[@id=\"659\"]/div/div[3]/div[2]/span");
    By addToCart = By.xpath("//*[@id=\"659\"]/div/div[3]/div[2]/app-add-to-cart-btn/div/div");
    By priceItem = By.xpath("//*[@id=\"659\"]/div/div[3]/div[1]/div/span");
    By viewCart = By.xpath("/html/body/app-root/ion-app/ion-header/app-toolbar-desktop/div/div[2]/div[2]/div");
    By orderItemDetails = By.xpath("(//div[@class='cart-page__order-summary-card'])[1]");
    By plus = By.xpath("/html/body/app-root/ion-app/ion-content/ion-router-outlet/app-cart/ion-content/div[2]/ion-grid/ion-row/ion-col[1]/app-order-summary/div/div[2]/app-menu-card-v3/div/div[2]/app-add-to-cart-btn/div/div[3]");
    By minus = By.xpath("/html/body/app-root/ion-app/ion-content/ion-router-outlet/app-cart/ion-content/div[2]/ion-grid/ion-row/ion-col[1]/app-order-summary/div/div[2]/app-menu-card-v3/div/div[2]/app-add-to-cart-btn/div/div[1]");

    By cheeseAdd = By.xpath("//*[@id=\"ion-overlay-3\"]/app-customise/div/div[2]/div[2]/swiper/div/div[4]/app-item-card/div/div[2]/app-add-to-cart-btn/div/div");
    By cheeseRemove = By.xpath("//*[@id=\"ion-overlay-5\"]/app-customise/div/div[2]/div[2]/swiper/div/div[4]/app-item-card/div/div[2]/app-add-to-cart-btn/div/div[1]");

    By addVegSauce = By.xpath("//*[@id=\"ion-overlay-3\"]/app-customise/div/div[2]/div[3]/swiper/div/div[1]/app-item-card/div/div[2]/app-add-to-cart-btn/div/div");
    By removeVegSauce = By.xpath("//*[@id=\"ion-overlay-5\"]/app-customise/div/div[2]/div[3]/swiper/div/div[1]/app-item-card/div/div[2]/app-add-to-cart-btn/div/div[1]");

    By done = By.xpath("//button[contains(@class, 'app-btn') and contains(text(), 'Done')]");
    By customisedItemDetails = By.xpath("/html/body/app-root/ion-app/ion-content/ion-router-outlet/app-cart/ion-content/div[2]/ion-grid/ion-row/ion-col[1]/app-order-summary/div/div[2]/app-menu-card-v3/div/div[1]/div/div[2]");
    By customisedPrice = By.xpath("//div[@class='menu__title-container']//div[@class='menu__price-bar']");
    By addToCart1 = By.xpath("//*[@id=\"ion-overlay-3\"]/app-customise/div/div[3]/div/app-button/button");

    By customised = By.cssSelector("div[class='menu__lable-customised'] div");

    By customiseNameVeg = By.xpath("//h5[normalize-space()='Veg Sauce']");


    public customizationPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement firstItem() {
        WebElement searchItemFirstElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultFirstItem));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchItemFirstElement);


        return searchItemFirstElement;
    }

    public boolean isCustomisableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(customisable)).isDisplayed();
    }


    public boolean isAddToCartDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart)).isDisplayed();
    }

    public WebElement addToCartItem(){
        WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
       // addToCartElement.click();
        return addToCartElement;
    }


    public double getItemPrice(){
        WebElement priceItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceItem));
        String priceText = priceItemElement.getText();
        return Double.parseDouble(priceText.replace("₹", "").trim());
    }
    public void viewCartMethod(){
        WebElement viewCartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(viewCart));
        viewCartElement.click();
    }

    public void cartDetails(){
        WebElement orderItemDetailsElement = driver.findElement(orderItemDetails);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", orderItemDetailsElement);



    }
    public boolean isOrderDetailsDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderItemDetails)).isDisplayed();


    }
    public void increaseQuantityByOne(double Quantity) throws InterruptedException {
        for(int i=0 ;i<Quantity ;i++) {
            WebElement plusElement = driver.findElement(plus);
            plusElement.click();
            Thread.sleep(3000);
        }
    }
    public void decreaseQuantityByOne(double Quantity) throws InterruptedException {
        for(int i=0 ;i<Quantity ;i++) {
            WebElement minusElement = driver.findElement(minus);
            minusElement.click();
            Thread.sleep(3000);
        }
    }

    public void addCheeseItem(int Quantity) throws InterruptedException {
        WebElement cheeseAddItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cheeseAdd));
        int maxAllowedClicks = 2;

        for (int i = 0; i < Math.min(Quantity, maxAllowedClicks); i++) {
            cheeseAddItemElement.click();
            Thread.sleep(3000);
        }

        if (Quantity > maxAllowedClicks) {
            System.out.println("Maximum number of customizations already added. Only " + maxAllowedClicks + " were allowed.");
        }

    }
    public void removeCheeseItem(int Quantity) throws InterruptedException {
        WebElement cheeseRemoveItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cheeseRemove));
        if(Quantity <= 2) {
            for (int i = 0; i < Quantity; i++) {
                cheeseRemoveItemElement.click();
                Thread.sleep(3000);
            }
        }else{
            System.out.println("Maximum customisations already deleted");
        }
    }

    public void addVegSauceItem(int Quantity) throws InterruptedException {
        WebElement vegSauceAddItemElement = driver.findElement(addVegSauce);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", vegSauceAddItemElement);
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0, -100);");
        int maxAllowedClicks = 2;

        for (int i = 0; i < Math.min(Quantity, maxAllowedClicks); i++) {
            vegSauceAddItemElement.click();
            Thread.sleep(3000);

        }

        if (Quantity > maxAllowedClicks) {
            System.out.println("Maximum number of customizations already added. Only " + maxAllowedClicks + " were allowed.");
        }

    }
    public void removeVegSauceItem(int Quantity) throws InterruptedException {
        WebElement customiseNameVegElement = driver.findElement(customiseNameVeg);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customiseNameVegElement);

        WebElement vegSauceRemoveItemElement = driver.findElement(removeVegSauce);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", vegSauceRemoveItemElement);
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0, -500);");
        if(Quantity <= 2){
            for (int i = 0; i < Quantity; i++) {
                vegSauceRemoveItemElement.click();
                Thread.sleep(3000);
            }

        }else{
            System.out.println("Maximum customisations already deleted");
        }
           }

    public void clickDone(){
        WebElement doneElement = driver.findElement(done);
        js.executeScript("arguments[0].click();", doneElement);

        //doneElement.click();
    }
    public boolean isCustomisedItemDetailsVisible1() throws InterruptedException {
        Thread.sleep(3000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(customisedItemDetails)).isEnabled();
    }

    public boolean isCustomisedItemDetailsVisible() throws InterruptedException {
        Thread.sleep(3000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(customisedItemDetails)).isDisplayed();
    }
    public String customisedItemDetailsText(){
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(customisedItemDetails));
        String descriptionText = textElement.getText();
        return descriptionText;
    }
    public double getCustomisedItemPrice(){
        WebElement CustomiseItemPriceElement = driver.findElement(customisedPrice);
        String customisedPriceText = CustomiseItemPriceElement.getText();
        return Double.parseDouble(customisedPriceText.replace("₹", "").trim());
    }


    public void clickAddToCartCustomised(){
        WebElement addTCart1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart1));
        addTCart1Element.click();
    }


    public boolean isCustomisedDisplayed(){
        WebElement customisedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(customised));
        customisedElement = driver.findElement(customised);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(customised)).isDisplayed();
    }

    public void clickCustomised(){
        WebElement customisedElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(customised));
        customisedElement1 = driver.findElement(customised);
        js.executeScript("arguments[0].click();", customisedElement1);
    }


}