package tests;

import data.AuthDataProvider;
import data.TestData;
import data.UserCredentials;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest extends BaseTest{

    @Test
    @Tag("auth")
    @DisplayName("Тест успешной авторизации")
    public void testSuccessfulLogin() {
        String actualUserName = new LoginPage()
                .loginWith(TestData.VALID_USER)
                .asValidUser()
                .getSidebar()
                .getUserName();

        assertEquals(
                TestData.USER_NAME,
                actualUserName,
                "Имя пользователя не совпадает"
        );
    }


    @DisplayName("Ошибка при неверных данных")
    @ParameterizedTest
    @ArgumentsSource(AuthDataProvider.class)
    @Tag("auth")
    public void testInvalidLoginError(UserCredentials userWithWrongCredentials,
                                      String expectedError) {
        String actualError = new LoginPage()
                .loginWith(userWithWrongCredentials)
                .asInvalidUser()
                .getErrorMessage();

        assertEquals(
                expectedError,
                actualError,
                "Сообщение об ошибке не совпадает"
        );
    }

}