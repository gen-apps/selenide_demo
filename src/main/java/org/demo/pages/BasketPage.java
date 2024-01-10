package org.demo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class BasketPage extends MenuPage<BasketPage> {

    private static final String restaurantName = "div.basket-top-side > p > a";

    private static final String itemPriceSelector = "#basket_item_price";
    private static final String voucherCountSelector = "#basket_item_count";


    public SelenideElement getRestaurantName() {
        return $(restaurantName);
    }

    public SelenideElement getItemPriceElement() {
        return $(itemPriceSelector);
    }

    public SelenideElement getCountVoucher() {
        return $(voucherCountSelector);
    }
}
