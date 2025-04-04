
package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement userProfileLink = $x("//a[@data-l='t,userPage']");
    private final SelenideElement userName = $x("//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div");


    public MainPage checkUserName(String name) {
        userName.shouldHave(text(name));
        return this;
    }

    public ProfilePage openProfile() {
        userProfileLink.shouldBe(visible).click();
        return new ProfilePage();
    }
}
