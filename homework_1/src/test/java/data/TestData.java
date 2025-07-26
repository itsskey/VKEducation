package data;

public class TestData {
    public static final UserCredentials VALID_USER =
            new UserCredentials("technopol71", "technopolisPassword");

    public static final UserCredentials INVALID_USER =
            new UserCredentials("invalid_login", "invalid_password");

    public static final UserCredentials EMPTY_CREDS =
            new UserCredentials("", "");

    public static final UserCredentials EMPTY_LOGIN =
            new UserCredentials(null, "invalid_password");

    public static final UserCredentials EMPTY_PASSWORD =
            new UserCredentials("invalid_login", null);

    public static final String USER_BIRTH_DATE_REGEX =
            "7 июня 2003 \\(\\d{1,2} (лет|год|года)\\)";//заменено на регулярное выражение для проверки возраста
    public static final int USER_BIRTH_YEAR = 2003;
    public static final String USER_BIRTH_MONTH = "JUNE";
    public static final int USER_BIRTH_DAY = 7;
    public static final String USER_NAME = "technopol71 technopol71";
}