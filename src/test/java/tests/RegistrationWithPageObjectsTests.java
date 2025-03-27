package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithPageObjectsTests {

    // Настройки перед всеми тестами
    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080"; // Размер окна браузера
        Configuration.baseUrl = "https://demoqa.com"; //  URL
        Configuration.timeout = 10000; // Ожидание  10 секунд
    }

    // Полное заполнение формы
    @Test
    void fullRegistrationTest() {
        new RegistrationPage()
                .openPage() // 1. Открываем страницу
                .setFirstName("Alex") // 2. Заполняем имя
                .setLastName("Egorov") // 3. Заполняем фамилию
                .setEmail("alex@egorov.com") // 4. Заполняем email
                .setGender("Male") // 5. Выбираем пол
                .setPhone("1234567890") // 6. Вводим телефон
                .submit() // 7. Отправляем форму
                .verifyModalAppears() // 8. Проверяем модальное окно
                .verifyResult("Student Name", "Alex Egorov") // 9. Проверяем имя
                .verifyResult("Student Email", "alex@egorov.com"); // 10. Проверяем email
    }

    //  Минимальное заполнение
    @Test
    void minimalRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .setFirstName("Alex") // Только имя
                .setLastName("Egorov") // и фамилия
                .setGender("Male") // и пол
                .setPhone("1234567890") // и телефон
                .submit()
                .verifyModalAppears()
                .verifyResult("Student Name", "Alex Egorov"); // Проверяем только имя
    }

    // Негативный
    @Test
    void negativeRegistrationTest() {
        new RegistrationPage()
                .openPage()
                .submit() // Отправляем пустую форму
                .checkRequiredFields(); // Проверяем подсветку ошибок
    }
}