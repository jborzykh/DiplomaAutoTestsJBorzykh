import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/* тестовые сценарии
https://docs.google.com/spreadsheets/d/1L5Mt6SQdWxHHyCJpL_K5MQ8K1SMpdOBjMaj27lPM42s/edit?usp=sharing

*/

public class orderPageTestsIntershop {
    static WebDriver webDriver;
    static String baseUrl;
    static WebDriverWait webDriverWait;

    private By sectionOne = By.cssSelector("section#product1");
    private By sectionTwo = By.cssSelector("section#product2");
    private By mainButtonHeader = By.cssSelector(".menu-item-26 a");
    private By catalogButtonHeader = By.cssSelector(".menu-item-46 a");
    private By orderingButtonHeader = By.cssSelector("#menu-item-31>a");
    private By cartButtonHeader = By.cssSelector(".menu-item-29 a");
    private By myAccountButtonHeader = By.cssSelector(".menu-item-30 a");
    private By pleaseLogIn = By.cssSelector(".showlogin");
    private By checkoutButton = By.cssSelector(".checkout-button");
    private By logOutButton = By.linkText("Выйти");
    private By orderButton = By.id("place_order");

    private By firstProductAddToCartButton = By.cssSelector(".product:nth-child(1) .button");
    private By secondProductAddToCartButton = By.cssSelector(".product:nth-child(2) .button");
    private By thirdProductAddToCartButton = By.cssSelector(".product:nth-child(3) .button");
    private By firstProductAddedToCart = By.cssSelector(".post-15 .added_to_cart");
    private By secondProductAddedToCart = By.cssSelector(".post-53 .added_to_cart");
    private By thirdProductAddedToCart = By.cssSelector(".post-55 .added_to_cart");
    private By thirdSaleItem = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7)");
    private By thirdSaleItemAddToCartButton =
            By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .add_to_cart_button");
    private By thirdSaleItemMoreButton = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .added_to_cart");
    private By secondLatestItem = By.cssSelector("section#product2 .slick-track>li:nth-of-type(6)");
    private By secondLatestItemAddToCartButton =
            By.cssSelector("section#product2 .slick-track>li:nth-of-type(6) .add_to_cart_button");
    private By secondLatestItemMoreButton = By.cssSelector("section#product2 .slick-track>li:nth-of-type(6) .added_to_cart");
    private By product1Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(1) > .product-name > a");
    private By product2Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(2) > .product-name > a");
    private By product3Name =
            By.cssSelector(".woocommerce-cart-form__cart-item:nth-child(3) > .product-name > a");
    private By checkoutProduct1 = By.cssSelector(".cart_item:nth-child(1)");
    private By checkoutProduct2 = By.cssSelector(".cart_item:nth-child(2)");
    private By checkoutProduct3 = By.cssSelector(".cart_item:nth-child(3)");


    private By userNameInputAuthForm = By.id("username");
    private By passwordInputAuthForm = By.id("password");
    private By logInButtonAuthForm = By.cssSelector(".woocommerce-button");

    private By billingFirstName = By.id("billing_first_name");
    private By billingLastName = By.id("billing_last_name");
    private By billingCountry = By.id("select2-billing_country-container");
    private By selectArgentina = By.cssSelector("[value='AR']");
    private By selectVietnam = By.cssSelector("[value='VN']");
    private By selectedCountry = By.id("select2-billing_country-container");
    private By billingAddress = By.id("billing_address_1");
    private By billingCity = By.id("billing_city");
    private By billingRegion = By.id("billing_state");
    private By billingPostCode = By.id("billing_postcode");
    private By billingPhone = By.id("billing_phone");
    private By billingEMail = By.id("billing_email");
    private By notFilledFirstNameMessage = By.cssSelector("[data-id='billing_first_name'] strong");
    private By notFilledLastNameMessage = By.cssSelector("[data-id='billing_last_name'] strong");
    private By notFilledAddressMessage = By.cssSelector("[data-id='billing_address_1'] strong");
    private By notFilledCityMessage = By.cssSelector("[data-id='billing_city'] strong");
    private By notFilledStateMessage = By.cssSelector("[data-id='billing_state'] strong");
    private By notFilledPostcodeMessage = By.cssSelector("[data-id='billing_postcode'] strong");
    private By notFilledPhoneMessage = By.cssSelector("[data-id='billing_phone']:nth-of-type(2) strong");
    private By notFilledEmailMessage = By.cssSelector("[data-id='billing_email'] strong");

    private By payCash = By.id("payment_method_cod");
    private By payBank = By.id("payment_method_bacs");


    private By haveDiscountOrderPage = By.cssSelector("a.showcoupon");
    private By couponFormOrderPage = By.cssSelector(".woocommerce-form-coupon");
    private By couponInputOrderPage = By.cssSelector("[name='coupon_code']");
    private By applyCouponButtonOrderPage = By.cssSelector("[name='apply_coupon']");
    private By couponWasApplied = By.cssSelector(".coupon-sert500 th");
    private By paymentMethod = By.cssSelector(".woocommerce-order-overview__payment-method strong");
    private By orderRecievedMessage = By.xpath("//h2[contains(text(),'Заказ получен')]");
    private By couponMessage = By.cssSelector(".woocommerce-message");
    private By couponError = By.cssSelector(".woocommerce-error");
    private By removeCouponButton = By.cssSelector(".woocommerce-remove-coupon");
    private By removedCouponMessage = By.xpath("//div[contains(text(),'удален')]");


    String userName = "julia2407";
    String password = "julia2407";
    String name = "Julia";
    String lastName = "Borzykh";
    String address = "Lenina 1-11";
    String city = "Krasnodar";
    String state = "Krasnodarsky kray";
    String zip = "111111";
    String phone = "87776665544";
    String email = "julia2407@2407.ru";
    String coupon = "sert500";
    String couponFalse = "sert10000000";


    @Before
    public  void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        baseUrl = "http://intershop5.skillbox.ru/";
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 10);

    }
    @After
    public  void teardown() {
        webDriver.quit();
    }

    //Добавление товара в корзину, авторизация, оформление заказа со всеми полями безналичный расчет
    @Test
    public void OrderPage_MakeOrderWithoutLoginWithAllFieldsBankPayment_OrderComplete() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", webDriver.findElement(orderRecievedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", webDriver.findElement(paymentMethod)
                .getText().contains("Прямой банковский перевод"));


    }

    //Добавление товара в корзину, авторизация, оформление заказа со всеми полями наличный расчет
    @Test
    public void OrderPage_MakeOrderWithoutLoginWithAllFieldsCashPayment_OrderComplete() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        webDriver.findElement(payCash).click();
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", webDriver.findElement(orderRecievedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", webDriver.findElement(paymentMethod)
                .getText().contains("Оплата при доставке"));


    }

    //Авторизация на сайте, добавление товара в корзину, оформление заказа со всеми полями безналичный расчет
    @Test
    public void OrderPage_LoggedInUserMakesOrderWithAllFieldsBankPayment_OrderComplete() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", webDriver.findElement(orderRecievedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", webDriver.findElement(paymentMethod)
                .getText().contains("Прямой банковский перевод"));


    }

    //Авторизация на сайте, добавление товара в корзину, оформление заказа со всеми полями наличный расчет
    @Test
    public void OrderPage_LoggedInUserMakesOrderWithAllFieldsCashPayment_OrderComplete() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        webDriver.findElement(payCash).click();
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", webDriver.findElement(orderRecievedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", webDriver.findElement(paymentMethod)
                .getText().contains("Оплата при доставке"));


    }

    //Оформление заказа авторизованным пользователем из корзины со всеми заполненными полями
    @Test
    public void OrderPage_LoggedInUserMakesOrderWithAllFieldsViaCartPage_OrderComplete() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(cartButtonHeader).click();
        webDriver.findElement(checkoutButton).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        webDriver.findElement(payBank).click();
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("заказ не был оформлен", webDriver.findElement(orderRecievedMessage)
                .getText().contains("Заказ получен"));
        Assert.assertTrue("выбран неправильный метод оплаты", webDriver.findElement(paymentMethod)
                .getText().contains("Прямой банковский перевод"));


    }

    //Оформление заказа авторизованным пользователем  - ввод существующего купона
    @Test
    public void OrderPage_CheckoutWithDiscountCoupon_DiscountApplied() {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(haveDiscountOrderPage).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(couponFormOrderPage));
        webDriver.findElement(couponInputOrderPage).sendKeys(coupon);
        webDriver.findElement(applyCouponButtonOrderPage).click();

        Assert.assertTrue("не появилось сообщение о добавлении купона вместо поля ввода купона", webDriver.findElement(couponMessage)
                .getText().contains("Купон успешно добавлен."));

        Assert.assertTrue("купон sert500 не отобразился в общей стоимости заказа", webDriver.findElement(couponWasApplied)
                .getText().contains("СКИДКА: SERT500"));
        webDriver.findElement(logOutButton).click();
    }

    //Оформление заказа авторизованным пользователем  - ввод несуществующего купона
    @Test
    public void OrderPage_CheckoutWithInvalidDiscountCoupon_DiscountDenied()  {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(mainButtonHeader).click();

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

        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(haveDiscountOrderPage).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(couponFormOrderPage));
        webDriver.findElement(couponInputOrderPage).sendKeys(couponFalse);
        webDriver.findElement(applyCouponButtonOrderPage).click();

        Assert.assertTrue("не появилось сообщение о том, что купон неверный", webDriver.findElement(couponError)
                .getText().contains("Неверный купон."));
    }

    //Удаление купона из формы оформления заказа
    @Test
    public void OrderPage_DeleteAppliedCouponFromOrder_CouponDeleted()  {
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
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(haveDiscountOrderPage).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(couponFormOrderPage));
        webDriver.findElement(couponInputOrderPage).sendKeys(coupon);
        webDriver.findElement(applyCouponButtonOrderPage).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(couponMessage));
        webDriver.findElement(removeCouponButton).click();

        WebElement top = webDriver.findElement(removedCouponMessage);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", top);

        /*var l = webDriver.findElement(removedCouponMessage);
        System.out.println(l.getText());*/
        Assert.assertTrue("купон не был удален", webDriver.findElement(removedCouponMessage)
                .getText().contains("Купон удален."));


    }


    //Оформление заказа без имени
    @Test
    public void OrderPage_MakeOrderWithoutUserFirstNameField_FirstNameRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незполненном имени", webDriver.findElement(notFilledFirstNameMessage)
                .getText().contains("Имя для выставления счета"));
    }

    //Оформление заказа  без фамилии
    @Test
    public void OrderPage_MakeOrderWithoutUserLastNameField_LastNameRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненной фамилии", webDriver.findElement(notFilledLastNameMessage)
                .getText().contains("Фамилия для выставления счета"));
    }

    //Оформление заказа  без адреса
    @Test
    public void OrderPage_MakeOrderWithoutAddressField_AddressRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном адресе", webDriver.findElement(notFilledAddressMessage)
                .getText().contains("Адрес для выставления счета"));

    }

    //Оформление заказа  без указания города
    @Test
    public void OrderPage_MakeOrderWithoutCityField_CityRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном городе", webDriver.findElement(notFilledCityMessage)
                .getText().contains("Город / Населенный пункт для выставления счета"));

    }


    //Оформление заказа  без указания области
    @Test
    public void OrderPage_MakeOrderWithoutStateField_StateRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненной области", webDriver.findElement(notFilledStateMessage)
                .getText().contains("Область для выставления счета"));

    }

    //Оформление заказа  без указания индекса
    @Test
    public void OrderPage_MakeOrderWithoutPostCodeField_PostCodeRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном индексе", webDriver.findElement(notFilledPostcodeMessage)
                .getText().contains("Почтовый индекс для выставления счета"));

    }

    //Оформление заказа  без указания телефона
    @Test
    public void OrderPage_MakeOrderWithoutPhoneField_PhoneRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingEMail).clear();
        webDriver.findElement(billingEMail).sendKeys(email);
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном номере телефона", webDriver.findElement(notFilledPhoneMessage)
                .getText().contains("Телефон для выставления счета"));

    }
    //Оформление заказа  без указания e-mail
    @Test
    public void OrderPage_MakeOrderWithoutEmailField_EmailRequiredMessage() throws Exception {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingFirstName).clear();
        webDriver.findElement(billingFirstName).sendKeys(name);
        webDriver.findElement(billingLastName).clear();
        webDriver.findElement(billingLastName).sendKeys(lastName);
        webDriver.findElement(billingAddress).clear();
        webDriver.findElement(billingAddress).sendKeys(address);
        webDriver.findElement(billingCity).clear();
        webDriver.findElement(billingCity).sendKeys(city);
        webDriver.findElement(billingRegion).clear();
        webDriver.findElement(billingRegion).sendKeys(state);
        webDriver.findElement(billingPostCode).clear();
        webDriver.findElement(billingPostCode).sendKeys(zip);
        webDriver.findElement(billingPhone).clear();
        webDriver.findElement(billingPhone).sendKeys(phone);
        webDriver.findElement(billingEMail).clear();
        Thread.sleep(30);
        webDriver.findElement(orderButton).click();

        Assert.assertTrue("не появилось сообщение о незаполненном email", webDriver.findElement(notFilledEmailMessage)
                .getText().contains("Адрес почты для выставления счета"));

    }
    //Оформление в страну Аргентина
    @Test
    public void OrderPage_SelectCountryFromListArgentina_SelectedCountryArgentina()  {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingCountry).click();
        webDriver.findElement(selectArgentina).click();

        Assert.assertTrue("не выбрана страна Аргентина", webDriver.findElement(selectedCountry)
        .getText().contains("Argentina"));
    }

    //Оформление в страну Вьетнам
    @Test
    public void OrderPage_SelectCountryFromListVietnam_SelectedCountryVietnam() {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(orderingButtonHeader).click();
        webDriver.findElement(pleaseLogIn).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        webDriver.findElement(billingCountry).click();
        webDriver.findElement(selectVietnam).click();

        Assert.assertTrue("не выбрана страна Вьетнам", webDriver.findElement(selectedCountry)
                .getText().contains("Vietnam"));
    }

    //Проверка соответсвия товаров в корзине и разделе Ваш заказ
    @Test
    public void OrderPage_ComparingItemNamesInCartAndCheckoutPage_AllGoodsAreDisplayedAndTheSame()  {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        webDriver.findElement(catalogButtonHeader).click();
        webDriver.findElement(firstProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductAddedToCart));
        webDriver.findElement(secondProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondProductAddedToCart));
        webDriver.findElement(thirdProductAddToCartButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdProductAddedToCart));

        webDriver.findElement(cartButtonHeader).click();
        var product1 = webDriver.findElement(product1Name).getText();
        var product2 = webDriver.findElement(product2Name).getText();
        var product3 = webDriver.findElement(product3Name).getText();

        webDriver.findElement(checkoutButton).click();
        var checkout1 = webDriver.findElement(checkoutProduct1).getText();
        var checkout2 = webDriver.findElement(checkoutProduct2).getText();
        var checkout3 = webDriver.findElement(checkoutProduct3).getText();
        var one = webDriver.findElement(checkoutProduct1);
        System.out.println(one.getText());
        var two = webDriver.findElement(checkoutProduct2);
        System.out.println(two.getText());
        var three = webDriver.findElement(checkoutProduct3);
        System.out.println(three.getText());

        var appleWatch = "Apple Watch 6";
        var iPad2020 = "iPad 2020 32gb wi-fi";
        var iPadAir = "iPad Air 2020 64gb wi-fi";

        Assert.assertTrue("товар1 не отобразился в разделе ваш заказ",webDriver.findElement(checkoutProduct1).isDisplayed());
        Assert.assertTrue(String.format("неправильный товар1", product1,checkout1), checkout1.contains(appleWatch));
        Assert.assertTrue("товар2 не отобразился в разделе ваш заказ",webDriver.findElement(checkoutProduct2).isDisplayed());
        Assert.assertTrue(String.format("неправильный товар2", product2,checkout2), checkout2.contains(iPad2020));
        Assert.assertTrue("товар3 не отобразился в разделе ваш заказ",webDriver.findElement(checkoutProduct3).isDisplayed());
        Assert.assertTrue(String.format("неправильный товар3", product3,checkout3), checkout3.contains(iPadAir));

    }



}
