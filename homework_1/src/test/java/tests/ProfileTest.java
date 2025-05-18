package tests;

import com.codeborne.selenide.WebDriverRunner;
import data.TestData;
import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
public class ProfileTest extends AuthenticatedTest{
    private ProfilePage profilePage;

    public void afterLoginSetup(HomePage homePage) {
        this.profilePage = homePage.getSidebar().openProfile();
    }

    @Test
    @Tag("profile")
    @DisplayName("Добавление записи на страницу")
    public void testAddAndDeletePost() {
        // Генерация уникальной записи
        String postText = "Тестовая запись " + System.currentTimeMillis();
        profilePage = profilePage.getSidebar().createPost(postText);
        assertTrue(
                profilePage.isPostVisible(postText),
                "Запись не отображается на странице"
        );

        profilePage = profilePage.deletePost(postText);
        assertFalse(
               profilePage.isPostVisible(postText),
                "Запись не была удалена" );
    }

    @Test
    @Tag("profile")
    @DisplayName("Проверка выхода из аккаунта")
    public void testLogout(){
        LoginPage loginPage =profilePage
                .getToolbar()
                .logout();
        loginPage.isLoaded();

        // Проверка URL
        String realURL = "anonymMain";
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertTrue(currentUrl.contains(realURL),
                "Текущий URL не соответствует странице входа. Актуальный URL: " + currentUrl);
    }

    @Test
    @Tag("profile")
    @DisplayName("Проверка отображения имени профиля и даты рождения")
    public void testCheckProfileFields() {
        String actualName = profilePage.getProfileName();
        String actualBirthDate = profilePage.getBirthDate();
        int expectedAge = calculateExpectedAge();
        String formatAge = formatAge(expectedAge);
        assertAll(
                // Проверка формата даты
                ()->assertTrue(actualBirthDate.matches(TestData.USER_BIRTH_DATE_REGEX),
                        "Формат даты не совпадает. Актуальное значение: '" + actualBirthDate + "'"),
                 // Проверка расчета возраста
                ()->assertTrue(actualBirthDate.contains("(" + expectedAge + " "+formatAge+ ")"),
                "Возраст не соответствует действительному. Ожидалось: " + expectedAge + formatAge),
                // Проверка имени
                ()->assertEquals(TestData.USER_NAME, actualName,
                        "Имя в профиле не совпадает с ожидаемым")
        );
    }

    // Метод для рассчета возраста
    private int calculateExpectedAge() {
        int currentYear = Year.now().getValue();
        boolean isBirthdayPassed = MonthDay.now().isAfter(
                MonthDay.of(Month.valueOf(TestData.USER_BIRTH_MONTH), TestData.USER_BIRTH_DAY)
        );
        return currentYear - TestData.USER_BIRTH_YEAR - (isBirthdayPassed ? 0 : 1);
    }

    // Метод для проверки год/года/лет
    private String formatAge(int age) {
        int lastTwoDigits = age % 100;
        int lastDigit = age % 10;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            return "лет";
        } else if (lastDigit == 1) {
            return "год";
        } else if (lastDigit >= 2 && lastDigit <= 4) {
            return "года";
        } else {
            return "лет";
        }
    }
}
