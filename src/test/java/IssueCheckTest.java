import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class IssueCheckTest {
    private static final String REPOSITORY = "selenide/selenide";
    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @Test
    @DisplayName("Проверка с чистым Selenide (с Listener)")
    void checkIssueTitleTest(){
        open("https://github.com");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("[data-content=Issues]").shouldHave(text("Issues"));
    }

    @Test
    @DisplayName("Проверка с использованием лямбда-шагов")
    void lambdaCheckIssueTitleTest(){
        step ("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверяем название Issue в репозитории", () ->
                $("[data-content=Issues]").shouldHave(text("Issues")));
    }

    @Test
    @DisplayName("Проверка с шагами аннотации @Step")
    void stepAnnotationCheckIssueTitleTest(){
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.chooseRepository(REPOSITORY);
        steps.issueCheck();
    }
}
