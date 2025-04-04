package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement profileName = $x("//*[@id=\"hook_Block_UserProfileInfo\"]/div/a");
    private final SelenideElement birthDateField = $x("//*[@id=\"hook_Block_AboutUserRB\"]/div/div[4]/div/div[1]/div/div/span[2]");

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