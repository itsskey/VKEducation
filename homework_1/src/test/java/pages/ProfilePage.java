package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elements.ToolbarWrapper;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage implements BasePage{
    private final ToolbarWrapper TOOLBAR = new ToolbarWrapper();
    //Локаторы:
    private final SelenideElement PROFILE_NAME = $x("//*[contains(@class,'user-profile-name')]");
    private final SelenideElement BIRTHDATE_FIELD = $x("//*[@data-type='AGE']");

    public ProfilePage() {
        isLoaded();
    }

    public ProfilePage isLoaded() {
        PROFILE_NAME.shouldBe(visible.because("Страница профиля не загрузилась"));
        BIRTHDATE_FIELD.shouldBe(visible.because("Поле с датой рождения не отображается"));
        TOOLBAR.isLoaded();
        return this;
    }

    public String getPROFILE_NAME() {
        return PROFILE_NAME
                .shouldBe(visible.because("Страница профиля не загрузилась"))
                .getText().trim();
    }

    public String getBirthDate() {
        return BIRTHDATE_FIELD
                .shouldBe(visible.because("Поле с датой рождения не отображается"))
                .getText();
    }
}