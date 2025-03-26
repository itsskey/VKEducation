package tests;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class BaseTest {
    @BeforeClass
    public static void globalSetup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.browser = "chrome";
        Configuration.timeout = 10_000;
        // Другие глобальные настройки
    }

    @After
    public void clearData() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
