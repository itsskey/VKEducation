package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class LoginPage {
    //xpaths:
    private final SelenideElement loginField = $x("//input[@name='st.email']");
    private final SelenideElement passwordField = $x("//*[@name='st.password']");
    private final SelenideElement submitButton = $x("//*[@value='Войти в Одноклассники']");
    private final SelenideElement errorMessage = $x("//*[@class='input-e login_error']");

    public LoginPage verifyPageLoaded() {
        loginField.shouldBe(visible.because("Не отобразилось поле для ввода логина"));;
        passwordField.shouldBe(visible.because("Не отобразилось поле для ввода пароля"));
        return this;
    }

    public HomePage login(String login, String password) {
        loginField.shouldBe(visible.because("Поле логина не отображается перед вводом")).setValue(login);
        passwordField.shouldBe(visible.because("Поле пароля не отображается перед вводом")).setValue(password);
        submitButton.shouldBe(visible.because("Кнопка входа не отображается")).click();
        return new HomePage();
    }

    public LoginPage loginWithInvalidCreds(String login, String password) {
        loginField.shouldBe(visible.because("Поле логина не отображается перед вводом")).setValue(login);
        passwordField.shouldBe(visible.because("Поле пароля не отображается перед вводом")).setValue(password);
        submitButton.shouldBe(visible.because("Кнопка входа не отображается")).click();
        return this;
    }

    public String getErrorMessage() {
        errorMessage.shouldBe(visible.because("Сообщение об ошибке не отображается"));
        return errorMessage.getText();
    }
}