package org.demo.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.demo.models.Client;
import org.demo.pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;

@Epic("Demo Test")
public class DemoTest {

    private static final int startPrice = 170;
    private static final int endPrice = 180;

    private static final String location = "თბილისი";

    private static final Client mClient = new Client(
            -1,
            "email@demo.org",
            "@Password1",
            "Sarah",
            "Smith",
            1985,
            "555555555"
    );


    @BeforeClass
    public void init() {
        Configuration.timeout = 15000;
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void beforeEach() {
        Selenide.open("https://www.swoop.ge/");
    }


    @Test(priority = 1)
    @Feature("login feature")
    @Stories({
            @Story("A Client should not be able to login with invalid date"),
            @Story("A client should be able to click the Password recovery link")
    })
    @Description("Login Form")
    @Severity(SeverityLevel.BLOCKER)
    public void firstTest() {
        Page.loadModel(MainPage.class)
                .clickLogin()
                .fillEmail(mClient.email())
                .fillPassword(mClient.password())
                .clickLogin()
                .job(this::checkErrorMessage)
                .job(this::checkRecoveryPasswordIsClickable);
    }

    @Test(priority = 2)
    @Feature("Registration feature")
    @Stories({
            @Story("A Client should be able to get to the registration page"),
            @Story("When Phone input field is empty then there must be a warning message below the field"),
            @Story("When Sms code input field is empty then there must be a warning message below the field")
    })
    @Description("Registration form")
    @Severity(SeverityLevel.CRITICAL)
    public void secondTest() {
        Page.loadModel(MainPage.class)
                .clickLogin()
                .clickCreate()
                .job(this::checkRegistrationPageIsVisible) // 1 check
                .fillEmail(mClient.email())
                .fillPassword(mClient.password())
                .fillRepeatPassword(mClient.password())
                .clickGenderMale()
                .fillFirstName(mClient.firstName())
                .fillLastName(mClient.lastName())
                .fillBirthYear(Integer.toString(mClient.birthYear()))
                .clickTermsAndConditions()
                .clickPrivacyPolicy()
                .clickRegistration()
                .job(this::checkPhoneNumberError) // 2 check
                .job(this::checkSmsCodeError); // 3 check
    }


    @Test(priority = 3, dependsOnMethods = "secondTest")
    @Description("Search Form")
    @Feature("Search Feature")
    @Story("Search result prices must be between the start price and the end price")
    @Severity(SeverityLevel.NORMAL)
    public void thirdTest() {
        Page.loadModel(MainPage.class)
                .clickRest()
                .fillStartPrice(Integer.toString(startPrice))
                .fillEndPrice(Integer.toString(endPrice))
                .clickSearch()
                .job(this::checkSearchResult);
    }


    @Test(priority = 4)
    @Features({
            @Feature("Search feature"),
            @Feature("Basket feature")
    })
    @Description("Add item into the basket")
    @Stories({
            @Story("Client should be able to clean the Search form when the client clicks delete button"),
            @Story("When client adds an item into the basket the item title must be valid"),
            @Story("When client adds an item into the basket the price must be correct"),
            @Story("When client adds an item into the basket the voucher value must be correct")
    })
    @Severity(SeverityLevel.NORMAL)
    public void fourthTest() {
        Page.loadModel(MainPage.class)
                .clickFood()
                .fillLocation(location)
                .clickDelete()
                .job(this::checkDeleteButton) // 1 check
                .getFirstProduct()
                .click()
                .clickAddVoucher()
                .clickAddBasket()
                .clickBasket()
                .job(this::checkRestaurantName) // 2 check
                .job(this::checkPrice) // 3 check
                .job(this::checkVoucher); // 4 check
    }


    private void checkVoucher(BasketPage it) {
        it.getCountVoucher()
                .should(have(text("2")));
    }

    private void checkPrice(BasketPage it) {
        it.getItemPriceElement()
                .should(have(text("10.00 ₾")));
    }

    private void checkRestaurantName(BasketPage it) {
        it.getRestaurantName()
                .should(have(text("პოტატო ორბელიანზე | Potato Orbeliani")));
    }

    private void checkDeleteButton(MainPage it) {
        it.getLocationElement()
                .shouldNot(have(text(location)));
    }

    private void checkSearchResult(MainPage restingPage) {
        var list = restingPage.getPrices()
                .stream()
                .map(it -> Integer.parseInt(it.replaceAll("[^0-9]", "")))
                .toList();

        var sa = new SoftAssert();

        sa.assertTrue(list.stream().allMatch(number -> number >= startPrice)
                , "Some prices are less than 'start' Price");

        sa.assertTrue(list.stream().allMatch(number -> number <= endPrice)
                , "Some prices are more than 'end' Price");

        sa.assertAll();
    }

    private void checkSmsCodeError(AnyPage ignore) {
        Page.loadModel(CreateAccountPage.class)
                .getSmsCodeError()
                .shouldHave(text("ჩაწერე SMS კოდი"));
    }

    private void checkPhoneNumberError(AnyPage ignore) {
        Page.loadModel(CreateAccountPage.class)
                .getPhoneNumberErrorElement()
                .shouldHave(text("ჩაწერე ტელეფონის ნომერი"));
    }

    private void checkRegistrationPageIsVisible(CreateAccountPage it) {
        it.getFormTitleElement().shouldBe(Condition.visible);
    }

    private void checkRecoveryPasswordIsClickable(AnyPage ignore) {
        Page.loadModel(LoginPage.class) // re create login page reference
                .getPasswordRecoveryElement()
                .shouldBe(and("clickable", Condition.visible, Condition.enabled));
    }

    private void checkErrorMessage(AnyPage anyPage) {
        anyPage.body()
                .shouldHave(text("მომხმარებელი ან პაროლი არასწორია"));
    }
}
