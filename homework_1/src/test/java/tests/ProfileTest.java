package tests;

import data.TestData;
import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.ProfilePage;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest extends AuthenticatedTest{
    private ProfilePage profilePage;

    public void afterLoginSetup(HomePage homePage) {
        this.profilePage = homePage.openProfile();
    }

    @Disabled("Тест деактивирован до реализации функционала")
    @Test
    @Tag("profile")
    @DisplayName("Добавление информации о себе")
    void testUploadProfileInfo() {    }


    @Test
    @Tag("profile")
    @DisplayName("Проверка отображения имени профиля и даты рождения")
    public void testCheckProfileFields() {
        String actualName = profilePage.getPROFILE_NAME();
        String actualBirthDate = profilePage.getBirthDate();
        int expectedAge = calculateExpectedAge();
        String formatAge = formatAge(expectedAge);
        assertAll(
                // Проверка формата даты
                ()->assertTrue(actualBirthDate.matches(TestData.USER_BIRTH_DATE_REGEX),
                        "Формат даты не совпадает. Актуальное значение: '" + actualBirthDate + "'"),
                 // Проверка расчета возраста
                ()->assertTrue(actualBirthDate.contains("(" + expectedAge + " "+formatAge+ ")"),
                "Возраст не соответствует действительному. Ожидалось: " + expectedAge),
                // Проверка имени
                ()->assertEquals(TestData.USER_NAME, actualName,
                        "Имя в профиле не совпадает с ожидаемым")
        );
    }
    private int calculateExpectedAge() {
        int currentYear = Year.now().getValue();
        boolean isBirthdayPassed = MonthDay.now().isAfter(MonthDay.of(Month.MARCH, 8));
        return currentYear - TestData.USER_BIRTH_YEAR - (isBirthdayPassed ? 0 : 1);
    }
    // Метод для проверки год/года/лет
    private String formatAge(int age){
        if ((age%100 >= 5 && age%100 <= 20) || age%10 >= 5 || age%10 == 0)
            return "лет";
        else if (age%10 >= 2 && age%10<=4)
            return "год";
        else
            return "года";
    }
}
