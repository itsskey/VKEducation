package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

public class ProfileTest extends BaseTest{
    private MainPage mainPage;

    @BeforeEach
    public void login() {
        mainPage =  new LoginPage()
               .verifyPageLoaded()
               .login(
                       TestData.VALID_LOGIN,
                       TestData.VALID_PASSWORD
               );

    }

    @Test
    public void checkProfileInfo() {
        mainPage
                .openProfile()
                .checkProfileName(TestData.USER_NAME);
    }

    @Test
    public void checkProfileBirthDateVisibility() {
        mainPage
                .openProfile()
                .checkBirthDateVisibility(TestData.USER_BIRTH_DATE);
    }
}
