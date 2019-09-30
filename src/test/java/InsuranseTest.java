import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class InsuranseTest {
    WebDriver driver;
    String baseUrl;
    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver");
        baseUrl = "https://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);


    }

    @Test
    public void testInsurance(){
        driver.findElement(By.xpath("//SPAN[@class='lg-menu__text'][text()='Страхование']")).click();
        driver.findElement(By.xpath("(//A[@href='/ru/person/bank_inshure/insuranceprogram/life/travel'][text()='Страхование путешественников'][text()='Страхование путешественников'])[1]")).click();
        WebElement title = driver.findElement(By.xpath("//H1[text()='Страхование путешественников']"));
        Assert.assertEquals("Страхование путешественников", title.getText());
        driver.findElement(By.xpath("//IMG[@src='/portalserver/content/atom/contentRepository/content/person/travel/banner-zashita-traveler.jpg?id=f6c836e1-5c5c-4367-b0d0-bbfb96be9c53']")).click();

        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        driver.findElement(By.xpath("(//DIV[@ng-click='setProdProg(prodProg)'])[1]")).click();
        driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();
        //Заполнение полей для "Застрахованные"
        fillField(By.xpath("//INPUT[@name='insured0_surname']"), "Ivanov");
        fillField(By.xpath("//INPUT[@name='insured0_name']"), "Ivan");
        fillField(By.cssSelector("input[name=\"insured0_birthDate\"]"),"01011971");

        //Заполнение полей страхователь

        fillField(By.cssSelector("input[name=\"surname\"]"), "Смирнов");
        fillField(By.cssSelector("input[name=\"name\"]"), "Сергей");
        fillField(By.cssSelector("input[name=\"middlename\"]"), "Антонович");
        fillField(By.cssSelector("input[name=\"birthDate\"]"),"02021972");

        //Данные паспорта РФ
        fillField(By.cssSelector("input[name=\"passport_series\"]"),"1234");
        fillField(By.cssSelector("input[name=\"passport_number\"]"),"678901");
        fillField(By.cssSelector("input[name=\"issueDate\"]"),"11112011");
        fillField(By.cssSelector("textarea[name=\"issuePlace\"]"),"ТП УФМС РОССИИ по г.Москва");


        //Продолжить
        driver.findElement(By.cssSelector("span.b-continue-btn")).click();
        WebElement fullness = driver.findElement(By.cssSelector("div[ng-show=\"tryNext && myForm.$invalid\"]"));
        Assert.assertEquals("Заполнены не все обязательные поля", fullness.getText());


    }
    public void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    @After
    public void afterTest(){
        //driver.quit();
    }
}
