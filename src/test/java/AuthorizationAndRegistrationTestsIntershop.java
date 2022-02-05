/* тестовые сценарии
https://docs.google.com/spreadsheets/d/1PDb0cgEVDml8MfvUXhTni4Es6i3tWatm7O6gJSa_5L8/edit#gid=0

*/

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthorizationAndRegistrationTestsIntershop {
    static WebDriver webDriver;
    static String baseUrl;
    static WebDriverWait webDriverWait;

    private By myAccountButtonHeader = By.cssSelector(".menu-item-30 a");
    private By enterAccountLocator = By.cssSelector(".login-woocommerce a.account");
    private By registrationButtonFooter = By.cssSelector(".page-item-141 a");
    private By registerButtonInAuthForm = By.cssSelector(".custom-register-button");
    private By myAccountButtonFooter = By.cssSelector(".page-item-22 a");

    private By userNameInputRegistrationForm = By.id("reg_username");
    private By emailInputRegistrationForm = By.id("reg_email");
    private By passwordInputRegistrationForm = By.id("reg_password");
    private By registerButtonRegistrationForm = By.cssSelector(".woocommerce-Button");
    private By registrationFormMessage = By.cssSelector(".content-page");
    private By errorMessage = By.cssSelector(".woocommerce-error");

    private By userNameInputAuthForm = By.id("username");
    private By passwordInputAuthForm = By.id("password");
    private By logInButtonAuthForm = By.cssSelector(".woocommerce-button");
    private By userNameMyAccount = By.cssSelector(".woocommerce-MyAccount-content strong");
    private By helloUserLocator = By.cssSelector(".user-name");
    private By forgotPasswordButton = By.cssSelector(".woocommerce-LostPassword > a");
    private By forgotPasswordInput = By.cssSelector(".woocommerce-Input");
    private By passwordResetMessage = By.cssSelector(".woocommerce-message");





    String uuid = UUID.randomUUID().toString().replace("-","").substring(0,8);



    @Before
    public  void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        baseUrl = "http://intershop5.skillbox.ru/";
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 10);
        webDriver.get(baseUrl);
    }



    @After
    public void teardown()  {
        webDriver.quit();
    }

    //Регистрация нового пользователя из меню хедера со всеми заполненными полями
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationFromHeaderMyAccountWithAllFieldsFull_RegistrationComplete(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

    Assert.assertTrue("пользователь не был зарегистрирован", webDriver.findElement(registrationFormMessage)
            .getText().contains("Регистрация завершена"));
}
//Регистрация нового пользователя через кнопку Войти в хедере со всеми заполненными полями
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationFromEnterHeaderButtonWithAllFieldsFull_RegistrationComplete(){
        webDriver.findElement(enterAccountLocator).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("пользователь не был зарегистрирован", webDriver.findElement(registrationFormMessage)
                .getText().contains("Регистрация завершена"));
    }
//Регистрация нового пользователя через кнопку Регистрация в футере
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationFromFooterWithAllFieldsFull_RegistrationComplete(){
        webDriver.findElement(registrationButtonFooter).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("пользователь не был зарегистрирован", webDriver.findElement(registrationFormMessage)
                .getText().contains("Регистрация завершена"));
    }
