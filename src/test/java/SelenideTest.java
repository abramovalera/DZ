import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
}

