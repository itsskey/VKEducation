package pages.elements;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import java.util.Objects;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ToolbarWrapper implements BasePage {
    private final SelenideElement rootElement;
    //Локаторы
    private final SelenideElement toolBarArrow = $x("//*[@class='ucard-mini_cnt']");
    private final SelenideElement switchLanguageButtonFromRu = $x("//*[contains(@class, 'u-menu_tx') and contains(text(), 'Русский')]");
    private final SelenideElement switchLanguageButtonFromEn = $x("//*[contains(@class, 'u-menu_tx') and contains(text(), 'English')]");
    private final SelenideElement chooseEnglishLanguageButton = $x("//*[contains(@class, 'sel-lang') and contains(text(), 'English')]");
    private final SelenideElement chooseRussianLanguageButton = $x("//*[contains(@class, 'sel-lang') and contains(text(), 'Русский')]");

    public ToolbarWrapper() {
        this.rootElement = $x("//*[contains(@class,'toolbar_decor')]");
    }

    public ToolbarWrapper isLoaded() {
        checkElementVisibility(rootElement,"Тулбар не отображается");
        return this;
    }

    public ToolbarWrapper openSettingsMenu() {
        toolBarArrow
                .shouldBe(visible.because("Стрелка открытия меню не отображается"))
                .click();
        return this;
    }

    public ToolbarWrapper switchToEnglish(){
        switchLanguageButtonFromRu.click();
        chooseEnglishLanguageButton.click();
        return this;
    }

    public ToolbarWrapper switchToRussian() {
        switchLanguageButtonFromEn.click();
        chooseRussianLanguageButton.click();
        return this;
    }

    public String getHtmlLangAttribute() {
        return Objects.requireNonNull(executeJavaScript("return document.documentElement.lang"))
                .toString()
                .toLowerCase();
    }

}