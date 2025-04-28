package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elements.ToolbarWrapper;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage implements BasePage{
    private final ToolbarWrapper TOOLBAR = new ToolbarWrapper();
    //Локаторы:
    private final SelenideElement USER_PROFILE_LINK = $x("//*[@data-l='t,userPage']");

    public HomePage() {
        isLoaded(); // Автоматическая проверка при создании страницы
    }

    public HomePage isLoaded() {
        USER_PROFILE_LINK.shouldBe(visible.because("Главная страница не загрузилась"));
        TOOLBAR.isLoaded();
        return this;
    }

    public ToolbarWrapper getToolbar() {
        return TOOLBAR;
    }

    public String getUserName() {
        return USER_PROFILE_LINK
                .shouldBe(visible.because("Главная страница не загрузилась"))
                .getText();
    }

    public ProfilePage openProfile() {
        USER_PROFILE_LINK
                .shouldBe(visible.because("Главная страница не загрузилась"))
                .click();
        return new ProfilePage().isLoaded();
    }
}
