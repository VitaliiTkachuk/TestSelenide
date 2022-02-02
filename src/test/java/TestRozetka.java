import com.codeborne.selenide.Selenide;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestRozetka {

    //Users can add products to the Cart
    @Test
    public void UserCanAddOneProductToCartFromSearchNonregister() {
        BasicConfigurator.configure();
        String searchName;
        searchName = "чайник";
        open("https://rozetka.com.ua/");
        $("input[name=search]").setValue(searchName).pressEnter();
        $("span[class=goods-tile__title]").shouldHave(text(searchName)); //Check that product is found.
        $("app-buy-button > button").click();
        $(By.xpath("//header/div/div/ul/li[7]/rz-cart/button")).click();
        $("h3[class='modal__heading']").shouldHave(text("Корзина")); //Check that cart modal is opened.
        $("div[class='cart-receipt__sum-price']").shouldBe(visible); //Check that on cart modal is product.
    }

    //The user can place an order after adding the product to the Сart.
    @Test
    public void UserCanPlaceOrderAfterAddingToCartNonregister() {
        BasicConfigurator.configure();
        String searchName;
        searchName = "чайник";
        open("https://rozetka.com.ua/");
        $("input[name='search']").setValue(searchName).pressEnter();
        $("span[class='goods-tile__title']").shouldHave(text(searchName)); //Check that product is found.
        $("app-buy-button > button").click();
        $(By.xpath("//header/div/div/ul/li[7]/rz-cart/button")).click();
        $("h3[class=modal__heading]").shouldHave(text("Корзина")); //Check that cart modal is opened.
        $("div[class=cart-receipt__sum-price]").shouldBe(visible); //Check that on cart modal is product.
        Selenide.sleep(3000);
        $(By.xpath("//a[contains(text(), 'Оформить заказ')]")).click();
        $(By.xpath("//h1[contains(text(), 'Оформление заказа')]")).shouldBe(visible);//Check that checkout modal is opened.
        $("div[class=check-order]").shouldBe(visible); //Check that order elements is presented (high-level).
        $("div[class=checkout-total]").shouldBe(visible);//Check that checkout elements is presented (high-level).
    }

    //Users can add products to the Wishlist
    @Test
    public void UserCanAddOneProductToWishlistFromSearchNonregister() { //Users cant add products to a wishlist without registration!
        BasicConfigurator.configure();
        String searchName;
        searchName = "чайник";
        open("https://rozetka.com.ua/");
        $("input[name='search']").setValue(searchName).pressEnter();
        $("span[class='goods-tile__title']").shouldHave(text(searchName)); //Check that product is found.
        Selenide.sleep(1000);
        $(By.xpath("//li[1]//app-goods-wishlist/button")).click();
        Selenide.sleep(1000);
        $(By.xpath("//h3[contains(text(), 'Вход')]")).shouldBe(visible);//Check that login modal is opened.
    }

    //Users can login in the system
    @Test
    public void UserCanLoginByEmailAndPasswordNonregister() { //Users can't login in to the system without Captcha
        BasicConfigurator.configure();
        String email;
        String password;
        email = "tkachuk.vitalij@gmail.com";
        password = "MyPasswordForRozetka1";
        open("https://rozetka.com.ua/");
        $(By.xpath("//rz-user/button")).click();
        $("#auth_email").setValue(email);
        $("#auth_pass").setValue(password);
        $(By.xpath("//h3[contains(text(), 'Вход')]")).shouldBe(visible);//Check that login modal is opened.
        $(By.xpath("//button[contains(text(), 'Войти')]")).click();
        Selenide.sleep(1000);
        $(By.xpath("//p[contains(text(), 'Необходимо подтвердить, что вы не робот')]")).shouldBe(visible); //Check that reCAPTCHA is present.
    }

    //Users can view information about products
    @Test
    public void UserCanViewInfoAboutOneProductFromHomePageNonregister() {
        BasicConfigurator.configure();
        open("https://rozetka.com.ua/");
        $(By.xpath("//a[@class = 'menu-categories__link'][contains (@href , 'https://rozetka.com.ua/computers-notebooks')]")).click();
        $(By.xpath("//h1[contains(text(), 'Компьютеры')]")).shouldBe(visible); //Check that all products page is opened.
        $(By.xpath("//img[contains(@alt, 'Компьютеры')]")).click();
        $(By.xpath("//h1[contains(text(), 'Компьютеры')]")).shouldBe(visible); //Check that products page is opened.
        $(By.xpath("//img[contains(@alt, 'Компьютер')]")).click();
        $("h1[class='product__title']").shouldHave(text("Компьютер")); //Check that products cart is opened.
    }

    //Users can view information about Company
    @Test
    public void UserCanViewInfoAboutCompanyFromMenuNonregister() {
        BasicConfigurator.configure();
        open("https://rozetka.com.ua/");
        $("button[aria-label='Открыть меню']").click();
        $(By.xpath("//h3[contains(text(), 'Информация о компании')]")).shouldBe(visible); //Check that main menu is opened.
        $(By.xpath("//*[@id=\"cdk-overlay-0\"]//rz-service-links//a[contains(text(), 'О нас')]")).click();
        $("h1[class='rz-static-page__heading']").shouldHave(text("О нас")); //Check that about company is opened.
    }

    //Users can change site language
    @Test
    public void UserCanChangeSiteLanguageToUkrainianNonregister() {
        BasicConfigurator.configure();
        open("https://rozetka.com.ua/");
        $(By.xpath("//a[@class = 'lang__link ng-star-inserted'][contains(@href, 'ua')]")).click();
        $(By.xpath("//a[@class = 'lang__link ng-star-inserted'][contains(@href, 'ua')]")).shouldBe(disappear); //Check that link is disable
        $(By.xpath("//h3[contains(text(), 'Інформація про компанію')]")).shouldBe(visible); //Check that language is changed.
        Selenide.sleep(2000);
    }

    //Users can add products to the Cart
    @Test
    public void UserRemoveOneProductFromCartNonregister() {
        BasicConfigurator.configure();
        String searchName;
        searchName = "чайник";
        open("https://rozetka.com.ua/");
        $("input[name=search]").setValue(searchName).pressEnter();
        $("span[class=goods-tile__title]").shouldHave(text(searchName)); //Check that product is found.
        $("app-buy-button > button").click();
        $(By.xpath("//header/div/div/ul/li[7]/rz-cart/button")).click();
        $("h3[class=modal__heading]").shouldHave(text("Корзина")); //Check that cart modal is opened.
        $("div[class=cart-receipt__sum-price]").shouldBe(visible); //Check that on cart modal is product.
        Selenide.sleep(2000);
        $("button[class='button button--white button--small context-menu__toggle']").click();
        $(By.xpath("//button[contains(text(), ' Удалить ')]")).click();
        $(By.xpath("//h4")).shouldHave(text("Корзина пуста")); //Check that product is removed from the cart.
        Selenide.sleep(2000);
    }

    //Users can view company social pages
    @Test
    public void UserCanViewCompanySocialPagesInstagramNonregister() {
        BasicConfigurator.configure();
        open("https://rozetka.com.ua/");
        $(By.xpath("//a[@class='socials__link socials__link--instagram']")).shouldBe(enabled); //Check that social link is enabled for user.
        $(By.xpath("//a[@class='socials__link socials__link--instagram']")).click();
        Selenide.sleep(2000);
        switchTo().window(1);
        $(By.xpath("//h2[contains(text(),'rozetkaua')]")).isDisplayed(); //Check that social page is displayed
    }

    //Users can use help centre
    @Test
    public void UserCanUseSearchInHelpCentreNonregister() {//Used capcha after click
        BasicConfigurator.configure();
        String question;
        question = "возврат";
        open("https://rozetka.com.ua/");
        $(By.xpath("//rz-main-page//aside//a[contains(text(), ' Справочный центр')]")).click();
        Selenide.sleep(6000);
        assert $(By.name("query")).isDisplayed(); //Check that searchbar is visible
        $(By.name("query")).setValue(question); //Check that searchbar is accepting
        Selenide.sleep(5000);
        $(By.xpath("//html/body/main/div[1]/div/div/div/form/input[3]")).click();
        Selenide.sleep(6000);
    }

}
