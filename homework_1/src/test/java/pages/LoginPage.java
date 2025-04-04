package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class LoginPage {
    //xpaths:
    private final SelenideElement loginField = $x("//*[@id=\"field_email\"]");
    private final SelenideElement passwordField = $x("//*[@id=\"field_password\"]");
    private final SelenideElement submitButton = $("input[data-l^='t,sign_in']");
    private final SelenideElement errorMessage = $(".input-e");

    public LoginPage verifyPageLoaded() {
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
        return this;
    }

    public MainPage login(String login, String password) {
        loginField.shouldBe(visible).setValue(login);
        passwordField.shouldBe(visible).setValue(password);
        submitButton.shouldBe(visible).click();
        return new MainPage();
    }

    public LoginPage loginWithInvalidCreds(String login, String password) {
        loginField.shouldBe(visible).setValue(login);
        passwordField.shouldBe(visible).setValue(password);
        submitButton.shouldBe(visible).click();
        return this;
    }

    public void checkErrorMessage(String text) {
        errorMessage.shouldHave(text(text));
    }
}