package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import pages.elements.SidebarWrapper;
import pages.elements.ToolbarWrapper;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage implements BasePage{
    private final ToolbarWrapper TOOLBAR = new ToolbarWrapper();
    private final SidebarWrapper SIDEBAR = new SidebarWrapper();

    /// Локаторы:
    private static final SelenideElement PROFILE_NAME = $x("//*[contains(@class,'user-profile-name')]");
    private static final SelenideElement BIRTHDATE_FIELD = $x("//*[@data-type='AGE']");
    // Шаблон для поиска записи
    private static final String POST_TEXT = "//div[contains(@class,'media-text_cnt') and normalize-space(.) = '%s']";
    private static final SelenideElement FEED_ACTIONS = $x("//*[contains(@class, 'feed-action-icon')]");
    private static final SelenideElement DELETE_BUTTON = $x("//*[contains(@class, 'ico_trash')]");
    private static final SelenideElement CONFIRM_DELETE_BUTTON = $x("//*[contains(@class, 'actions_yes')]");

    public ProfilePage() {
        isLoaded();
    }

    @Override
    public ProfilePage isLoaded() {
        PROFILE_NAME.shouldBe(visible.because("Страница профиля не загрузилась"));
        BIRTHDATE_FIELD.shouldBe(visible.because("Поле с датой рождения не отображается"));
        TOOLBAR.isLoaded();
        SIDEBAR.isLoaded();
        return this;
    }

    public String getProfileName() {
        return PROFILE_NAME
                .shouldBe(visible.because("Страница профиля не загрузилась"))
                .getText()
                .trim();
    }

    public String getBirthDate() {
        return BIRTHDATE_FIELD
                .shouldBe(visible.because("Поле с датой рождения не отображается"))
                .getText();
    }

    public ToolbarWrapper getToolbar() {
        return TOOLBAR;
    }

    public SidebarWrapper getSidebar() {
        return SIDEBAR.isLoaded();
    }

    public boolean isPostVisible(String text) {
        try {
            SelenideElement post = $x(String.format(POST_TEXT, text))
                    .shouldBe(visible.because("Пост с текстом '" + text + "' не отображается"));
            post.scrollIntoView("{block: 'center'}");
            return post.isDisplayed();
        } catch (ElementNotFound e) {
            return false;
        }
    }

    public ProfilePage deletePost(String text) {
        SelenideElement post = $x(String.format(POST_TEXT, text))
                .shouldBe(visible.because("Текст поста не найден"));
        post.scrollIntoView("{block: 'center'}");

        FEED_ACTIONS
                .shouldBe(interactable.because("Меню действий недоступно"))
                .click();

        DELETE_BUTTON
                .shouldBe(interactable.because("Кнопка удаления недоступна"))
                .click();

        CONFIRM_DELETE_BUTTON
                .shouldBe(visible.because("Кнопка подтверждения удаления не появилась"))
                .click();

        refresh();
        return new ProfilePage();
    }
}