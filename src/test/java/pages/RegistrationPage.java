
        package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    // Элементы страницы
    private final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            gender = $("#genterWrapper"),
            phone = $("#userNumber"),
            submit = $("#submit"),
            modal = $(".modal-dialog"),
            resultsTable = $(".table-responsive");

    // Открытие страницы
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

        // Заполняем имя
    public RegistrationPage setFirstName(String value) {
        firstName.setValue(value); // Вводим текст в поле
        return this;
    }

    // Заполняем фамилию
    public RegistrationPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    // Заполняем email
    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    // Выбираем пол (Male/Female/Other)
    public RegistrationPage setGender(String value) {
        gender.$(byText(value)).click(); // Ищем текст и кликаем
        return this;
    }

    // Заполняем телефон
    public RegistrationPage setPhone(String value) {
        phone.setValue(value);
        return this;
    }

    // Отправляем форму
    public RegistrationPage submit() {
        submit.click(); // Кликаем по кнопке Submit
        return this;
    }

    // Проверяем, что модальное окно появилось
    public RegistrationPage verifyModalAppears() {
        modal.shouldBe(visible); // Ждем пока окно станет видимым
        return this;
    }

    // Проверяем конкретное значение в таблице результатов
    public RegistrationPage verifyResult(String key, String value) {
        // Ищем ячейку с текстом key и проверяем соседнюю ячейку на значение value
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    // Проверяем, что обязательные поля подсвечены красным
    public RegistrationPage checkRequiredFields() {
        // Проверяем цвет рамки у обязательных полей
        firstName.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        lastName.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }
}