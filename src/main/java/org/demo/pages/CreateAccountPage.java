package org.demo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.checkerframework.checker.nullness.qual.NonNull;

import static com.codeborne.selenide.Condition.text;

public class CreateAccountPage extends Page<CreateAccountPage> {

    private static final String formTitleSelector = ".form-content h2";
    private static final String emailSelector = "#email";
    private static final String passwordSelector = "#password";
    private static final String repeatPasswordSelector = "#PasswordRetype";
    private static final String genderFemaleSelector = "#Gender2";
    private static final String genderMaleSelector = "#Gender1";
    private static final String firstNameSelector = "#name";
    private static final String lastNameSelector = "#surname";

    private static final String birthYearContSelector = "span[role=\"textbox\"]";
    private static final String birthYearSelector = "input[type=\"search\"]";
    private static final String birthYearListSelector = "ul[role=\"listbox\"] li";
    private static final String phoneNumberSelector = "#Phone";
    private static final String phoneNumberErrorSelector = "#input-error-phone";
    private static final String smsCodeSelector = "#PhoneCode";
    private static final String smsCodeErrorSelector = "#input-error-sms_code";
    private static final String termsAndConditionsSelector = ".check-radio .checkmark";
    private static final String privacyPolicySelector = ".checkbox-container .checkmark";
    private static final String registrationSelector = "#registrationBtn";

    public SelenideElement getFormTitleElement() {
        return $(formTitleSelector);
    }

    @Step("fill email")
    public CreateAccountPage fillEmail(@NonNull String email) {
        $(emailSelector).setValue(email);

        return self();
    }

    @Step("fill password input field")
    public CreateAccountPage fillPassword(@NonNull String password) {
        $(passwordSelector).setValue(password);


        return self();
    }

    @Step("fill repeat password input field")
    public CreateAccountPage fillRepeatPassword(@NonNull String repeatPassword) {
        $(repeatPasswordSelector).setValue(repeatPassword);

        return self();
    }

    @Step("click gender female checkbox")
    public CreateAccountPage clickGenderFemale() {
        $(genderFemaleSelector).click();

        return self();
    }

    @Step("click gender male checkbox")
    public CreateAccountPage clickGenderMale() {
        $(genderMaleSelector).click();

        return self();
    }

    @Step("fill first name input field")
    public CreateAccountPage fillFirstName(@NonNull String firstName) {
        $(firstNameSelector).setValue(firstName);

        return self();
    }

    @Step("fill last name input field")
    public CreateAccountPage fillLastName(@NonNull String lastName) {
        $(lastNameSelector).setValue(lastName);

        return self();
    }

    @Step("fill birth year input field")
    public CreateAccountPage fillBirthYear(@NonNull String birthYear) {
        $(birthYearContSelector).click(); // 1

        $(birthYearSelector).setValue(birthYear); // 2

        $$(birthYearListSelector) // 3
                .findBy(text(birthYear))
                .click();

        return self();
    }

    @Step("fill phone number input field")
    public CreateAccountPage fillPhoneNumber(@NonNull String phoneNumber) {
        $(phoneNumberSelector).setValue(phoneNumber);

        return self();
    }

    @Step("fill sms code input field")
    public CreateAccountPage fillSmsCode(@NonNull String otpCode) {
        $(smsCodeSelector).setValue(otpCode);

        return self();
    }

    @Step("click terms and conditions checkbox")
    public CreateAccountPage clickTermsAndConditions() {
        $(termsAndConditionsSelector).click();

        return self();
    }

    @Step("click privacy policy checkbox")
    public CreateAccountPage clickPrivacyPolicy() {
        $$(privacyPolicySelector).last().click();

        return self();
    }

    @Step("click registration button")
    public AnyPage clickRegistration() {
        $(registrationSelector).click();

        return Page.loadModel(AnyPage.class);
    }

    public SelenideElement getPhoneNumberErrorElement() {
        return $(phoneNumberErrorSelector);
    }

    public SelenideElement getSmsCodeError() {
        return $(smsCodeErrorSelector);
    }
}
