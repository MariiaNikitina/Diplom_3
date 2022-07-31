package com.ya;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class MainPageBurger {
    //локатор кнопки входа
    @FindBy(how = How.XPATH, using = ".//div[@class='BurgerConstructor_basket__container__2fUl3 mt-10']/button")
    private SelenideElement loginButtonMainPage;
    //локатор разделов "Булки" "Соусы" "Начинки"
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab__1SPyG')]")
    private ElementsCollection bunTab;
    //общий локатор ингредиентов
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__menuContainer__Xu3Mo")
    private SelenideElement ingredients;

    public boolean areIngredientsDisplayed() {
        ingredients.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return ingredients.isDisplayed();
    }

    public void clickLoginButtonMainPage() {
        loginButtonMainPage.click();
    }

    public String getTextLoginButtonMainPage() {
        loginButtonMainPage.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return loginButtonMainPage.getText();
    }

    public void clickBunTab() {
        bunTab.get(0).click();
    }

    public String getBunTabCurrentClass() {
        return bunTab.get(0).getAttribute("class");
    }

    public void clickSauceTab() {
        bunTab.get(1).click();
    }

    public String getSauceTabCurrentClass() {
        return bunTab.get(1).getAttribute("class");
    }

    public void clickToppingTab() {
        bunTab.get(2).click();
    }

    public String getToppingTabCurrentClass() {
        return bunTab.get(2).getAttribute("class");
    }

    public boolean isCurrentTabSelected(String tabClassSet) {
        return tabClassSet.contains("tab_tab_type_current__2BEPc");
    }
}
