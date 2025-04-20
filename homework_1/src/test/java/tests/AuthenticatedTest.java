package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import pages.HomePage;
import pages.LoginPage;

public interface AuthenticatedTest extends BaseTest{
    @BeforeEach
    @Override
    default void lokalSetup() {
        BaseTest.super.lokalSetup();
        HomePage homePage = performLogin();
        afterLoginSetup(homePage);
    }

    private HomePage performLogin() {
        return new LoginPage()
                .isLoaded()
                .login(TestData.VALID_USER);
    }
    void afterLoginSetup(HomePage homePage);


}
