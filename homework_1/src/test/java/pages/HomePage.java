package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elements.ToolbarWrapper;
import static com.codeborne.selenide.Selenide.*;

public class HomePage implements BasePage{
    private final ToolbarWrapper toolbar = new ToolbarWrapper();
    //Локаторы:
    private final SelenideElement userProfileLink = $x("//*[@data-l=\"t,userPage\"]");
    private final SelenideElement userName = $x("//*[contains(@data-l, 'userPage')]");


    public HomePage isLoaded() {
        checkElementVisibility(userProfileLink, "Главная страница не загрузилась");
        checkElementVisibility(userName, "Имя пользователя не отображается");
        toolbar.isLoaded();
        return this;
    }

    public ToolbarWrapper getToolbar() {
        return toolbar;
    }

    public String getUserName() {
        return userName.getText();
    }

    public ProfilePage openProfile() {
        userProfileLink.click();
        return new ProfilePage().isLoaded();
    }
}
