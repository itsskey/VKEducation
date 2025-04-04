package tests;

import data.TestData;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ProfileTest extends BaseTest{
    private MainPage mainPage;
    private ProfilePage profilePage;

    @BeforeEach
    public void login() {
        mainPage = new LoginPage()
               .verifyPageLoaded()
               .login(
                       TestData.VALID_LOGIN,
                       TestData.VALID_PASSWORD
               );

    }


    @Disabled("Отключен до тех пор, пока не будет реализован")
    @Test
    @Tag("profile")
    @DisplayName("Добавление информации о себе")
    void shouldUploadProfileInfo() {
        profilePage=mainPage.openProfile();
    }


    @Test
    @Tag("profile")
    @DisplayName("Проверка отображения имени профиля и даты рождения")
    public void checkProfileFields() {
        profilePage=mainPage.openProfile();
        assertAll(
                () -> profilePage.checkProfileName(TestData.USER_NAME),
                () -> profilePage.checkBirthDateVisibility(TestData.USER_BIRTH_DATE)
        );

    }
}
