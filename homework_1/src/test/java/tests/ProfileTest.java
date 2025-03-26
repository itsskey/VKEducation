package tests;

import com.codeborne.selenide.Configuration;
import data.TestData;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;

public class ProfileTest extends BaseTest{
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();

    @Before
    public void login() {
        loginPage.openPage()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

    @Test
    public void checkProfileInfo() {
        mainPage.openProfile()
                .checkProfileName("Карина Айтынбетова");
    }

    @Test
    public void checkProfileBirthDateVisibility() {
        new MainPage()
                .openProfile()
                .checkBirthDateVisibility(TestData.USER_BIRTH_DATE);
    }
}
