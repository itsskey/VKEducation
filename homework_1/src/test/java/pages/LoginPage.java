package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class LoginPage {
    //xpaths:
    private final SelenideElement loginField = $x("//*[@id=\"field_email\"]");
    private final SelenideElement passwordField = $x("//*[@id=\"field_password\"]");
    private final SelenideElement submitButton = $("input[data-l^='t,sign_in']");
    private final SelenideElement errorMessage = $(".input-e");

    public LoginPage openPage() {
        Selenide.open("/");
        return this;
    }

    public MainPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
        return new MainPage();
    }

    public LoginPage loginWithInvalidCreds(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
        return this;
    }

    public void checkErrorMessage(String text) {
        errorMessage.shouldHave(text(text));
    }
}
