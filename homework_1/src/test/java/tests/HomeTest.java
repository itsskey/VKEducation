package tests;

import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomeTest implements AuthenticatedTest{
    private HomePage homePage;

    public void afterLoginSetup(HomePage homePage) {
        this.homePage = homePage;
    }


    @Test
    @Tag("home")
    @DisplayName("Проверка смены языка интерфейса на английский")
    void testLanguageSwitch() {
        try {
            String actualLanguage = homePage
                    .getToolbar()
                    .openSettingsMenu()
                    .switchToEnglish()
                    .getHtmlLangAttribute();

            assertEquals("en", actualLanguage,
                    "Язык интерфейса не был изменен на английский");
        }finally {
            String newLanguage = homePage
                    .getToolbar()
                    .openSettingsMenu()
                    .switchToRussian()
                    .getHtmlLangAttribute();

            assertEquals("ru", newLanguage,
                    "Язык интерфейса не был изменен на русский");
        }
    }

}
