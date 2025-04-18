package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement profileName = $x("//*[contains(@class,'user-profile-name')]");
    private final SelenideElement birthDateField = $x("//*[@data-type='AGE']");

    public String getProfileName() {
        profileName.shouldBe(visible.because("Имя в профиле не отображается"));
        return profileName.getText().trim();
    }

    public String getBirthDate() {
        birthDateField
                .shouldBe(visible.because("Поле с датой рождения не отображается"));
        return birthDateField.getText();
    }
}