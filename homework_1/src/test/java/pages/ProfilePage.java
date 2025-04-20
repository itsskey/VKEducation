package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elements.ToolbarWrapper;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage implements BasePage{
    private final ToolbarWrapper toolbar = new ToolbarWrapper();
    //Локаторы:
    private final SelenideElement profileName = $x("//*[contains(@class,'user-profile-name')]");
    private final SelenideElement birthDateField = $x("//*[@data-type='AGE']");

    public ProfilePage isLoaded() {
        checkElementVisibility(profileName, "Страница профиля не загрузилась");
        checkElementVisibility(birthDateField, "Поле с датой рождения не отображается");
        toolbar.isLoaded();
        return this;
    }

    public ToolbarWrapper getToolbar() {
        return toolbar;
    }

    public String getProfileName() {
        return profileName.getText().trim();
    }

    public String getBirthDate() {
        return birthDateField.getText();
    }
}