package tests;

import org.junit.jupiter.api.*;
import pages.HomePage;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
public class HomeTest extends AuthenticatedTest{
    private HomePage homePage;

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
                    .switchToRussian();
            refresh();
        }
    }

    @Test
    @Tag("home")
    @DisplayName("Проверка смены языка интерфейса на английский")
    void testLanguageSwitch() {
            String actualLanguage = homePage
                    .getToolbar()
                    .switchToEnglish()
                    .getHtmlLangAttribute();
            refresh();

        String EN = "en";
        assertEquals(EN, actualLanguage,
                    "Язык интерфейса не был изменен на английский");
    }
}
