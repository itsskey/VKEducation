package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement profileName = $x("//*[@class='__user-profile-name-decorator']");
    private final SelenideElement birthDateField = $x("//span[@data-type='AGE']");

    public ProfilePage checkProfileName(String name) {
        profileName.shouldHave(text(name));
        return this;
    }

    public ProfilePage checkBirthDateVisibility(String expectedDate) {
        birthDateField
                .shouldBe(visible)
                .shouldHave(exactText(expectedDate));
        return this;
    }
}