package data;

public class TestData {
    public static final UserCredentials VALID_USER =
            new UserCredentials("technopol44", "technopolisPassword");

    public static final UserCredentials INVALID_USER =
            new UserCredentials("invalid_login", "invalid_password");

    public static final UserCredentials EMPTY_CREDS =
            new UserCredentials("", "");

    public static final UserCredentials EMPTY_LOGIN =
            new UserCredentials(null, "invalid_password");

    public static final UserCredentials EMPTY_PASSWORD =
            new UserCredentials("invalid_login", null);

    public static final String USER_BIRTH_DATE_REGEX = "9 марта 1997 \\(\\d{1,2} лет\\)"; //заменено на регулярное выражение для проверки возраста
    public static final int USER_BIRTH_YEAR = 1997;
    public static final String USER_NAME = "technopol44 technopol44";
}