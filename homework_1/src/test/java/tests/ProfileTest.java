package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

public class ProfileTest extends BaseTest{
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();

    @BeforeEach
    public void login() {

        loginPage.openPage()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

    @Test
    public void checkProfileInfo() {
        mainPage.openProfile()
                .checkProfileName(TestData.USER_NAME);
    }

    @Test
    public void checkProfileBirthDateVisibility() {
        mainPage.openProfile()
                .checkBirthDateVisibility(TestData.USER_BIRTH_DATE);
    }
}
