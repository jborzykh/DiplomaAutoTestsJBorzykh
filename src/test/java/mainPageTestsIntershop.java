

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
https://docs.google.com/spreadsheets/d/1JaCThUOhkJD2ge6hQa4ve8zITtgFaLufigNxuyIIjIg/edit#gid=0

*/

public class mainPageTestsIntershop {
    static WebDriver webDriver;
    static String baseUrl;
    static WebDriverWait webDriverWait;



    // Header Locators
    private By mainButtonHeader = By.cssSelector(".menu-item-26 a");
    private By catalogButtonHeader = By.cssSelector(".menu-item-46 a");
    private By myAccountButtonHeader = By.cssSelector(".menu-item-30 a");
    private By cartButtonHeader = By.cssSelector(".menu-item-29 a");
    private By orderingButtonHeader = By.cssSelector("#menu-item-31>a");
    private By searchInputLocator = By.cssSelector("input.search-field");
    private By searchButtonLocator = By.cssSelector("button.searchsubmit");
    private By enterAccountLocator = By.cssSelector(".login-woocommerce a.account");

    //Footer Locators
    private By footerSection = By.cssSelector("div#top-footer");
    private By contactsInformation = By.cssSelector("div.cta-banner");
    private By allGoodsButtonFooter = By.cssSelector(".page-item-33 a");
    private By cartButtonFooter = By.cssSelector(".page-item-20 a");
    private By myAccountButtonFooter = By.cssSelector(".page-item-22 a");
    private By orderingButtonFooter = By.cssSelector(".page-item-24 a");
    private By registrationButtonFooter = By.cssSelector(".page-item-141 a");

