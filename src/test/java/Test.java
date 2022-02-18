import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Test {
    @org.junit.Test
    public void googleTest() {
        //-------------------
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        //--------------------

        open("https://www.google.com.ua/");
    }

    @org.junit.Test
    public void openBeetrootAcademy() {

        BasicConfigurator.configure();

        open("https://www.google.com");
        $(By.name("q")).setValue("Beetroot Academy").pressEnter();
        assert WebDriverRunner.url().contains("q=Beetroot+Academy");
        Selenide.sleep(1000);
        $(By.xpath("//h3[contains(text(),'интенсивные IT-курсы в дружеской атмосфере')]")).shouldBe(Condition.visible);
        $(By.xpath("//h3[contains(text(),'интенсивные IT-курсы в дружеской атмосфере')]")).click();
    }


}
