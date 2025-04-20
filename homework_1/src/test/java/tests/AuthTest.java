package tests;

import data.AuthDataProvider;
import data.TestData;
import data.UserCredentials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Timeout(50)
public class AuthTest implements BaseTest{

    @Test
    @Tag("auth")
    @DisplayName("Тест успешной авторизации")
    public void testSuccessfulLogin() {
        String actualUserName = new LoginPage()
                .isLoaded()
                .login(TestData.VALID_USER)
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
                .isLoaded()
                .loginWithInvalidCreds(userWithWrongCredentials)
                .getErrorMessage();

        assertEquals(
                expectedError,
                actualError,
                "Сообщение об ошибке не совпадает"
        );
    }

}