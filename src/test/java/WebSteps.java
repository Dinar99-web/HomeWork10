import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step ("Открываем главную страницу")
    public void openMainPage (){
        open("https://github.com");
    }
    @Step ("Ищем репозиторий")
    public void searchForRepository(String repo){
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }
    @Step ("Кликаем по ссылке репозитория")
    public void chooseRepository(String repo){
        $(linkText(repo)).click();
    }
    @Step ("Проверяем название Issue в репозитории")
    public void issueCheck(){
        $("[data-content=Issues]").shouldHave(text("Issues"));
    }
 }

