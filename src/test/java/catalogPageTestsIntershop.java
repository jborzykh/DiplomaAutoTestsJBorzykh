

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;




/* тестовые сценарии
https://docs.google.com/spreadsheets/d/1z-lDKO7os-7nfmc0zbUV5mf5AFjKu4f0gnwNR2KxgyI/edit?usp=sharing

*/

public class catalogPageTestsIntershop {
    static WebDriver webDriver;
    static String baseUrl;
    static WebDriverWait webDriverWait;

    private By catalogMenu= By.cssSelector(".menu-item-46>a");
    private By subMenu = By.cssSelector("ul.sub-menu");
    private By appliancesSubMenu = By.cssSelector(".menu-item-119>.sub-menu");
    private By appliancesSubMenuButton = By.cssSelector(".menu-item-119>a");
    private By refrigeratorsSubMenuButton = By.cssSelector(".menu-item-120>a");
    private By electronicsSubMenuButton = By.cssSelector(".menu-item-47>a");
    private By electronicsSubMenu = By.cssSelector(".menu-item-47 .sub-menu");
    private By phonesSubMenuButton = By.cssSelector(".menu-item-114>a");
    private By clothsSubMenuButton = By.cssSelector(".menu-item-48>a");


    private By searchInputLocator = By.cssSelector("input.search-field");
    private By searchButtonLocator = By.cssSelector("button.searchsubmit");
    private By searchResultLocator = By.cssSelector(".entry-title.ak-container");
    private By searchResultCountLocator = By.cssSelector(".woocommerce-result-count");
    private By productTitleLocator = By.cssSelector(".product_title.entry-title");

    private By firstItemButtonToCart = By.cssSelector(".product:nth-child(1) .button");
    private By firstItemButtonMore = By.cssSelector(".added_to_cart");
    private By currentPageLocator = By.cssSelector("span.current");
    private By currentPageNumberLocator = By.cssSelector(".woocommerce-breadcrumb > span");


    //разделы Каталога
    private By refrigeratorsLocator = By.linkText("Холодильники");
    private By electronicsLocator = By.linkText("Электроника");
    private By phonesLocator = By.linkText("Телефоны");
    private By washingMachinesLocator = By.linkText("Стиральные машины");

    //сортировка
    private By selectSortingType = By.cssSelector(".orderby");
    private By mostPopular = By.cssSelector("[value='popularity']");
    private By cheapestItem = By.cssSelector("[value='price']");
    private By mostExpensiveItem = By.cssSelector("[value='price-desc']");
    private By firstItemCard = By.cssSelector("ul.products>li:first-child");

    //страницы каталога
    private By previousPageArrow = By.cssSelector("a.prev");
    private By nextPageArrow = By.cssSelector(".next");
    private By firstPageOfCatalog = By.linkText("1");
    private By secondPageOfCatalog = By.linkText("2");
    private By thirdPageOfCatalog = By.linkText("3");
    private By fourthPageOfCatalog = By.linkText("4");


