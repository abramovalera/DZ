package utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomUtils {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final String[] STATES = {
            "NCR",
            "Uttar Pradesh",
            "Haryana",
            "Rajasthan"
    };

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
        return "9" + faker.number().digits(9); // пример: 9876543210
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
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
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

    /**
     * Случайный штат из заранее заданного списка.
     */
    public static String getRandomState() {
        return faker.options().option(STATES);
    }

    /**
     * Случайный город для переданного штата.
     * @param state один из значений getRandomState()
     * @return имя города
     */
    public static String getRandomCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }
}
