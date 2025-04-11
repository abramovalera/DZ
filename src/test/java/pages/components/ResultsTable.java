package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

// Компонент для работы с таблицей результатов
public class ResultsTable {
    private final SelenideElement table = $(".table-responsive");

    // Проверка значения в таблице
    public void verifyResult(String fieldName, String expectedValue) {
        table.$(byText(fieldName))        // Находим ячейку с названием поля
                .parent()                     // Переходим к родительской строке
                .lastChild()                 // Выбираем ячейку со значением
                .shouldHave(text(expectedValue)); // Проверяем текст
    }
}