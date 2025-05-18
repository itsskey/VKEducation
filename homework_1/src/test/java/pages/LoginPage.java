package pages;

import com.codeborne.selenide.SelenideElement;
import data.UserCredentials;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage implements BasePage{
    //Локаторы:
    private static final SelenideElement LOGIN_FIELD = $x("//input[@name='st.email']");
    private static final SelenideElement PASSWORD_FIELD = $x("//*[@name='st.password']");
    private static final SelenideElement SUBMIT_BUTTON = $x("//*[contains(@value, 'Войти')]");
    private static final SelenideElement ERROR_MESSAGE = $x("//*[@class='input-e login_error']");

    public LoginPage() {
        isLoaded();
    }

    @Override
    public LoginPage isLoaded() {
        LOGIN_FIELD.shouldBe(visible.because("Поле логина не отобразилось"));
        PASSWORD_FIELD.shouldBe(visible.because("Поле пароля не отобразилось"));
        return this;
    }

    public String getErrorMessage() {
        ERROR_MESSAGE.shouldBe(visible.because("Сообщение об ошибке не отображается"));
        return ERROR_MESSAGE.getText();
    }

    public LoginPromise loginWith(UserCredentials credentials){
        return new LoginPromise(this, credentials);
    }

    // Использование паттерна Promise
    public static class LoginPromise{
        private final LoginPage LOGIN_PAGE;
        private final UserCredentials USER_CREDS;

        public LoginPromise(LoginPage loginPage, UserCredentials userCredentials){
            this.LOGIN_PAGE=loginPage;
            this.USER_CREDS=userCredentials;
        }

        private void performLoginActions(){
            LOGIN_PAGE.LOGIN_FIELD.setValue(USER_CREDS.login());
            LOGIN_PAGE.PASSWORD_FIELD.setValue(USER_CREDS.password());
            LOGIN_PAGE.SUBMIT_BUTTON.click();
        }

        public HomePage asValidUser(){
            performLoginActions();
            return new HomePage().isLoaded();
        }

        public LoginPage asInvalidUser(){
            performLoginActions();
            return LOGIN_PAGE;
        }
    }
}