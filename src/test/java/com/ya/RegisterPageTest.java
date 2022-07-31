package com.ya;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RegisterPageTest {
    private static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";
    RegisterPageBurger registerPageBurger;
    UserAPI user;
    UserClientAPI userClientAPI = new UserClientAPI();

    @BeforeClass
    public static void setUpClass() {
        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        registerPageBurger =
                open(REGISTRATION_URL, RegisterPageBurger.class);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    public void createUserWithAPI() {
        user = UserDataGenerator.getRandomData();
        userClientAPI.create(user);
    }

    @DisplayName("user Registration With Correct Data Turns To Login Page")
    @Description("Check that registration with correct credentials is successful and after that user sees login page")
    @Test
    public void userRegistrationWithCorrectDataTurnsToLoginPage() {
        user = UserDataGenerator.getRandomData();
        registerPageBurger.fillUserData(user.getName(), user.getLogin(), user.getPassword());
        registerPageBurger.clickRegistrationButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        assertTrue("User doesn't see login button. User isn't switched to login page.", loginPageBurger.isLoginButtonDisplayed());
       // assertThat("The URL isn't equal to login URL. User isn't switched to login page.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/login"));
        userClientAPI.deleteUser(user.getLogin(), user.getPassword());
    }

    @DisplayName("user Registration With Small Password Shows Error Message")
    @Description("Check that registration with small password cannot be done. User sees error message.")
    @Test
    public void userRegistrationWithSmallPasswordShowsErrorMessage() {
        String smallPassword = RandomStringUtils.randomAlphabetic(5).toLowerCase();
        user = UserDataGenerator.getRandomData();
        registerPageBurger.fillUserData(user.getName(), user.getLogin(), smallPassword);
        registerPageBurger.clickRegistrationButton();
        assertTrue("User doesn't see error message", registerPageBurger.isPasswordErrorDisplayed());
        assertThat("Error message isn't equal to expected", registerPageBurger.getPasswordErrorText(), equalTo("Некорректный пароль"));
    }

    @DisplayName("user Can Login From Registration Page With Correct Creds")
    @Description("Check the opportunity to login existed user via login button at the register page.")
    @Test
    public void userCanLoginFromRegistrationPageWithCorrectCreds() {
        createUserWithAPI();
        registerPageBurger.clickLoginFromRegPageButton();
        LoginPageBurger loginPageBurger = page(LoginPageBurger.class);
        loginPageBurger.fillUserCreds(user.getLogin(), user.getPassword());
        loginPageBurger.clickLoginButton();
        MainPageBurger mainPageBurger = page(MainPageBurger.class);
        assertTrue("User doesn't see constructor of ingredients. User isn't switched to main page.", mainPageBurger.areIngredientsDisplayed());
        //assertThat("The URL isn't equal to main page URL. User isn't switched to main page.", WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/"));
        userClientAPI.deleteUser(user.getLogin(), user.getPassword());
    }
}
