package utils;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class RandomUtils {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final Map<String, String[]> statesAndCities = new HashMap<>();

    static {
        statesAndCities.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        statesAndCities.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        statesAndCities.put("Haryana", new String[]{"Karnal", "Panipat"});
        statesAndCities.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPhone() {
        return "9" + faker.number().digits(9); // Пример: 9876543210
    }

    public static String getRandomStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomBirthDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomBirthMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1980, 2010));
    }

    public static String getRandomSubject() {
        return faker.options().option("Maths", "Chemistry", "Physics", "English");
    }

    public static String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomFile() {
        return "1.jpg";
    }

    public static String getRandomState() {
        return faker.options().option(statesAndCities.keySet().toArray(new String[0]));
    }

    public static String getRandomCity(String state) {
        return faker.options().option(statesAndCities.get(state));
    }
}