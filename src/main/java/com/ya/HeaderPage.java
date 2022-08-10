package com.ya;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPage {
    //локатор кнопки конструктор
    @FindBy(how = How.XPATH, using = ".//ul[@class='AppHeader_header__list__3oKJj']/li[1]")
    private SelenideElement constructorButton;
    //локатор кнопки аккаунта
    @FindBy(how = How.XPATH, using = ".//a[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']")
    private SelenideElement accountButton;
    //локатор кнопки logo
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement mainLogoButton;

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickMainLogoButton() {
        mainLogoButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }
}
