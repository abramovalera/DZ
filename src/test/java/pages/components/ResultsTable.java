package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTable {
    private final SelenideElement table = $(".table-responsive");

    public void verifyResult(String fieldName, String expectedValue) {
        table.$(byText(fieldName))
                .parent().lastChild()
                .shouldHave(text(expectedValue));
    }
}