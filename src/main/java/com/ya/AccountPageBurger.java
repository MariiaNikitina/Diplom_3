package com.ya;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPageBurger {
    //локатор кнопки выход
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'Account_list__3KQQf')]/li)[3]")
    private SelenideElement quitButton;

    public void clickQuitButton() {
        quitButton.click();
    }
}
