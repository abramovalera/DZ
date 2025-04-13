package utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static final SecureRandom random = new SecureRandom();

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {
        return String.valueOf(8000000000L + random.nextInt(999999999));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public static String getRandomBirthDay() {
        return String.format("%02d", getRandomInt(1, 28));
    }

    public static String getRandomBirthMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return getRandomItemFromArray(months);
    }

    public static String getRandomBirthYear() {
        return String.valueOf(getRandomInt(1980, 2005));
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Accounting", "Arts", "Social Studies", "English",
                "Chemistry", "Computer Science", "Economics", "Physics", "Biology"};
        return getRandomItemFromArray(subjects);
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomItemFromArray(hobbies);
    }

    public static String getRandomFile() {
        String[] files = {"3.jpg", "2.jpg", "1.jpg"};
        return getRandomItemFromArray(files);
    }

    public static String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomItemFromArray(states);
    }

    public static String getRandomCity(String state) {
        switch (state) {
            case "NCR": return getRandomItemFromArray(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh": return getRandomItemFromArray(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana": return getRandomItemFromArray(new String[]{"Karnal", "Panipat"});
            case "Rajasthan": return getRandomItemFromArray(new String[]{"Jaipur", "Jaiselmer"});
            default: return "";
        }
    }

    public static String getRandomItemFromArray(String[] array) {
        return array[random.nextInt(array.length)];
    }
}