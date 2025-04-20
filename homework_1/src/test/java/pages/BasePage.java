package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

//Page Object + Loadable Component
public interface BasePage {
    BasePage isLoaded();

    default void checkElementVisibility(SelenideElement element, String errorMsg) {
        element.shouldBe(visible.because(errorMsg));
    }
}
