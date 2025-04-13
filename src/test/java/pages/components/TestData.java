package tests;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestData {
    Faker faker = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String phoneNumber = faker.phoneNumber().phoneNumber();
    public String streetAddress = faker.address().streetAddress();
}