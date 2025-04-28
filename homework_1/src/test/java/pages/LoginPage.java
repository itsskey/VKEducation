package pages;

import com.codeborne.selenide.SelenideElement;
import data.UserCredentials;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage implements BasePage{
    //Локаторы:
    private final SelenideElement LOGIN_FIELD = $x("//input[@name='st.email']");
    private final SelenideElement PASSWORD_FIELD = $x("//*[@name='st.password']");
    private final SelenideElement SUBMIT_BUTTON = $x("//*[@value='Войти в Одноклассники']");
    private final SelenideElement ERROR_MESSAGE = $x("//*[@class='input-e login_error']");

    public LoginPage() {
        isLoaded();
    }

    public LoginPage isLoaded() {
        LOGIN_FIELD.shouldBe(visible.because("Поле логина не отобразилось"));
        PASSWORD_FIELD.shouldBe(visible.because("Поле пароля не отобразилось"));
        return this;
    }

    public LoginPromise loginWith(UserCredentials credentials){
        return new LoginPromise(this, credentials);
    }

    public String getErrorMessage() {
        ERROR_MESSAGE.shouldBe(visible.because("Сообщение об ошибке не отображается"));
        return ERROR_MESSAGE.getText();
    }


    // Использование паттерна Promise
    public static class LoginPromise{
        private final LoginPage LOGIN_PAGE;
        private final UserCredentials USER_CREDS;

        public LoginPromise(LoginPage loginPage, UserCredentials userCredentials){
            this.LOGIN_PAGE=loginPage;
            this.USER_CREDS =userCredentials;
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