    //Sales, Main and Latest Goods
    private By booksWatchSection = By.xpath("(//span[@class='btn promo-link-btn'])[1]");
    private By tabletWatchSection = By.xpath("(//span[@class='btn promo-link-btn'])[2]");
    private By cameraWatchSection = By.xpath("(//span[@class='btn promo-link-btn'])[3]");
    private By mainBanner = By.cssSelector(".promo-widget-wrap-full a");
    private By mainBannerButton = By.cssSelector("#promo-section2 .promo-link-btn");
    private By booksWatchButton = By.xpath("(//h4[contains(@class,'widget-title')])[1]//following-sibling::span");
    private By tabletsWatchButton = By.xpath("(//h4[contains(@class,'widget-title')])[2]//following-sibling::span");
    private By camerasWatchButton = By.xpath("(//h4[contains(@class,'widget-title')])[3]//following-sibling::span");
    private By thirdSaleItem = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7)");
    private By thirdSaleItemAddToCartButton =
            By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .add_to_cart_button");
    private By thirdSaleItemMoreButton = By.cssSelector("section#product1 .slick-track>li:nth-of-type(7) .added_to_cart");
    private By secondLatestItem = By.cssSelector("section#product2 .slick-track>li:nth-of-type(6)");


    //Other Pages Locators
    private By currentPageSecondRowLocator = By.cssSelector("#accesspress-breadcrumb span.current");
    private By currentPageFirstRowLocator = By.cssSelector(".entry-title.ak-container");
    private By myAccountPageLocator = By.cssSelector(".content-inner.clearfix");
    private By productTitleLocator = By.cssSelector(".product_title.entry-title");
    private By lastWatchedProductTitle = By.cssSelector(".product_list_widget li:nth-of-type(1) span.product-title");
    private By searchResultsCountLocator = By.cssSelector("p.woocommerce-result-count");
    private By sectionOne = By.cssSelector("section#product1");
    private By sectionTwo = By.cssSelector("section#product2");

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
    public void teardown()  {
        webDriver.quit();
    }


    /* Header Tests Переход в разделы */

    //Переход в раздел Каталог
    @Test
    public void MainPage_ClickToHeaderCatalogButton_GoesToCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(catalogButtonHeader).click();
        Assert.assertTrue("не произошел переход в раздел Каталог", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("КАТАЛОГ"));
    }

    //Переход в раздел Мой аккаунт
    @Test
    public void MainPage_ClickToHeaderMyAccountButton_GoesToAuthorizationPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonHeader).click();
        Assert.assertTrue("не перешел переход в раздел Авторизация", webDriver.findElement(myAccountPageLocator).isDisplayed());
    }

    //Переход в раздел Корзина
    @Test
    public void MainPage_ClickToHeaderCartButton_GoesToCartPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(cartButtonHeader).click();
        Assert.assertTrue("не произошел переход в раздел Корзина", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Корзина"));
    }

    //Переход в раздел Оформление заказа с пустой корзиной
    @Test
    public void MainPage_ClickToHeaderOrderingButtonWithEmptyCart_GoesToCartPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(orderingButtonHeader).click();
        Assert.assertTrue("не произошел переход в раздел Корзина", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Корзина"));
    }

    //Переход в раздел Оформление заказа с товаром в корзине
    @Test
    public void MainPage_ClickToHeaderOrderingButtonWithFullCart_GoesToOrderingPage() throws Exception {
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
        webDriver.findElement(orderingButtonHeader).click();
        var sourceFile2 = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile2, new File("tmp\\screenshot2.png"));

        Assert.assertTrue("не произошел переход в Офоромление заказа", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Оформление Заказа"));
    }

    //Переход в раздел Мой аккаунт кнопкой Войти
    @Test
    public void MainPage_ClickToHeaderEnterButton_GoesToAuthorizationPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(enterAccountLocator).click();
        Assert.assertTrue("не перешел переход в раздел Авторизация", webDriver.findElement(myAccountPageLocator).isDisplayed());

    }

    //Header Search Поиск товаров в хедере

    //Поиск товара по полному наименованию
    @Test
    public void MainPageHeaderSearch_FullNameAppleWatch6Search_FindsAppleWatch6() {
        webDriver.get(baseUrl);
        webDriver.findElement(searchInputLocator).sendKeys("Apple Watch 6");
        webDriver.findElement(searchButtonLocator).click();
        Assert.assertTrue("не найден товар Apple Watch 6", webDriver.findElement(productTitleLocator)
                .getText().contains("Apple Watch 6"));

    }

    //Поиск товара по частичному наименованию
    @Test
    public void MainPageHeaderSearch_PartNameAppleItemsSearch_Finds3AppleItems() {
        webDriver.get(baseUrl);
        webDriver.findElement(searchInputLocator).sendKeys("Apple");
        webDriver.findElement(searchButtonLocator).click();
        Assert.assertTrue("не найдены товары Apple", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("РЕЗУЛЬТАТЫ ПОИСКА: “APPLE”"));
        Assert.assertTrue("не появилось сообщение Показ всех 3 элементов", webDriver.findElement(searchResultsCountLocator)
                .getText().contains("Показ всех 3 элементов"));

    }


    // Footer Tests Переход в разделы сайта из меню футера

    //Переход в раздел Все товары
    @Test
    public void MainPage_ClickToFooterAllGoodsButton_GoesToAllGoodsPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(allGoodsButtonFooter).click();
        Assert.assertTrue("не произошел переход в раздел Все товары", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("ВСЕ ТОВАРЫ"));
    }

    //Переход в Корзину
    @Test
    public void MainPage_ClickToFooterCartButton_GoesToCartPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(cartButtonFooter).click();
        Assert.assertTrue("не произошел переход в раздел Корзина", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Корзина"));
    }

    //Переход на станицу Авторизации
    @Test
    public void MainPage_ClickToMyAccountButton_GoesToAuthorizationPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(myAccountButtonFooter).click();
        Assert.assertTrue("не произошел переход в раздел Авторизация", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Мой Аккаунт"));
    }

    //Переход на страницу регистрации
    @Test
    public void MainPage_ClickToRegisterButton_GoesToRegistrationPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(registrationButtonFooter).click();
        Assert.assertTrue("не произошел переход в раздел Регистрация", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Регистрация"));
    }


    //Переход на станицу Оформление заказа с пустой корзиной
    @Test
    public void MainPage_ClickToFooterOrderingButtonWithEmptyCart_GoesToCartPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(orderingButtonFooter).click();
        Assert.assertTrue("не произошел переход в раздел Корзина", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Корзина"));
    }

    //Переход на сатницу Оформление заказа с товаром в корзине
    @Test
    public void MainPage_ClickToFooterOrderingButtonWithFullCart_GoesToOrderingPage() throws Exception {
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
        webDriver.findElement(orderingButtonFooter).click();
        var sourceFile4 = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile4, new File("tmp\\screenshot4.png"));

        Assert.assertTrue("не произошел переход в Офоромление заказа", webDriver.findElement(currentPageSecondRowLocator)
                .getText().contains("Оформление Заказа"));

    }

    //Footer Contacts Раздел Контакты в футере
    @Test
    public void MainPage_FooterContactsInformation_PhoneAndEmailDisplayed() throws Exception{
        webDriver.get(baseUrl);
        WebElement footer = webDriver.findElement(footerSection);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", footer);
       Thread.sleep(10);
        Assert.assertTrue("не отобразился блок с контактами", webDriver.findElement(contactsInformation).isDisplayed());
    }


    //Main Tests Тесты соновной части сайта

    //Переход в раздел книги кликом на карточку Книги
    @Test
    public void MainPage_ClickToBooksSection_GoesToBooksCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(booksWatchSection).click();
        Assert.assertTrue("не произошел переход в раздел Книги", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("КНИГИ"));
    }

    //Переход в раздел Книги кликом на кнопку Просмотреть на карточке Книги
    @Test
    public void MainPage_ClickToBooksButton_GoesToBooksCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(booksWatchButton).click();
        Assert.assertTrue("не произошел переход в раздел Книги", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("КНИГИ"));
    }

    //Переход в раздел Планшеты кликом на карточку Планшеты
    @Test
    public void MainPage_ClickToTabletsSection_GoesToTabletsCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(tabletWatchSection).click();
        Assert.assertTrue("не произошел переход в раздел Планшеты", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("ПЛАНШЕТЫ"));
    }

    //Переход в раздел Планшеты кликм на кнопку Просмотреть в карточке Планшеты
    @Test
    public void MainPage_ClickToTabletsButton_GoesToTabletsCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(tabletsWatchButton).click();
        Assert.assertTrue("не произошел переход в раздел Планшеты", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("ПЛАНШЕТЫ"));
    }

    //Переход в раздел Фоттоаппараты кликом на карточку Фотоаппараты
    @Test
    public void MainPage_ClickToCamerasSection_GoesToCamerasCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(cameraWatchSection).click();
        Assert.assertTrue("не произошел переход в раздел Фотоаппараты", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("ФОТО/ВИДЕО"));
    }

    //Переход в раздел Фотоаппараты кликом на кнопку Просмотреть в карточке Фотоаппараты
    @Test
    public void MainPage_ClickToCamerasButton_GoesToCamerasCatalogPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(camerasWatchButton).click();
        Assert.assertTrue("не произошел переход в раздел Фотоаппараты", webDriver.findElement(currentPageFirstRowLocator)
                .getText().contains("ФОТО/ВИДЕО"));
    }

    //Переход в карточку главного рекламного товара iPad2020 кликом на баннер
    @Test
    public void MainPage_ClickToMainPromoIpadBanner_GoesToIpadPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(mainBanner).click();
        Assert.assertTrue("не произошел переход в карточку товара iPad 2020 32gb wi-fi", webDriver.findElement(productTitleLocator)
                .getText().contains("iPad 2020 32gb wi-fi"));
    }

    //Переход в карточку главного рекламного товара iPad 2020 кликом на кнопку Просмотреть
    @Test
    public void MainPage_ClickToMainPromoIpadBannerButton_GoesToIpadPage() {
        webDriver.get(baseUrl);
        webDriver.findElement(mainBannerButton).click();
        Assert.assertTrue("не произошел переход в карточку товара iPad 2020 32gb wi-fi", webDriver.findElement(productTitleLocator)
                .getText().contains("iPad 2020 32gb wi-fi"));
    }

    //Переход в карточку  товара раздела рапродажа
    @Test
    public void MainPage_ClickFirstSalesItem_GoesToFirstSalesItemCard() {
        webDriver.get(baseUrl);
        WebElement section1 = webDriver.findElement(sectionOne);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section1);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(thirdSaleItem));
        webDriver.findElement(thirdSaleItem).click();
       Assert.assertTrue("не произошел переход в карточку товара New White Woman’s Shorts", webDriver.findElement(productTitleLocator)
                .getText().contains("New White Woman’s Shorts"));
    }



    //Переход в карточку  товара раздела новинки
    @Test
    public void MainPage_ClickSecondLatestItem_GoesToFirstSalesItemCard() {
        webDriver.get(baseUrl);
        WebElement section2 = webDriver.findElement(sectionTwo);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItem));
        webDriver.findElement(secondLatestItem).click();
        Assert.assertTrue("не произошел переход в карточку товара Новый товар", webDriver.findElement(productTitleLocator)
                .getText().contains("Новый товар"));
    }



    //Отображение последнего просмотренного товара в разделе Просмотренные товары
    @Test
    public void MainPage_WatchLastViewedPinkShorts_PinkShortsDisplayedInLastViewedGoods() {
        webDriver.get(baseUrl);
        WebElement section2 = webDriver.findElement(sectionTwo);
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView();", section2);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(secondLatestItem));
        webDriver.findElement(secondLatestItem).click();
        webDriver.findElement(mainButtonHeader).click();
        Assert.assertTrue("товар Новый товар не отобразился в просмотренных товарах",
                webDriver.findElement(lastWatchedProductTitle).getText().contains("Новый товар"));
    }
}
