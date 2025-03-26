import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTest {
    @Test
    void successLoginTest() {
        Configuration.holdBrowserOpen = true;

        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Valery");
        $("#lastName").setValue("Abram");
        $("#userEmail").setValue("test@mail.com");
        $("#gender-radio-1").parent().click();
        $("#gender-radio-1").shouldBe(Condition.selected);
        $("#userNumber").setValue("8900123450");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbies-checkbox-1").parent().click();
        $("#uploadPicture").uploadFromClasspath("cat.jpg");
        $("#currentAddress").setValue("ул. Боброва д. 001");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();
        $(".table-responsive").shouldBe(Condition.visible);
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(Condition.text("Valery Abram"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(Condition.text("test@mail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(Condition.text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(Condition.text("8900123450"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(Condition.text("01 July,1995"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(Condition.text("English"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(Condition.text("Sports"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(Condition.text("cat.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(Condition.text("ул. Боброва д. 001"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(Condition.text("Haryana Karnal"));
    }
}