package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {
    @BeforeAll
    public static void globalSetup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        // Другие глобальные настройки
    }

    @AfterEach
    public void clearData() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.refresh();
    }
}
