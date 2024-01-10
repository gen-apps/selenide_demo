package org.demo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.checkerframework.checker.nullness.qual.NonNull;

public class LoginPage extends Page<LoginPage> {

    private static final String emailSelector = "#Email";
    private static final String passwordSelector = "#Password";
    private static final String loginSelector = "#LoginForm button";
    private static final String passwordRecoverySelector = "#LoginForm  a.text-black";
    private static final String createSelector = ".create a";

    @Step("fill email input field")
    public LoginPage fillEmail(@NonNull String username) {
        $(emailSelector).setValue(username);

        return self();
    }

    @Step("fill password input field")
    public LoginPage fillPassword(@NonNull String password) {
        $(passwordSelector).setValue(emailSelector);


        return self();
    }


    @Step("click create button")
    public CreateAccountPage clickCreate() {
        $(createSelector).click();

        return loadModel(CreateAccountPage.class);
    }


    public SelenideElement getPasswordRecoveryElement() {
        return $(passwordRecoverySelector);
    }

    @Step("click login button")
    public AnyPage clickLogin() {
        $(loginSelector).click();

        return Page.loadModel(AnyPage.class);
    }
}
