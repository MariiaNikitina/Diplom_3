package com.ya;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class LoginPageBurger {
    //Локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;
    //Локатор поля логин
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement userEmailLogin;
    //Локатор поля пароль
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement userPasswordLogin;

    public boolean isLoginButtonDisplayed() {
        loginButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return loginButton.isDisplayed();
    }

    public void fillUserEmailLoginPage(String uEmail) {
        userEmailLogin.setValue(uEmail);
    }

    public void fillUserPasswordLoginPage(String uPassword) {
        userPasswordLogin.setValue(uPassword);
    }

    public void fillUserCreds(String uEmail, String uPassword) {
        fillUserEmailLoginPage(uEmail);
        fillUserPasswordLoginPage(uPassword);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
