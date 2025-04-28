package tests;

import org.junit.jupiter.api.*;
import pages.HomePage;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTest extends AuthenticatedTest{
    private HomePage homePage;
    private final String EN = "en";

    @Override
    public void afterLoginSetup(HomePage homePage) {
        this.homePage = homePage;
        refresh();
    }

    @AfterEach
    void switchLanguageToRussian(){
        if (homePage != null) {
            homePage
                    .getToolbar()
                    .openSettingsMenu()
                    .switchToRussian();
        }
    }


    @Test
    @Tag("home")
    @DisplayName("Проверка смены языка интерфейса на английский")
    void testLanguageSwitch() {
            String actualLanguage = homePage
                    .getToolbar()
                    .openSettingsMenu()
                    .switchToEnglish()
                    .getHtmlLangAttribute();

            assertEquals(EN, actualLanguage,
                    "Язык интерфейса не был изменен на английский");
    }
}
