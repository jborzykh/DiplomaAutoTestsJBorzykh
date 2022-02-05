import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/* тестовые сценарии
https://docs.google.com/spreadsheets/d/1a3Ydvz-w1vgTdGyp4lNtxCGc6P_uRGvUlXrYayDNKRs/edit#gid=0

*/


public class cartPageTestsIntershop {
    static WebDriver webDriver;
    static String baseUrl;
    static WebDriverWait webDriverWait;

    private By sectionOne = By.cssSelector("section#product1");
    private By sectionTwo = By.cssSelector("section#product2");
    private By cartTotalsSection = By.cssSelector("div.cart_totals");
    private By mainButtonHeader = By.cssSelector(".menu-item-26 a");
    private By catalogButtonHeader = By.cssSelector(".menu-item-46 a");
    private By cartButtonHeader = By.cssSelector(".menu-item-29 a");
    private By currentPageLocator = By.cssSelector("span.current");

    //товары главной страницы
    private By thirdSaleItem = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7)");

    private By thirdSaleItemAddToCartButton =
            By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .add_to_cart_button");

    private By thirdSaleItemMoreButton = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .added_to_cart");

    private By secondLatestItem = By.cssSelector("section#product2 .slick-track>li:nth-of-type(6)");

    private By secondLatestItemAddToCartButton =
            By.cssSelector("section#product2 .slick-track>li:nth-of-type(6) .add_to_cart_button");

    private By secondLatestItemMoreButton = By.cssSelector("section#product2 .slick-track>li:nth-of-type(6) .added_to_cart");
    private By productTitleLocator = By.cssSelector(".product_title.entry-title");

    //товары каталога
    private By firstProductAddToCartButton= By.cssSelector(".product:nth-child(1) .button");
    private By secondProductAddToCartButton = By.cssSelector(".product:nth-child(2) .button");
    private By thirdProductAddToCartButton = By.cssSelector(".product:nth-child(3) .button");
    private By firstProductAddedToCart= By.cssSelector(".post-15 .added_to_cart");
    private By secondProductAddedToCart= By.cssSelector(".post-53 .added_to_cart");
    private By thirdProductAddedToCart= By.cssSelector(".post-55 .added_to_cart");


    //наименование товаров каталога
    private By product1Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(1) > .product-name > a");
    private By product2Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(2) > .product-name > a");
    private By product3Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(3) > .product-name > a");

    //удаление товаров из корзины
    private By oneProductToRemoveFromCartButton = By.cssSelector(".remove");
    private By productWasRemovedMessage = By.cssSelector(".woocommerce-message");
    private By cartIsEmpty = By.cssSelector(".cart-empty");
    private By product1DeleteButtonLocator =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-of-type(1) >.product-remove > a");
    private By product2DeleteButtonLocator =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-of-type(2) >.product-remove > a");
    private By product3DeleteButtonLocator =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-of-type(3) >.product-remove > a");
    private By restoreButton = By.cssSelector(".restore-item");

    //Купон
    private By couponCodeInput = By.id("coupon_code");
    private By couponButton = By.cssSelector("[name='apply_coupon']");
    private By discountAmmountLocator = By.cssSelector("td:nth-child(2) > .woocommerce-Price-amount");
    private By couponError = By.cssSelector(".woocommerce-error > li");
    private By removeCouponButton = By.cssSelector(".woocommerce-remove-coupon");
    private By couponRemovedMessage = By.cssSelector(".woocommerce-message");

    private By checkoutButton =By.cssSelector(".checkout-button");

    @Before
    public  void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        baseUrl = "http://intershop5.skillbox.ru";
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

  @After
    public  void teardown()  {
        webDriver.quit();
    }

    //Добавление одного товара с главной страницы в Корзину
    @Test
    public void CartPage_AddOneItemOfSameTypeFromMainPageToCart_ProductAddedToCart() throws Exception{
        webDriver.get(baseUrl);
        WebElement section1 = webDriver.findElement(sectionOne);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section1);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItem));
        WebElement salesItem = webDriver.findElement(thirdSaleItem);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(salesItem).perform();

        webDriver.findElement(thirdSaleItemAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItemMoreButton));
        webDriver.findElement(cartButtonHeader).click();

        Assert.assertTrue("не был добавлен товар New White Woman’s Shorts", webDriver.findElement(product1Name)
                .getText().contains("New White Woman’s Shorts"));
}

    //Добавление двух товаров с главной страницы в Корзину
    @Test
    public void CartPage_AddTwoItemsOfDifferentFromMainPageTypeToCart_ProductsAddedToCart() throws Exception {
        webDriver.get(baseUrl);
        WebElement section1 = webDriver.findElement(sectionOne);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section1);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItem));
        WebElement salesItem = webDriver.findElement(thirdSaleItem);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(salesItem).perform();
        webDriver.findElement(thirdSaleItemAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItemMoreButton));

        WebElement section2 = webDriver.findElement(sectionTwo);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItem));
        WebElement latestItem = webDriver.findElement(secondLatestItem);
        Actions action2 = new Actions(webDriver);
        action2.moveToElement(latestItem).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItemAddToCartButton));
        webDriver.findElement(secondLatestItemAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItemMoreButton));

        webDriver.findElement(cartButtonHeader).click();
        Assert.assertTrue("не был добавлен товар New White Woman’s Shorts", webDriver.findElement(product1Name)
                .getText().contains("New White Woman’s Shorts"));
        Assert.assertTrue("не был добавлен товар Новый товар", webDriver.findElement(product2Name)
                .getText().contains("Новый товар"));
    }

    //Добавление трех товаров из раздела каталог в корзину
    @Test
    public void CartPage_AddThreeFirstItemsFromCatalogToCart_ProductsAddedToCart() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));

        webDriver.findElement(cartButtonHeader).click();

        Assert.assertTrue("первый товар не был добавлен в корзину", webDriver.findElement(product1Name)
                .getText().contains("Apple Watch 6"));
        Assert.assertTrue("второй товар не был добалвен в корзину", webDriver.findElement(product2Name)
                .getText().contains("iPad 2020 32gb wi-fi"));
        Assert.assertTrue("третий товар не был добалвен в корзину", webDriver.findElement(product3Name)
                .getText().contains("iPad Air 2020 64gb wi-fi"));

    }

    // Удаление одного товара из корзины
    @Test
    public void CartPage_AddOneItemToCartAndRemoveItFromCart_ProductRemovedAndCartEmpty() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(oneProductToRemoveFromCartButton).click();
        Thread.sleep(10);

        Assert.assertTrue("товар не был удален из корзины", webDriver.findElement(productWasRemovedMessage)
            .getText().contains("Apple Watch 6"));

        Assert.assertTrue("появилось сообщение Корзина пуста" ,webDriver.findElement(cartIsEmpty)
                .getText().contains("Корзина пуста."));

    }

    //Добавление двух товаров в корзину и удаление одного
    @Test
    public void CartPage_AddTwoItemsToCartAndRemoveOneItemFromCart_OneItemRemovedAndOneItemLeftInCart() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(oneProductToRemoveFromCartButton).click();
        Thread.sleep(10);

        Assert.assertTrue("первый товар не был удален из корзины", webDriver.findElement(productWasRemovedMessage)
                .getText().contains("Apple Watch 6"));
        Assert.assertTrue("второй товар остутсвует", webDriver.findElement(product1Name)
                .getText().contains("iPad 2020 32gb wi-fi"));

    }

    // Добавление трех товаро в корзину и их удаление из Корзины
    @Test
    public void CartPage_AddThreeItemsToCartAndRemoveAllFromCart_CartIsEmpty() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();

        webDriver.findElement(product3DeleteButtonLocator).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(product3Name));
        Assert.assertTrue("третий товар не был удален из корзины", webDriver.findElement(productWasRemovedMessage)
                .getText().contains("“iPad Air 2020 64gb wi-fi”"));

        webDriver.findElement(product2DeleteButtonLocator).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(product2Name));
        Assert.assertTrue("третий товар не был удален из корзины", webDriver.findElement(productWasRemovedMessage)
                .getText().contains("“iPad 2020 32gb wi-fi”"));

        webDriver.findElement(product1DeleteButtonLocator).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(product1Name));
        Assert.assertTrue("третий товар не был удален из корзины", webDriver.findElement(productWasRemovedMessage)
                .getText().contains("“Apple Watch 6”"));

        Assert.assertTrue("появилось сообщение Корзина пуста" ,webDriver.findElement(cartIsEmpty)
                .getText().contains("Корзина пуста."));
    }

    //Возрат удаленного из корзины товара
    @Test
    public void CartPage_ReturnDeletedItemToCart_ProductRestored() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(oneProductToRemoveFromCartButton).click();
        Thread.sleep(10);
        webDriver.findElement(restoreButton).click();

        Assert.assertTrue("первый товар не был возращен в корзину", webDriver.findElement(product1Name)
                .getText().contains("Apple Watch 6"));

    }

    //Просмотр карточки товара из корзины
    @Test
    public void CartPage_ProductReviewFromCart_GoesToItemCard() {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(product1Name).click();

        Assert.assertTrue("не произошел переход в карточку товара Apple Watch 6", webDriver.findElement(productTitleLocator)
                .getText().contains("Apple Watch 6"));

    }

    //Применение существующего купона
    @Test
    public void CartPage_ApplyCorrectDiscountCoupon_CouponApplied() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();

        webDriver.findElement(couponCodeInput).sendKeys("sert500");
        webDriver.findElement(couponButton).click();

        Assert.assertTrue("купон на 500рублей не добавился", webDriver.findElement(discountAmmountLocator)
                .getText().contains("500,00"));

    }


    //Применение несуществующего купона
    @Test
    public void CartPage_ApplyInCorrectDiscountCoupon_CouponNotApplied() {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();

        webDriver.findElement(couponCodeInput).sendKeys("100000");
        webDriver.findElement(couponButton).click();

        Assert.assertTrue("не появилось предупреждение Неверный купон", webDriver.findElement(couponError)
                .getText().contains("Неверный купон."));

    }

    //Удаление купона
    @Test
    public void CartPage_DeletingDiscountCoupon_CouponDeleted() throws Exception{
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();

        webDriver.findElement(couponCodeInput).sendKeys("sert500");
        webDriver.findElement(couponButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(discountAmmountLocator));
        webDriver.findElement(removeCouponButton).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(discountAmmountLocator));

        Assert.assertTrue("не появилось сообщение Купон удален", webDriver.findElement(couponRemovedMessage)
                .getText().contains("Купон удален."));

    }

    //Действия пользователя по добавлению товара в корзину и переходом в раздел Оформление заказа
    @Test
    public void CartPage_FullCustomerCheckout_GoesToOrderPage() throws Exception{
        webDriver.get(baseUrl);
        WebElement section1 = webDriver.findElement(sectionOne);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section1);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItem));
        WebElement salesItem = webDriver.findElement(thirdSaleItem);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(salesItem).perform();
        webDriver.findElement(thirdSaleItemAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItemMoreButton));

        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));

        webDriver.findElement(mainButtonHeader).click();
        WebElement section2 = webDriver.findElement(sectionTwo);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItem));
        WebElement latestItem = webDriver.findElement(secondLatestItem);
        Actions action2 = new Actions(webDriver);
        action2.moveToElement(latestItem).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItemAddToCartButton));
        webDriver.findElement(secondLatestItemAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItemMoreButton));

        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(couponCodeInput).sendKeys("sert500");
        webDriver.findElement(couponButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(discountAmmountLocator));

        WebElement totals = webDriver.findElement(cartTotalsSection);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", totals);
        webDriver.findElement(checkoutButton).click();
        var sourceFile10 = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile10, new File("tmp\\screenshot10.png"));
        var l = webDriver.findElement(currentPageLocator);
        System.out.println(l.getText());

        Assert.assertTrue("не произошел переход на станицу Оформления Заказа", webDriver.findElement(currentPageLocator)
                .getText().contains("Оформление Заказа"));



    }
}
