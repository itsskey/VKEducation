package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;

public interface BaseTest {
    @BeforeAll
    public static void globalSetup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeEach
    public default void lokalSetup() {
        open("/");
    }

    @AfterEach
    public default void clearData() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.refresh();
    }
}
