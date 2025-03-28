package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.BeforeClass;


public class BaseTest {
    @BeforeClass
    public static void globalSetup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        // Другие глобальные настройки
    }

    @After
    public void clearData() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.refresh();
    }
}