//Регистрация нового пользователя с незаполненным именем пользователя
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationWithoutUserName_UserNameRequiredMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue(" не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Пожалуйста введите корректное имя пользователя."));
    }

    //Регистрация нового пользователя с незаполненным e-mail
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationWithoutEmail_EmailRequiredMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Пожалуйста, введите корректный email."));
    }

    //Регистрация нового пользователя с незаполненнмым паролем
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationWithoutPassword_PasswordRequiredMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Введите пароль для регистрации."));
    }

    //Регистрация нового пользователя с уже зарегистрированным именем пользователя
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationWithAlreadyRegisteredUserName_UserAlreadyExistMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys("julia2407");
        webDriver.findElement(emailInputRegistrationForm).sendKeys(uuid+"@"+uuid+".ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Учетная запись с таким именем пользователя уже зарегистрирована."));
    }

    //Регистрация нового пользователя с уже зарегистрированным e-mail
    @Test
    public void AuthorizationAndRegistrationPage_NewUserRegistrationWithAlreadyRegisteredEmail_EmailAlreadyExistMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(registerButtonInAuthForm).click();

        webDriver.findElement(userNameInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(emailInputRegistrationForm).sendKeys("julia2407@2407.ru");
        webDriver.findElement(passwordInputRegistrationForm).sendKeys(uuid);
        webDriver.findElement(registerButtonRegistrationForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Учетная запись с такой почтой уже зарегистировавана."));
    }


    // Авторизация зарегистрированного пользователя из меню Хедера Мой аккаунт
    @Test
    public void AuthorizationAndRegistrationPage_LoginOfRegisteredUserFromHeaderNavWithAllFields_SuccessLogin(){
        webDriver.findElement(myAccountButtonHeader).click();
        var userName = "julia2407";
        var password = "julia2407";
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        var actualUserName = webDriver.findElement(userNameMyAccount).getText();
        var helloUser = webDriver.findElement(helloUserLocator).getText();
        Assert.assertTrue(String.format("неправильное имя пользователя", actualUserName,userName), actualUserName.contains(userName));
        Assert.assertTrue(String.format("неправильное имя пользователя Привет", actualUserName,helloUser), helloUser.contains(userName));
    }

    // Авторизация зарегистрированного пользователя кнопкой Войти в хедере
    @Test
    public void AuthorizationAndRegistrationPage_LoginOfRegisteredUserFromEnterButtonWithAllFields_SuccessLogin(){
        webDriver.findElement(enterAccountLocator).click();
        var userName = "julia2407";
        var password = "julia2407";
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        var actualUserName = webDriver.findElement(userNameMyAccount).getText();
        var helloUser = webDriver.findElement(helloUserLocator).getText();
        Assert.assertTrue(String.format("неправильное имя пользователя", actualUserName,userName), actualUserName.contains(userName));
        Assert.assertTrue(String.format("неправильное имя пользователя Привет", actualUserName,helloUser), helloUser.contains(userName));
    }

    // Авторизация зарегистрированного пользователя из меню футера
    @Test
    public void AuthorizationAndRegistrationPage_LoginOfRegisteredUserFromFooterWithAllFields_SuccessLogin(){
        webDriver.findElement(myAccountButtonFooter).click();
        var userName = "julia2407";
        var password = "julia2407";
        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();
        var actualUserName = webDriver.findElement(userNameMyAccount).getText();
        var helloUser = webDriver.findElement(helloUserLocator).getText();
        Assert.assertTrue(String.format("неправильное имя пользователя", actualUserName,userName), actualUserName.contains(userName));
        Assert.assertTrue(String.format("неправильное имя пользователя Привет", actualUserName,helloUser), helloUser.contains(userName));
    }

    //Авторизация с несуществующим именем пользователя
    @Test
    public void AuthorizationAndRegistrationPage_LoginWithUnregisteredUserName_IncorrectUserNameMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys(uuid);
        webDriver.findElement(passwordInputAuthForm).sendKeys(uuid);
        webDriver.findElement(logInButtonAuthForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты."));
    }


    //Авторизация с неправильным паролем
    @Test
    public void AuthorizationAndRegistrationPage_LoginWithWrogPassword_IncorrectPasswordMessage(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(userNameInputAuthForm).sendKeys("julia2407");
        webDriver.findElement(passwordInputAuthForm).sendKeys(uuid);
        webDriver.findElement(logInButtonAuthForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Веденный пароль для пользователя julia2407 неверный."));
    }

    // Авторизация зарегистрированного пользователя без ввода Имени пользователя
    @Test
    public void AuthorizationAndRegistrationPage_LoginOfRegisteredUserFromHeaderNavWithoutUserName_ErrorMessageUserNameRequired(){
        webDriver.findElement(myAccountButtonHeader).click();
        var password = "julia2407";
        webDriver.findElement(passwordInputAuthForm).sendKeys(password);
        webDriver.findElement(logInButtonAuthForm).click();

        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Error: Имя пользователя обязательно."));

    }
    // Авторизация зарегистрированного пользователя без ввода пароля
    @Test
    public void AuthorizationAndRegistrationPage_LoginOfRegisteredUserFromHeaderNavWithoutPassword_ErrorMessagePasswordRequired() {
        webDriver.findElement(myAccountButtonHeader).click();
        var userName = "julia2407";

        webDriver.findElement(userNameInputAuthForm).sendKeys(userName);

        webDriver.findElement(logInButtonAuthForm).click();
        Assert.assertTrue("не появилось сообщение об ошибке", webDriver.findElement(errorMessage)
                .getText().contains("Пароль обязателен."));
    }

    //Восстановление пароля в форме авторизации
    @Test
    public void AuthorizationAndRegistrationPage_UserLoginFromRegistrationFormWhenEnteredAlreadyExistingEmail_SuccsessLogin(){
        webDriver.findElement(myAccountButtonHeader).click();
        webDriver.findElement(forgotPasswordButton).click();
        webDriver.findElement(forgotPasswordInput).sendKeys("julia2407");
        webDriver.findElement(registerButtonRegistrationForm).click();
        Assert.assertTrue("не появилось сообщение о сбросе пароля на почту", webDriver.findElement(passwordResetMessage)
                .getText().contains("Password reset email has been sent."));

    }

}