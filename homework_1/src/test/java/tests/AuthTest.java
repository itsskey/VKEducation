package tests;

import com.codeborne.selenide.Configuration;
import data.TestData;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

public class AuthTest extends BaseTest{
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();

    // Тест успешной авторизации
    @Test
    public void successfulLogin() {
        loginPage.openPage()
                .login(
                        TestData.VALID_LOGIN,
                        TestData.VALID_PASSWORD
                );
        mainPage.checkUserName(TestData.USER_NAME);
    }

    // Тест ошибки при неверных данных
    @Test
    public void invalidLoginError() {
        loginPage.openPage()
                .loginWithInvalidCreds(
                        TestData.INVALID_LOGIN,
                        TestData.INVALID_PASSWORD
                )
                .checkErrorMessage("Неправильно указан логин и/или пароль");
    }
}