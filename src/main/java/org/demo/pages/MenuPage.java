package org.demo.pages;


import io.qameta.allure.Step;

public class MenuPage<T extends MenuPage<T>> extends Page<T> {

    private static final String loginSelector = ".swoop-login";
    private static final String restSelector = ".HeaderMenu a[href=\"/category/24/dasveneba\"]";
    private static final String foodSelector = ".HeaderMenu a[href=\"/category/15/restornebi-da-barebi\"]";
    private static final String basketSelector = "div.HeaderRightSide > div:nth-child(2) > a";


    @Step("click login")
    public LoginPage clickLogin() {
        $(loginSelector).click();

        return loadModel(LoginPage.class);
    }

    @Step("click rest")
    public MainPage clickRest() {
        $(restSelector).click();

        return loadModel(MainPage.class);
    }

    @Step("click food")
    public MainPage clickFood() {
        $(foodSelector).click();

        return loadModel(MainPage.class);
    }


    @Step
    public BasketPage clickBasket() {
        $(basketSelector).click();

        return loadModel(BasketPage.class);
    }


}
