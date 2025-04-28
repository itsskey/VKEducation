package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import pages.HomePage;
import pages.LoginPage;

public abstract class AuthenticatedTest extends BaseTest{
    @BeforeEach
    @Override
    void lokalSetup() {
        super.lokalSetup();
        HomePage homePage = performLogin();
        afterLoginSetup(homePage);
    }

    private HomePage performLogin() {
        return new LoginPage()
                .loginWith(TestData.VALID_USER)
                .asValidUser();
    }
    abstract void afterLoginSetup(HomePage homePage);


}
