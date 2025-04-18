package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

public class RegistrationWithPageObjectsTestsFaker {
    private final String
            firstName = RandomUtils.getRandomFirstName(),
            lastName = RandomUtils.getRandomLastName(),
            userEmail = RandomUtils.getRandomEmail(),
            phoneNumber = RandomUtils.getRandomPhone(),
            streetAddress = RandomUtils.getRandomStreetAddress(),
            gender = RandomUtils.getRandomGender(),
            birthDay = RandomUtils.getRandomBirthDay(),
            birthMonth = RandomUtils.getRandomBirthMonth(),
            birthYear = RandomUtils.getRandomBirthYear(),
            subject = RandomUtils.getRandomSubject(),
            hobby = RandomUtils.getRandomHobby(),
            fileName = RandomUtils.getRandomFile(),
            state = RandomUtils.getRandomState(),
            city = RandomUtils.getRandomCity(state);

    @BeforeAll
    static void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 4000;
    }

    @Test
    void fullRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhone(phoneNumber)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(fileName)
                .setAddress(streetAddress)
                .setState(state)
                .setCity(city)
                .submit()
                .verifyModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", phoneNumber)
                .verifyResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", fileName)
                .verifyResult("Address", streetAddress)
                .verifyResult("State and City", state + " " + city);
    }
}