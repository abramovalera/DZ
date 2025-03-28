package pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    // Локаторы элементов
    private final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            phone = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            uploadPicture = $("#uploadPicture"),
            address = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submitBtn = $("#submit"),
            modal = $(".modal-dialog"),
            resultsTable = $(".table-responsive");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    // Методы
    public RegistrationPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhone(String value) {
        phone.setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day)).click();
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadFile(String filePath) {
        uploadPicture.uploadFromClasspath(filePath);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    public RegistrationPage setStateAndCity(String stateValue, String cityValue) {
        state.click();
        $(byText(stateValue)).click();
        city.click();
        $(byText(cityValue)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitBtn.click();
        return this;
    }

    // Проверка модального окна
    public RegistrationPage verifyModalAppears() {
        modal.shouldBe(visible, Duration.ofSeconds(5));
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    // Проверка обязательных полей (красные борда проверяяем)
    public RegistrationPage checkRequiredFields() {
        String errorColor = "rgb(220, 53, 69)";
        firstName.shouldHave(cssValue("border-color", errorColor));
        lastName.shouldHave(cssValue("border-color", errorColor));
        phone.shouldHave(cssValue("border-color", errorColor));
        genderWrapper.shouldHave(cssValue("border-color", errorColor));
        return this;

    }

    // Проверка результатов
    public RegistrationPage verifyResult(String fieldName, String expectedValue) {
        resultsTable.$(byText(fieldName))
                .parent().lastChild()
                .shouldHave(text(expectedValue));
        return this;
    }

}