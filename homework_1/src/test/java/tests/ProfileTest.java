package tests;

import data.TestData;
import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.ProfilePage;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest implements AuthenticatedTest{
    private HomePage homePage;
    private ProfilePage profilePage;

    public void afterLoginSetup(HomePage homePage) {
        this.homePage = homePage;
        this.profilePage = homePage.openProfile();
    }

    @Disabled("Тест деактивирован до реализации функционала")
    @Test
    @Tag("profile")
    @DisplayName("Добавление информации о себе")
    void testUploadProfileInfo() {
        profilePage=homePage.openProfile();
    }


    @Test
    @Tag("profile")
    @DisplayName("Проверка отображения имени профиля и даты рождения")
    public void testCheckProfileFields() {
        profilePage=homePage.openProfile();
        String actualName = profilePage.getProfileName();
        String actualBirthDate = profilePage.getBirthDate();
        int expectedAge = calculateExpectedAge();
        assertAll(
                // Проверка формата даты
                ()->assertTrue(actualBirthDate.matches(TestData.USER_BIRTH_DATE_REGEX),
                        "Формат даты не совпадает. Актуальное значение: '" + actualBirthDate + "'"),
                 // Проверка расчета возраста
                ()->assertTrue(actualBirthDate.contains("(" + expectedAge + " лет)"),
                "Возраст не соответствует действительному. Ожидалось: " + expectedAge),
                // Проверка имени
                ()->assertEquals(TestData.USER_NAME, actualName,
                        "Имя в профиле не совпадает с ожидаемым")
        );
    }
    private int calculateExpectedAge() {
        int currentYear = Year.now().getValue();
        boolean isBirthdayPassed = MonthDay.now().isAfter(MonthDay.of(Month.MARCH, 8));
        return currentYear - 1997 - (isBirthdayPassed ? 0 : 1);
    }
}
