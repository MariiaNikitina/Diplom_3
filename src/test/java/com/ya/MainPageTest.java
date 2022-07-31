package com.ya;

import com.codeborne.selenide.Configuration;
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

public class MainPageTest {
    private static final String STELLARBURGERS_URL = "https://stellarburgers.nomoreparties.site/";
    MainPageBurger mainPageBurger;
    UserAPI user;
    UserClientAPI userClientAPI = new UserClientAPI();

    @BeforeClass
    public static void setUpClass() {
        Configuration.browser = "chrome";
        // Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        mainPageBurger = open(STELLARBURGERS_URL, MainPageBurger.class);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    public void createUserWithAPI() {
        user = UserDataGenerator.getRandomData();
        userClientAPI.create(user);
    }

    @DisplayName("login From Main Page With Correct Credentials")
    @Description("Check the opportunity to login existed user via login button at the main page.")
    @Test
    public void loginFromMainPageWithCorrectCredentials() {
        createUserWithAPI();
        mainPageBurger.clickLoginButtonMainPage();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        loginPageBurger.fillUserCreds(user.getLogin(), user.getPassword());
        loginPageBurger.clickLoginButton();
        assertThat("The button Войти isn't turned into Оформить заказ", mainPageBurger.getTextLoginButtonMainPage(), equalTo("Оформить заказ"));

    }

    @DisplayName("check Switch From Bun To Toppings")
    @Description("Check that after clicking to Topping tab it turns to selected tab")
    @Test
    public void checkSwitchFromBunToToppings() {
        mainPageBurger.clickToppingTab();
        assertTrue(mainPageBurger.isCurrentTabSelected(mainPageBurger.getToppingTabCurrentClass()));

    }

    @DisplayName("check Switch From Bun To Sauce")
    @Description("Check that after clicking to Sauce tab it turns to selected tab")
    @Test
    public void checkSwitchFromBunToSauce() {
        mainPageBurger.clickSauceTab();
        assertTrue(mainPageBurger.isCurrentTabSelected(mainPageBurger.getSauceTabCurrentClass()));
    }

    @DisplayName("check Switch From Sauce To Bun")
    @Description("Check that after clicking to Bun tab it turns to selected tab")
    @Test
    public void checkSwitchFromSauceToBun() {
        mainPageBurger.clickSauceTab();
        mainPageBurger.clickBunTab();
        assertTrue(mainPageBurger.isCurrentTabSelected(mainPageBurger.getBunTabCurrentClass()));
    }

}
