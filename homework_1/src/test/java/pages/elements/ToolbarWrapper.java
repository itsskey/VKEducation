package pages.elements;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import pages.LoginPage;
import java.time.Duration;
import java.util.Objects;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToolbarWrapper implements BasePage {
    //Локаторы
    private static final String ROOT_XPATH =".//*[contains(@class,'toolbar_decor')]";
    private static final String TOOLBAR_ARROW_XPATH = ".//*[contains(@class,'toolbar_ucard')]";
    private static final String SWITCH_LANGUAGE_FROM_RU_XPATH = ".//*[contains(@class, 'u-menu_tx') and contains(text(), 'Русский')]";
    private static final String SWITCH_LANGUAGE_FROM_EN_XPATH = ".//*[contains(@class, 'u-menu_tx') and contains(text(), 'English')]";
    private static final String CHOOSE_EN_BUTTON_XPATH = ".//*[contains(@class, 'sel-lang') and contains(text(), 'English')]";
    private static final String CHOOSE_RU_BUTTON_XPATH = ".//*[contains(@class, 'sel-lang') and contains(text(), 'Русский')]";

    // Контейнер выпадающего меню
    private static final String MENU_CONTAINER_XPATH  = "//*[contains(@id, 'user-dropdown-menu')]";
    // Пункт «Выйти»
    private static final String LOGOUT = ".//*[contains(@data-l, 'logout')]";
    // Контейнер диалога подтверждения
    private static final String CONFIRM_DIALOG_XPATH = "//*[contains(@class, 'modal-new_center')]";
    // Кнопка «Выйти» в диалоге
    private static final String CONFIRM_LOGOUT_BUTTON = ".//input[contains(@data-l, 'logout') and contains(@id, 'Button_logoff.confirm')]";

    private static final SelenideElement ROOT = $x(ROOT_XPATH);

    @Override
    public ToolbarWrapper isLoaded() {
        ROOT.shouldBe(visible.because("Тулбар не отображается"));
        return this;
    }

    public ToolbarWrapper openSettingsMenu() {
        ROOT
                .$x(TOOLBAR_ARROW_XPATH)
                .shouldBe(visible.because("Стрелка открытия меню не отображается"))
                .click();
        return this;
    }

    public ToolbarWrapper switchToEnglish(){
        openSettingsMenu();
        SelenideElement menu = $x(MENU_CONTAINER_XPATH)
                .shouldBe(visible.because("Меню пользователя не открылось"),
                        Duration.ofSeconds(5));
        menu
                .$x(SWITCH_LANGUAGE_FROM_RU_XPATH).click();
        $x(CHOOSE_EN_BUTTON_XPATH).click();
        return this;
    }

    public ToolbarWrapper switchToRussian() {
        openSettingsMenu();
        SelenideElement menu = $x(MENU_CONTAINER_XPATH)
                .shouldBe(visible.because("Меню пользователя не открылось"),
                        Duration.ofSeconds(5));
        menu
                .$x(SWITCH_LANGUAGE_FROM_EN_XPATH).click();
        $x(CHOOSE_RU_BUTTON_XPATH).click();
        return this;
    }

    public String getHtmlLangAttribute() {
        return Objects.requireNonNull(executeJavaScript("return document.documentElement.lang"))
                .toString()
                .toLowerCase();
    }

    public LoginPage logout(){
        openSettingsMenu();
        SelenideElement menu = $x(MENU_CONTAINER_XPATH)
                .shouldBe(visible.because("Меню пользователя не открылось"));
        // Ожидаем появления и кликаем на кнопку выхода
        menu
                .$x(LOGOUT)
                .shouldBe(visible.because("Пункт 'Выйти' в меню не виден"))
                .shouldBe(enabled.because("Пункт 'Выйти' заблокирован"))
                .click();

        SelenideElement dialog = $x(CONFIRM_DIALOG_XPATH)
                .shouldBe(visible.because("Диалог подтверждения выхода не появился"));
        // Ожидаем подтверждающую кнопку
        dialog
                .$x(CONFIRM_LOGOUT_BUTTON)
                .shouldBe(visible.because("Кнопка 'Выйти' не появилась"))
                .shouldBe(enabled.because("Кнопка 'Выйти' неактивна"))
                .hover()
                .doubleClick();

        return new LoginPage();
    }

}