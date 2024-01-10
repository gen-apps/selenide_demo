package org.demo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;

public class MainPage extends MenuPage<MainPage> {
    private static final String startPriceSelector = "#minprice";
    private static final String endPriceSelector = "#maxprice";
    private static final String searchSelector = ".submit-button";
    private static final String deleteSelector = "#sidebar > div > div > div > section > div > div.submit-form > div.delete-search-button";
    private static final String prices = "div.deal-description > div.voucher-counts > div.outs-and-shorts > div > p:nth-child(1)";

    private static final String locationSelector = "#sidebar > div > div > div > section > div > div.ms-parent.MultipleSelect.districts > button";
    private static final String locationListSelector = "#sidebar > div > div > div > section > div > div.ms-parent.MultipleSelect.districts > div > ul > li > label > span";

    private static final String productsSelector = "#partialid > section > div";


    @Step("fill 'start' price input field")
    public MainPage fillStartPrice(@NonNull String priceFrom) {
        $$(startPriceSelector)
                .last()
                .setValue(priceFrom);

        return self();
    }

    @Step("fill 'end' price input field")
    public MainPage fillEndPrice(@NonNull String priceTo) {
        $$(endPriceSelector)
                .last()
                .setValue(priceTo);

        return self();
    }


    @Step("click search button")
    public MainPage clickSearch() {
        $$(searchSelector)
                .last()
                .click();

        return self();
    }

    @Step("click delete button")
    public MainPage clickDelete() {
        $(deleteSelector).click();

        return self();
    }

    @Step("get first product")
    public Product getFirstProduct() {
        return getProducts().get(0);
    }

    public List<Product> getProducts() {
        return $$(productsSelector).asFixedIterable().stream()
                .map(Product::new)
                .toList();
    }

    @Step("get all items prices")
    public List<String> getPrices() {
        return $$(prices).texts();
    }

    @Step("fill location field")
    public MainPage fillLocation(@NonNull String... locations) {
        var e = getLocationElement();
        e.click();

        var foundLocations = $$(locationListSelector);
        Arrays.stream(locations)
                .forEach(it -> foundLocations.findBy(text(it)).click());

        e.click();

        return self();
    }

    @Step("get location element")
    public SelenideElement getLocationElement() {
        return $(locationSelector);
    }


    public record Product(SelenideElement element) {

        @Step("click the product")
        public ProductPage click() {
            element.click();

            return Page.loadModel(ProductPage.class);
        }
    }
}
