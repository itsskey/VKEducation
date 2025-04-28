package pages.elements;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import java.util.Objects;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ToolbarWrapper implements BasePage {
    //Локаторы
    private final String ROOT_XPATH =".//*[contains(@class,'toolbar_decor')]";
    private final String TOOLBAR_ARROW_XPATH = ".//*[contains(@class,'toolbar_ucard')]";
    private final String SWITCH_LANGUAGE_FROM_RU_XPATH = ".//*[contains(@class, 'u-menu_tx') and contains(text(), 'Русский')]";
    private final String SWITCH_LANGUAGE_FROM_EN_XPATH = ".//*[contains(@class, 'u-menu_tx') and contains(text(), 'English')]";
    private final String CHOOSE_EN_BUTTON_XPATH = ".//*[contains(@class, 'sel-lang') and contains(text(), 'English')]";
    private final String CHOOSE_RU_BUTTON_XPATH = ".//*[contains(@class, 'sel-lang') and contains(text(), 'Русский')]";
    private final SelenideElement ROOT = $x(ROOT_XPATH);

    public ToolbarWrapper isLoaded() {
        ROOT.shouldBe(visible.because("Тулбар не отображается"));
        return this;
    }

    public ToolbarWrapper openSettingsMenu() {
        ROOT
                .find(byXpath(TOOLBAR_ARROW_XPATH))
                .shouldBe(visible.because("Стрелка открытия меню не отображается"))
                .click();
        return this;
    }

    public ToolbarWrapper switchToEnglish(){
        ROOT
                .find(byXpath(SWITCH_LANGUAGE_FROM_RU_XPATH))
                .click();
        $(byXpath(CHOOSE_EN_BUTTON_XPATH)).click();
        return this;
    }

    public void switchToRussian() {
        ROOT
                .find(byXpath(SWITCH_LANGUAGE_FROM_EN_XPATH))
                .click();
        $(byXpath(CHOOSE_RU_BUTTON_XPATH)).click();
    }

    public String getHtmlLangAttribute() {
        return Objects.requireNonNull(executeJavaScript("return document.documentElement.lang"))
                .toString()
                .toLowerCase();
    }

}