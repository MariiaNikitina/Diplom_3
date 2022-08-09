package com.ya;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class HeaderTest {

    private static final String STELLARBURGERS_URL = "https://stellarburgers.nomoreparties.site/";
    HeaderPage headerPage;
    UserAPI user;
    UserClientAPI userClientAPI = new UserClientAPI();

    @BeforeClass
    public static void setUpClass() {
        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        headerPage = open(STELLARBURGERS_URL, HeaderPage.class);
    }

    public void createUserWithAPI() {
        user = UserDataGenerator.getRandomData();
        userClientAPI.create(user);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        if(user!=null)
        userClientAPI.deleteUser(user.getLogin(), user.getPassword());
    }

    @DisplayName("login User From Header With Correct Credentials")
    @Description("Check the opportunity to login existed user via account button at the header.")
    @Test
    public void loginUserFromHeaderWithCorrectCreds() {
        createUserWithAPI();
        headerPage.clickAccountButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        loginPageBurger.fillUserCreds(user.getLogin(), user.getPassword());
        loginPageBurger.clickLoginButton();
        MainPageBurger mainPageBurger = page(MainPageBurger.class);
        assertThat("The button Войти isn't turned into Оформить заказ", mainPageBurger.getTextLoginButtonMainPage(), equalTo("Оформить заказ"));

    }

    @DisplayName("switch From Logo To Account Tab")
    @Description("Check that login page opens after click to account button at the header.")
    @Test
    public void switchFromLogoToAccountTab() {
        headerPage.clickAccountButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        assertTrue("Login button isn't displayed. User isn't switched to account tab.", loginPageBurger.isLoginButtonDisplayed());
        //assertThat("The URL isn't equal to login URL. User isn't switched to account tab.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/login"));
    }

    @DisplayName("switch From Account To Logo Tab")
    @Description("Check that main page opens after click to Logo button at the header.")
    @Test
    public void switchFromAccountToLogoTab() {
        headerPage.clickAccountButton();
        headerPage.clickMainLogoButton();
        MainPageBurger mainPageBurger = page(MainPageBurger.class);
        assertTrue("Ingredients menu aren't displayed. User isn't switched to main page.", mainPageBurger.areIngredientsDisplayed());
        //assertThat("The URL isn't equal to main app URL. User isn't switched to main page.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/"));
    }

    @DisplayName("switch From Account To Constructor Tab")
    @Description("Check that constructor(main) page opens after click to constructor button at the header.")
    @Test
    public void switchFromAccountToConstructorTab() {
        headerPage.clickAccountButton();
        headerPage.clickConstructorButton();
        MainPageBurger mainPageBurger = page(MainPageBurger.class);
        assertTrue("Ingredients menu aren't displayed. User isn't switched to constructor tab.", mainPageBurger.areIngredientsDisplayed());
        //assertThat("The URL isn't equal to main app URL. User isn't switched to constructor tab.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/"));
    }

}
