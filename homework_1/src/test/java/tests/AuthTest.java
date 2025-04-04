package tests;

import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import java.util.stream.Stream;


@Timeout(50)
public class AuthTest extends BaseTest{

    @Test
    @Tag("auth")
    @DisplayName("Тест успешной авторизации")
    public void successfulLogin() {
        new LoginPage()
                .verifyPageLoaded()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD)
                .checkUserName(TestData.USER_NAME);
    }


    @DisplayName("Ошибка при неверных данных")
    @ParameterizedTest
    @Tag("auth")
    @MethodSource("invalidData")
    public void invalidLoginError(String login,
                                  String password,
                                  String expectedError) {
        new LoginPage()
                .verifyPageLoaded()
                .loginWithInvalidCreds(
                        login, password
                )
                .checkErrorMessage(expectedError);
    }
    private static Stream<Arguments> invalidData() {
        return Stream.of(
                Arguments.of(
                        TestData.INVALID_LOGIN,
                        TestData.INVALID_PASSWORD,
                        "Неправильно указан логин и/или пароль"
                ),
                Arguments.of(null,
                        null,
                        "Введите логин"),
                Arguments.of(TestData.INVALID_LOGIN,
                        null,
                        "Введите пароль")
        );
    }
}