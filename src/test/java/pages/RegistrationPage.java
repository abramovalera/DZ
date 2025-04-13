package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DatePicker;
import pages.components.ResultsTable;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class RegistrationPage {
    private static final String ERROR_COLOR = "rgb(220, 53, 69)";

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
            modal = $(".modal-dialog");

    // Компоненты
    private final ResultsTable resultsTable = new ResultsTable();
    private final DatePicker datePicker = new DatePicker();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        removeBanners();
        return this;
    }


    public RegistrationPage removeBanners() {
        executeJavaScript("document.getElementById('fixedban')?.remove()");
        executeJavaScript("document.querySelector('footer')?.remove()");
        return this;
    }

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

    // Используем компонент DatePicker
    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        datePicker.selectMonth(month)
                .selectYear(year)
                .selectDay(day);
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

    public RegistrationPage setState(String stateValue) {
        state.click();
        $(byText(stateValue)).click();
        return this;
    }

    public RegistrationPage setCity(String cityValue) {
        city.click();
        $(byText(cityValue)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitBtn.click();
        return this;
    }

    public RegistrationPage verifyModalAppears() {
        modal.shouldBe(visible, Duration.ofSeconds(5));
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationPage verifyResult(String fieldName, String expectedValue) {
        resultsTable.verifyResult(fieldName, expectedValue);
        return this;
    }

    public RegistrationPage checkRequiredFields() {
        firstName.shouldHave(cssValue("border-color", ERROR_COLOR));
        lastName.shouldHave(cssValue("border-color", ERROR_COLOR));
        phone.shouldHave(cssValue("border-color", ERROR_COLOR));
        return this;
    }
}
