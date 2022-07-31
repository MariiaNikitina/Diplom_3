package com.ya;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPageBurger {
    //локатор поля имя
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]")//LINK_TEXT, using = "Имя")
    private SelenideElement userNameRegistration;
    //локатор поля емэйл
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]")//LINK_TEXT, using = "Имя")
    private SelenideElement userEmailRegistration;
    //локатор поля пароль
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement userPasswordRegistration;
    //локатор кнопки "зарегистрироваться"
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registrationButton;
    //локатор кнопки "Войти"
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginFromRegPageButton;
    //локатор сообщения об ошибке
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement passwordErrorMessage;

    public void fillUserName(String uName) {
        userNameRegistration.setValue(uName);
    }

    public void fillUserEmail(String uEmail) {
        userEmailRegistration.setValue(uEmail);
    }

    public void fillUserPassword(String uPassword) {
        userPasswordRegistration.setValue(uPassword);
    }

    public void fillUserData(String uName, String uEmail, String uPassword) {
        fillUserName(uName);
        fillUserEmail(uEmail);
        fillUserPassword(uPassword);
    }

    public void clickRegistrationButton() {
        registrationButton.scrollIntoView(true);
        registrationButton.click();
    }

    public void clickLoginFromRegPageButton() {
        loginFromRegPageButton.scrollIntoView(true);
        loginFromRegPageButton.click();
    }

    public boolean isPasswordErrorDisplayed() {
        return passwordErrorMessage.isDisplayed();
    }

    public String getPasswordErrorText() {
        return passwordErrorMessage.getText();
    }
}
