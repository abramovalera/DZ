package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {

    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com/");
    }

    // ValueSource
    @ParameterizedTest
    @ValueSource(strings = {"котики", "погода", "Java"})
    void basicSearchTest(String query) {
        $("#searchbox_input").setValue(query).pressEnter();
        $$("[data-testid='result']").first().shouldBe(visible);
    }

    // CsvSource
    @ParameterizedTest
    @CsvSource({
            "Selenide, selenide.org",
            "JUnit 5, junit.org"
    })
    void domainInResultsTest(String searchQuery, String expectedDomain) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='result'] a[href*='" + expectedDomain + "']")
                .should(exist);
    }

    //CsvFileSource
    @ParameterizedTest
    @CsvFileSource(resources = "/search_data.csv")
    void titleInResultsTest(String searchQuery, String expectedTitle) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='result'] h2").shouldHave(text(expectedTitle));
    }
}