    @Before
    public  void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        baseUrl = "http://intershop5.skillbox.ru/product-category/catalog/";
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 10);
    }



    @After
    public  void teardown()  {
        webDriver.quit();
    }

    //Поиск товаров по наименованию Холодильник
    @Test
    public void CatalogPage_SearchRefrigeratorsFromSearchInput_FoundFiveRefrigerators() {
        webDriver.get(baseUrl);
        webDriver.findElement(searchInputLocator).sendKeys("Холодильник");
        webDriver.findElement(searchButtonLocator).click();

        Assert.assertTrue("не найдены товары раздела Холодильник", webDriver.findElement(searchResultLocator)
                .getText().contains("РЕЗУЛЬТАТЫ ПОИСКА: “ХОЛОДИЛЬНИК”"));
        Assert.assertTrue("найдено неправильное количество товаров раздела Холодильник",
                webDriver.findElement(searchResultCountLocator).getText().contains
                        ("Показ всех 5 элементов"));
    }

    //Поиск товара по частичному наименованию "БИРЮСА Б-M10"
    @Test
    public void CatalogPage_SearchRefrigeratorByNameBiryusaFromSearchInput_FoundRefrigeratorBiryusa() {
        webDriver.get(baseUrl);
        webDriver.findElement(searchInputLocator).sendKeys("БИРЮСА Б-M10");
        webDriver.findElement(searchButtonLocator).click();

        Assert.assertTrue("не найдены товар БИРЮСА Б-M10", webDriver.findElement(productTitleLocator)
                .getText().contains("БИРЮСА Б-M10"));

    }

    //Просмотр товаров категории Холодильники кликом на левое меню
    @Test
    public void CatalogPage_ViewingRefrigeratorsFromLeftCatalogMenu_FoundFiveRefrigerators() {
        webDriver.get(baseUrl);
        webDriver.findElement(refrigeratorsLocator).click();
        Assert.assertTrue("не найдены товары раздела Холодильник", webDriver.findElement(searchResultLocator)
                .getText().contains("ХОЛОДИЛЬНИКИ"));
        Assert.assertTrue("найдено неправильное количество товаров раздела Холодильник",
                webDriver.findElement(searchResultCountLocator).getText().contains
                        ("Показ всех 5 элементов"));
    }

    //Просмотр самого популярного товара
    @Test
    public void CatalogPage_ViewingMostPopularItem_GoesToLEDLGTV() {
        webDriver.get(baseUrl);
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(mostPopular).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар LED телевизор LG 65NANO956NA Ultra HD 8K", webDriver.findElement(productTitleLocator)
                .getText().contains("LED телевизор LG 65NANO956NA Ultra HD 8K"));

    }

    //Просмотр самого дорогого товара
    @Test
    public void CatalogPage_ViewingMostExpensiveItem_GoesToLEDLGTV() {
        webDriver.get(baseUrl);
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(mostExpensiveItem).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар LED телевизор LG 65NANO956NA Ultra HD 8K", webDriver.findElement(productTitleLocator)
                .getText().contains("LED телевизор LG 65NANO956NA Ultra HD 8K"));

    }

    //Просмотр самого дешевого товара
    @Test
    public void CatalogPage_ViewingCheapest_Item_GoesToNewItemWithPriceZero() {
        webDriver.get(baseUrl);
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(cheapestItem).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар Новый товар 3.14231", webDriver.findElement(productTitleLocator)
                .getText().contains("Новый товар 3.14231"));

    }

    //Просмотр самого популярного товара в разделе Электроника
    @Test
    public void CatalogPage_ViewingMostPopularItemAmongElectronics_GoesToLEDLGTV() {
        webDriver.get(baseUrl);
        webDriver.findElement(electronicsLocator);
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(mostPopular).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар LED телевизор LG 65NANO956NA Ultra HD 8K", webDriver.findElement(productTitleLocator)
                .getText().contains("LED телевизор LG 65NANO956NA Ultra HD 8K"));

    }

    //Просмотр самого дорогого товара в разделе Телефоны
    @Test
    public void CatalogPage_ViewingMostExpensiveItemAmongPhones_GoesToOnePlus8Pro() {
        webDriver.get(baseUrl);
        webDriver.findElement(phonesLocator).click();
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(mostExpensiveItem).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар OnePlus 8 Pro", webDriver.findElement(productTitleLocator)
                .getText().contains("OnePlus 8 Pro"));

    }

    //Просмотр самого дешевого товара в разделе Стиральные машины
    @Test
    public void CatalogPage_ViewingCheapestItemAmongWashingMachines_GoesToCANDYCS4() {
        webDriver.get(baseUrl);
        webDriver.findElement(washingMachinesLocator).click();
        webDriver.findElement(selectSortingType).click();
        webDriver.findElement(cheapestItem).click();
        webDriver.findElement(firstItemCard).click();

        Assert.assertTrue("не найдены товар CANDY CS4 1061D1/2-07", webDriver.findElement(productTitleLocator)
                .getText().contains("CANDY CS4 1061D1/2-07"));

    }

    //Добавление тпервого товара из каталога в корзину и переход в корзину кнпкой подробнее на товаре
    @Test
    public void CatalogPage_AddingItemToCartClickingMoreButton_GoesToCart() {
        webDriver.get(baseUrl);
        webDriver.findElement(firstItemButtonToCart).click();
        webDriver.findElement(firstItemButtonMore).click();
        Assert.assertTrue("не произошел переход в раздел Корзина", webDriver.findElement(currentPageLocator)
                .getText().contains("Корзина"));


    }

    //переключение по страницам каталога нажатием на кнопки 2,3,4
    @Test
    public void CatalogPage_SwitchAmongCatalogPagest_SwithToPages1234() {
        webDriver.get(baseUrl);
        webDriver.findElement(secondPageOfCatalog).click();
        Assert.assertTrue("не произошел переход на вторую страницу каталога", webDriver.findElement(currentPageNumberLocator)
                .getText().contains("2"));
        webDriver.findElement(thirdPageOfCatalog).click();
        Assert.assertTrue("не произошел переход на третью страницу каталога", webDriver.findElement(currentPageNumberLocator)
                .getText().contains("3"));
        webDriver.findElement(fourthPageOfCatalog).click();
        Assert.assertTrue("не произошел переход на четвертую страницу каталога", webDriver.findElement(currentPageNumberLocator)
                .getText().contains("4"));
    }

    //Переход на следующуюю страницу каталога нажатием на кнопку next
    @Test
    public void CatalogPage_SwitchToNextPageWithNextArrowButton_GoesToNextPage(){
        webDriver.get(baseUrl);
        webDriver.findElement(nextPageArrow).click();
        Assert.assertTrue("не произошел переход на следующуюю страницу каталога", webDriver.findElement(currentPageNumberLocator)
        .getText().contains("2"));

    }

    //Перехд на предыдущуюю страницу каталога нажатием на кнопку prev
    @Test
    public void CatalogPage_SwitchToPreviousPageWithPrevArrowButton_GoesToPreviousPage(){
        webDriver.get(baseUrl);
        webDriver.findElement(fourthPageOfCatalog).click();
        webDriver.findElement(previousPageArrow).click();
        Assert.assertTrue("не произошел переход на предыдущуюю страницу каталога", webDriver.findElement(currentPageNumberLocator)
                .getText().contains("3"));
    }

    //Переход в раздел Одежда из Субменю Каталога
    @Test
    public void CatalogPage_ClickOnCatalogSubMenuCloths_GoesToClothsPage(){
        webDriver.get(baseUrl);
        WebElement catalog = webDriver.findElement(catalogMenu);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(catalog).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(subMenu));
        webDriver.findElement(clothsSubMenuButton).click();
        Assert.assertTrue("не произошел переход в раздел Одежда", webDriver.findElement(searchResultLocator)
        .getText().contains("ОДЕЖДА"));
    }

    //Переход в раздел Холодильники из Субменю каталога
    @Test
    public void CatalogPage_ClickOnCatalogAppliancesSubMenuRefrigerators_GoesToRefrigeratorsPage(){
        webDriver.get(baseUrl);
        WebElement catalog = webDriver.findElement(catalogMenu);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(catalog).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(subMenu));
        WebElement appliances = webDriver.findElement(appliancesSubMenuButton);
        Actions action2 = new Actions(webDriver);
        action2.moveToElement(appliances).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(appliancesSubMenu));
        webDriver.findElement(refrigeratorsSubMenuButton).click();
        Assert.assertTrue("не произошел переход в раздел Холодильники", webDriver.findElement(searchResultLocator)
                .getText().contains("ХОЛОДИЛЬНИКИ"));
    }

    //Переход в раздел Телефоны из Субменю Каталога
    @Test
    public void CatalogPage_ClickOnCatalogElectronicsSubMenuPhones_GoesToPhonesPage(){
        webDriver.get(baseUrl);
        WebElement catalog = webDriver.findElement(catalogMenu);
        Actions action1 = new Actions(webDriver);
        action1.moveToElement(catalog).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(subMenu));
        WebElement electronics = webDriver.findElement(electronicsSubMenuButton);
        Actions action2 = new Actions(webDriver);
        action2.moveToElement(electronics).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(electronicsSubMenu));
        webDriver.findElement(phonesSubMenuButton).click();
        Assert.assertTrue("не произошел переход в раздел Телефоны", webDriver.findElement(searchResultLocator)
                    .getText().contains("ТЕЛЕФОНЫ"));
    }

}
