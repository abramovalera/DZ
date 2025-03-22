import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class COMANDTEST {

    public static void main(String[] args) throws FileNotFoundException {
        // Демонстрация основных команд Selenide
        browserCommands();
        selectorExamples();
        actionsExamples();
        assertionsExamples();
        conditionsExamples();
        collectionsExamples();
        fileOperationExamples();
        javascriptExamples();
    }

    static void browserCommands() {
        // Открытие страницы по абсолютному URL
        open("https://google.com");

        // Открытие страницы с относительным URL (используется базовый URL, заданный параметром -Dselenide.baseUrl)
        open("/customer/orders");

        // Открытие страницы с базовой HTTP-аутентификацией
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password"));

        // Навигация по страницам
        Selenide.back();     // Переход назад по истории браузера
        Selenide.refresh();  // Обновление текущей страницы

        // Очистка куки и локального хранилища
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();"); // Очистка sessionStorage через JavaScript

        // Работа с диалоговыми окнами (alert)
        Selenide.confirm();  // Нажать "ОК"
        Selenide.dismiss();  // Нажать "Отмена"

        // Закрытие активной вкладки и браузера
        Selenide.closeWindow();     // Закрыть текущую вкладку
        Selenide.closeWebDriver();    // Полностью закрыть браузер

        // Переключение между фреймами и окнами
        Selenide.switchTo().frame("new");          // Переключение на iframe с именем "new"
        Selenide.switchTo().defaultContent();        // Возврат к основному содержимому
        Selenide.switchTo().window("The Internet");  // Переключение на окно с заголовком "The Internet"

        // Работа с куки: добавление нового cookie
        Cookie cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    static void selectorExamples() {
        // Простые селекторы по CSS
        $("div").click(); // Находит первый <div> и кликает по нему
        element("div").click(); // Альтернативный способ поиска

        $("div", 2).click(); // Клик по третьему элементу <div> (индексация начинается с 0)

        // Селекторы по XPath
        $x("//h1/div").click();
        $(byXpath("//h1/div")).click();

        // Селекторы по тексту
        $(byText("full text")).click(); // Поиск элемента с точным совпадением текста
        $(withText("ull tex")).click();   // Поиск элемента, содержащего указанный фрагмент текста

        // Селекторы по тегу и тексту
        $(byTagAndText("div", "full text"));
        $(withTagAndText("div", "ull text"));

        // Навигация по DOM-дереву
        $("").parent();      // Получить родительский элемент
        $("").sibling(1);    // Получить соседний элемент с индексом 1
        $("").preceding(1);  // Получить предыдущий элемент
        $("").closest("div"); // Найти ближайшего родителя с тегом <div>
        $("").ancestor("div"); // То же, что и closest
        $("div:last-child");   // Найти последний дочерний элемент с тегом <div>

        // Цепочка поиска вложенных элементов
        $("div").$("h1").find(byText("abc")).click();

        // Селекторы по атрибутам
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();

        // Селекторы по идентификатору (id)
        $(byId("mytext")).click();
        $("#mytext").click();

        // Селекторы по CSS-классу
        $(byClassName("red")).click();
        $(".red").click();
    }

    static void actionsExamples() {
        // Выполнение кликов
        $("").click();
        $("").doubleClick();  // Двойной клик
        $("").contextClick(); // Правый клик (контекстное меню)

        // Наведение курсора на элемент
        $("").hover();

        // Работа с текстовыми полями
        $("").setValue("text"); // Установка текста
        $("").append("text");   // Добавление текста к уже существующему
        $("").clear();          // Очистка поля
        $("").setValue("");     // Альтернативная очистка

        // Отправка нажатий клавиш
        $("div").sendKeys("c"); // Нажатие клавиши "c" на элементе
        actions().sendKeys("c").perform(); // Отправка клавиши "c" ко всему приложению
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Комбинация клавиш Ctrl+F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

        // Нажатие специальных клавиш
        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        // Комплексное действие с мышью (например, drag-and-drop)
        actions().moveToElement($("div"))
                .clickAndHold()
                .moveByOffset(300, 200)
                .release()
                .perform();

        // Выбор опций в выпадающем списке и радио-кнопках
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");
    }

    static void assertionsExamples() {
        // Проверки видимости и наличия текста
        $("").shouldBe(visible);           // Элемент должен быть видим
        $("").shouldNotBe(visible);        // Элемент не должен быть видим
        $("").shouldHave(text("abc"));     // Элемент должен содержать текст "abc"
        $("").shouldNotHave(text("abc"));  // Элемент не должен содержать текст "abc"

        // Проверки появления или исчезновения элемента
        $("").should(appear);
        $("").shouldNot(appear);

        // Проверка с увеличенным временем ожидания
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    static void conditionsExamples() {
        // Проверка видимости и скрытости
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        // Проверка содержания текста (точное и частичное, с учетом регистра)
        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));

        // Проверка текста с помощью регулярного выражения
        $("").should(matchText("[0-9]abc$"));

        // Проверка наличия CSS-класса и CSS-свойств
        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        // Проверка значений элементов (например, полей ввода)
        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        // Проверка наличия атрибутов и их значений
        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        // Проверка состояния чекбоксов/радиокнопок
        $("").shouldBe(checked);

        // Проверка существования элемента в DOM
        $("").should(exist);

        // Проверка активности элемента
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    static void collectionsExamples() {
        // Получение коллекции элементов по CSS и XPath
        $$("div");    // По CSS-селектору
        $$x("//div"); // По XPath

        // Фильтрация коллекций и проверка их размера
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        // Выбор элементов из коллекции
        $$("div").first().click(); // Первый элемент
        elements("div").first().click();
        $$("div").last().click();  // Последний элемент
        $$("div").get(1).click();  // Второй элемент (индекс 1)
        $("div", 1).click();       // Альтернативный способ выбора

        // Поиск элемента с заданным текстом в коллекции
        $$("div").findBy(text("123")).click();

        // Проверка, что коллекция пуста
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty);

        // Проверка текстового содержимого коллекции
        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        // Проверка текстов без учёта порядка
        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        // Проверка наличия хотя бы одного элемента с заданным текстом
        $$("").shouldHave(itemWithText("Gamma"));

        // Проверка размера коллекции по условиям
        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));
    }

    static void fileOperationExamples() throws FileNotFoundException {
        // Загрузка файла по ссылке (<a href="...">)
        File file1 = $("a.fileLink").download();

        // Загрузка файла с использованием режима загрузки (например, через Grid/Selenoid)
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));

        // Загрузка файла с локального диска
        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);

        // Загрузка файла из classpath
        $("#file-upload").uploadFromClasspath("readme.txt");

        // Нажатие кнопки для подтверждения загрузки файла
        $("uploadButton").click();
    }

    static void javascriptExamples() {
        // Выполнение простого JavaScript-кода (вывод alert)
        executeJavaScript("alert('selenide')");

        // Выполнение JavaScript с передачей аргументов
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);

        // Выполнение JavaScript с возвратом результата
        long result = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
    }
}
