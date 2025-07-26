package pages.elements;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import pages.ProfilePage;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

// Класс-обертка для бокового меню
public class SidebarWrapper implements BasePage {
    private static final SelenideElement ROOT = $x(".//*[contains(@id, 'AsideColumn')]");

    private static final String USER_PROFILE_LINK = ".//*[@data-l='t,userPage']";
    private static final String POST_BUTTON = ".//*[contains(@class, 'dropdown-button')]";
    private static final String POST_ENTRY = ".//*[contains(@data-l, 'feed.posting')]";
    private static final String POST_INPUT =".//div[contains(@class, 'posting_cnt')]//*[@contenteditable='true']";
    private static final String SUBMIT_POST_BUTTON = ".//*[contains(@data-l, 'button.submit')]";

    @Override
    public SidebarWrapper isLoaded() {
        ROOT.shouldBe(visible.because("Боковое меню не отображается"));
        return this;
    }

    public ProfilePage openProfile() {
        ROOT
                .$x(USER_PROFILE_LINK)
                .shouldBe(interactable.because("Кнопка профиля недоступна"), Duration.ofSeconds(10))
                .click();
        return new ProfilePage().isLoaded();
    }

    public String getUserName() {
        return
            ROOT
                .$x(USER_PROFILE_LINK)
                .shouldBe(visible.because("Главная страница не загрузилась"))
                .getText();
    }

    // Открыть меню создания поста
    public SidebarWrapper openPostMenu() {
        ROOT
                .$x(POST_BUTTON)
                .shouldBe(interactable.because("Кнопка 'Опубликовать' недоступна"))
                .click();
        return this;
    }

    // Выбрать тип (запись)
    public SidebarWrapper selectPostEntry() {
        $x(POST_ENTRY)
                .shouldBe(visible.because("Кнопка 'Опубликовать запись' не найдена"))
                .click();
        return this;
    }

    // Ввести текст записи
    public SidebarWrapper enterPostEntry(String text) {
        SelenideElement postInput =
                $x(POST_INPUT)
                        .shouldBe(visible.because("Поле ввода контента недоступно"))
                        .shouldBe(enabled.because("Поле ввода неактивно"));

        postInput.click();
        postInput.clear();
        postInput.sendKeys(text);
        return this;
    }

    // Опубликовать запись
    public ProfilePage submitPost() {
        $(byXpath(SUBMIT_POST_BUTTON))
                .shouldBe(enabled.because("Кнопка отправки заблокирована"))
                .click();
        return new ProfilePage().isLoaded();
    }

    // Метод для добавления записи
    public ProfilePage createPost(String text) {
        openPostMenu()
                .selectPostEntry()
                .enterPostEntry(text)
                .submitPost();
        return new ProfilePage();
    }
}