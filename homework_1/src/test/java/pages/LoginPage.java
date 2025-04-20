package pages;

import com.codeborne.selenide.SelenideElement;
import data.UserCredentials;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage implements BasePage{
    //Локаторы:
    private final SelenideElement loginField = $x("//input[@name='st.email']");
    private final SelenideElement passwordField = $x("//*[@name='st.password']");
    private final SelenideElement submitButton = $x("//*[@value='Войти в Одноклассники']");
    private final SelenideElement errorMessage = $x("//*[@class='input-e login_error']");

    public LoginPage isLoaded() {
        checkElementVisibility(loginField, "Поле логина не отобразилось");
        checkElementVisibility(passwordField, "Поле пароля не отобразилось");
        return this;
    }

    public HomePage login(UserCredentials userCredentials) {
        loginField.setValue(userCredentials.login());
        passwordField.setValue(userCredentials.password());
        submitButton.click();
        return new HomePage().isLoaded();
    }

    public LoginPage loginWithInvalidCreds(UserCredentials userCredentials) {
        loginField.setValue(userCredentials.login());
        passwordField.setValue(userCredentials.password());
        submitButton.click();
        return this;
    }


    public String getErrorMessage() {
        checkElementVisibility(errorMessage,"Сообщение об ошибке не отображается");
        return errorMessage.getText();
    }
}