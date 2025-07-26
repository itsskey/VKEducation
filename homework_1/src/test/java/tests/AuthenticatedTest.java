package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import pages.HomePage;
import pages.LoginPage;

public abstract class AuthenticatedTest extends BaseTest{
    @BeforeEach
    @Override
    public void localSetup() {
        super.localSetup();
        HomePage homePage = performLogin();
        afterLoginSetup(homePage);
    }

    private HomePage performLogin() {
        return new LoginPage()
                .loginWith(TestData.VALID_USER)
                .asValidUser();
    }

    public abstract void afterLoginSetup(HomePage homePage);
}
