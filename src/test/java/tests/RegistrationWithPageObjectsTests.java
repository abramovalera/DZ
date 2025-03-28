package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithPageObjectsTests {

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
//        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 20000;
    }

    @Test
    void fullRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Male")
                .setPhone("1234567890")
                .setBirthDate("15", "May", "1990")
                .setSubject("Maths")
                .setSubject("Physics")
                .setHobby("Sports")
                .setHobby("Music")
                .uploadFile("sample.jpg")
                .setAddress("Moscow, Red Square 1")
                .setStateAndCity("NCR", "Delhi")
                .submit()
                .verifyModalAppears()
                .verifyResult("Student Name", "Alex Egorov")
                .verifyResult("Student Email", "alex@egorov.com")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "1234567890")
                .verifyResult("Date of Birth", "15 May,1990")
                .verifyResult("Subjects", "Maths, Physics")
                .verifyResult("Hobbies", "Sports, Music")
                .verifyResult("Picture", "sample.jpg")
                .verifyResult("Address", "Moscow, Red Square 1")
                .verifyResult("State and City", "NCR Delhi");
    }

    @Test
    void minimalRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setGender("Male")
                .setPhone("1234567890")
                .submit()
                .verifyModalAppears()
                .verifyResult("Student Name", "Alex Egorov")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "1234567890");
    }
    @Test
    void negativeRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .submit()
                .checkRequiredFields();
    }
}