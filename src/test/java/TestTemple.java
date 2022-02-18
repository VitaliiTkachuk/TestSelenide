import com.codeborne.selenide.Condition;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TestTemple {
    @Test
    public void userCanSearchKeywordWithGoogle() {
        BasicConfigurator.configure();

        open("https://www.google.com/");
        $(By.name("q")).setValue("Selenide").pressEnter();
        $(".tF2Cxc").shouldHave(Condition.text("Selenide"));

    }
}
