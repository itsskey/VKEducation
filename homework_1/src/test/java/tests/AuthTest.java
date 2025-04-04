package tests;

import data.TestData;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class AuthTest extends BaseTest{
    // Тест успешной авторизации
    @Test
    public void successfulLogin() {
        new LoginPage()
                .verifyPageLoaded()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD)
                .checkUserName(TestData.USER_NAME);
    }

    // Тест ошибки при неверных данных
    @Test
    public void invalidLoginError() {
        new LoginPage()
                .verifyPageLoaded()
                .loginWithInvalidCreds(
                        TestData.INVALID_LOGIN,
                        TestData.INVALID_PASSWORD
                )
                .checkErrorMessage("Неправильно указан логин и/или пароль");
    }
}