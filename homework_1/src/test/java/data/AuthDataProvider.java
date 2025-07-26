package data;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.util.stream.Stream;

//Пример паттерна Data Provider
public class AuthDataProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        TestData.INVALID_USER,
                        "Неправильно указан логин и/или пароль"
                ),
                Arguments.of(TestData.EMPTY_CREDS,
                        "Введите логин"),
                Arguments.of(TestData.EMPTY_PASSWORD,
                        "Введите пароль"),
                Arguments.of(TestData.EMPTY_LOGIN,
                        "Введите логин")
        );
    }
}