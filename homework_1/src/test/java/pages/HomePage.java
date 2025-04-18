
package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private final SelenideElement userProfileLink = $x("//*[@data-l=\"t,userPage\"]");
    private final SelenideElement userName = $x("//*[contains(@data-l, 'userPage')]");
    private final SelenideElement toolBarArrow = $x("//*[@class=\"ucard-mini_cnt\"]"); //потом обернуть в класс Wrapper
    private final SelenideElement switchLanguageButton = $x("//*[contains(@class,\"u-menu_tx\")]");

    public String getUserName() {
        userName.shouldBe(visible.because("Элемент с именем пользователя не отображается"));
        return userName.getText();
    }

    public ProfilePage openProfile() {
        userProfileLink.shouldBe(visible.because("Ссылка на профиль пользователя не отображается")).click();
        return new ProfilePage();
    }

    public HomePage switchLanguage(){
        // Реализация метода
        return this;
    }
}
