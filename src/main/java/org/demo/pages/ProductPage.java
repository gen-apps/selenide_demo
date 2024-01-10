package org.demo.pages;

import io.qameta.allure.Step;

public class ProductPage extends MenuPage<ProductPage> {

    private static final String addVoucherSelector = "button.amount-box-button.amount-box-add";
    private static final String subtractVoucherSelector = "button.amount-box-button.amount-box-dec";
    private static final String addBasketSelector = ".addBasket";

    @Step("click add voucher")
    public ProductPage clickAddVoucher() {
        $(addVoucherSelector).click();

        return self();
    }


    @Step("click subtract voucher")
    public ProductPage clickSubtractVoucher() {
        $(subtractVoucherSelector).click();

        return self();
    }

    @Step("click Add Basket")
    public ProductPage clickAddBasket() {
        $(addBasketSelector).click();

        return self();
    }
}
