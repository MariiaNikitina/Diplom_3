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

public class AccountPageTest {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    UserAPI user;
    UserClientAPI userClientAPI = new UserClientAPI();
    AccountPageBurger accountPageBurger;
    HeaderPage headerPage;

    @BeforeClass
    public static void setUpClass() {
        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        createUserWithAPI();
        headerPage =
                open(MAIN_PAGE_URL, HeaderPage.class);
        loginUser();
    }

    public void createUserWithAPI() {
        user = UserDataGenerator.getRandomData();
        userClientAPI.create(user);
    }

    public void loginUser() {
        headerPage.clickAccountButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        loginPageBurger.fillUserCreds(user.getLogin(), user.getPassword());
        loginPageBurger.clickLoginButton();
    }

    @After
    public void tearDown() {
        userClientAPI.deleteUser(user.getLogin(), user.getPassword());
        closeWebDriver();
    }

    @DisplayName("quit Button From Account Page Switching To Login Page")
    @Description("Check that after clicking to quit button user is switched to login window")
    @Test
    public void quitButtonFromAccountPageSwitchingToLoginPage() {
        headerPage.clickAccountButton();
        accountPageBurger = page(AccountPageBurger.class);
        accountPageBurger.clickQuitButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        assertTrue("User doesn't see login button. User isn't switched to login page.", loginPageBurger.isLoginButtonDisplayed());
        //assertThat("The URL isn't equal to login URL. User isn't switched to login page.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/login"));
    }
}
