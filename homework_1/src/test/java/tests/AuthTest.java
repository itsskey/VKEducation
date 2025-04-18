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

import static org.junit.jupiter.api.Assertions.assertEquals;


@Timeout(50)
public class AuthTest implements BaseTest{

    @Test
    @Tag("auth")
    @DisplayName("Тест успешной авторизации")
    public void testSuccessfulLogin() {
        String actualUserName = new LoginPage()
                .verifyPageLoaded()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD)
                .getUserName();

        assertEquals(
                TestData.USER_NAME,
                actualUserName,
                "Имя пользователя не совпадает"
        );
    }


    @DisplayName("Ошибка при неверных данных")
    @ParameterizedTest
    @Tag("auth")
    @MethodSource("invalidData")
    public void testInvalidLoginError(String login,
                                  String password,
                                  String expectedError) {
        String actualError = new LoginPage()
                .verifyPageLoaded()
                .loginWithInvalidCreds(
                        login, password
                )
                .getErrorMessage();

        assertEquals(
                expectedError,
                actualError,
                "Сообщение об ошибке не совпадает"
        );
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