package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {
    @BeforeAll
    public static void globalSetup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.browser = "chrome";
        Configuration.timeout = 10_000;
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeEach
    public void localSetup() {
        open("/");
    }

    @AfterEach
    public void clearData() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
