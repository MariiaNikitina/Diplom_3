package com.ya;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordResetPage {
    //локатор кнопки входа
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButtonFromResetPage;

    public void clickLoginButtonFromResetPage() {
        loginButtonFromResetPage.scrollIntoView(true);
        loginButtonFromResetPage.click();
    }
}
