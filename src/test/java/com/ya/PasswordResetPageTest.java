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

public class PasswordResetPageTest {
    private static final String PASSWORD_RESET_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    PasswordResetPage passwordResetPage;
    UserAPI user;
    UserClientAPI userClientAPI = new UserClientAPI();

    @BeforeClass
    public static void setUpClass() {
        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        passwordResetPage = open(PASSWORD_RESET_URL, PasswordResetPage.class);
    }

    public void createUserWithAPI() {
        user = UserDataGenerator.getRandomData();
        userClientAPI.create(user);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        if (user != null)
            userClientAPI.deleteUser(user.getLogin(), user.getPassword());
    }

    @DisplayName("user Can Login From Reset Page Button With Correct Creds")
    @Description("Check the opportunity to login existed user via login button at the reset password page.")
    @Test
    public void userCanLoginFromResetPageButtonWithCorrectCreds() {
        createUserWithAPI();
        passwordResetPage.clickLoginButtonFromResetPage();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        loginPageBurger.fillUserCreds(user.getLogin(), user.getPassword());
        loginPageBurger.clickLoginButton();
        MainPageBurger mainPageBurger = page(MainPageBurger.class);
        assertThat("The button Войти isn't turned into Оформить заказ", mainPageBurger.getTextLoginButtonMainPage(), equalTo("Оформить заказ"));
        // assertThat("The URL isn't equal to main page URL. User isn't switched to main page.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/"));
    }

}
