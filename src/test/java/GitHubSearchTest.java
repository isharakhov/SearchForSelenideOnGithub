import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GitHubSearchTest {

    private static final String SEARCH_VALUE = "Selenide";
    private static final String SEARCH_CONTAINS = "SoftAssertions";
    private static final SelenideElement searchField =
            $(byAttribute("placeholder", "Search GitHub"));
    private static final SelenideElement wikis =
            $(byText("Wikis"));
    private final static SelenideElement searchResult =
            $x("//main/div/div[3]")
                    .$(byText(SEARCH_CONTAINS))
                    .closest("div")
                    .$("a");
    private final static SelenideElement junitTitle =
            $(byText("Using JUnit5 extend test class:"));
    private final static SelenideElement codeExample =
            $x("//*[@id=\"wiki-body\"]/div/div[5]");

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1980x1050";
        open("https://github.com/");
    }

    @Test
    public void searchSoftAssertionsOnGitHub() {

        searchField.setValue(SEARCH_VALUE)
                .pressEnter();
        wikis.click();
        searchResult.click();
        assertEquals(junitTitle.getText(), "Using JUnit5 extend test class:", "Заголовок c JUnit5 отсутствует");
        assertTrue(codeExample.isDisplayed(), "Пример кода для JUnit5 отсутствует");
    }
}
