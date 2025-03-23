import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @BeforeAll
    public static void setup() {
        Configuration.holdBrowserOpen = true; // способ оставить браузер открытым
    }

    @Test
    void testNumberFour() {
        open("https://github.com/selenide/selenide");
        $("[id=wiki-tab]").click();
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").shouldBe(visible).shouldHave(Condition.text("Soft Assertions"));
        //visible убеждаемся что элемент видемый далее Проверяет, что внутри элемента присутствует нужный текст.
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $("#wiki-content").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +//# ищет элемент на странице с id="wiki-content", разобратся потом почему #, точки Ul и прочее, как ставится зачем и почему (непонятно)
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }

    @Test
    void testNumberFive() {
        open("https://github.com/");
        $(".HeaderMenu-nav").find(byText("Solutions")).hover();
        // элемент по классу, селектор должен начинаться с точки
        $(".HeaderMenu-nav").find(byText("Enterprises")).click();
        $(".application-main").shouldHave(text("The AI-powered developer platform"));

    }
    @Test
    void testNumberFivePointTwo() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().moveToElement($("#column-a")) //# используется для выбора элемента по его идентификатору (id)
                .clickAndHold()
                .moveByOffset(250, 0)
                .release()
                .perform();
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}



