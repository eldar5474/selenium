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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        driver.findElement(By.xpath("(//DIV[@ng-click='setProdProg(prodProg)'])[1]")).click();
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.findElement(By.xpath("//INPUT[@type='checkbox'])[1]")).click();

        //Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        //wait.until(ExpectedCondition.visibilityOf());
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // JavascriptExecutor js = (JavascriptExecutor)driver;
       // js.executeScript("scroll(0, 400);");

        //driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
       // driver.findElement(By.xpath("(//DIV[@ng-click='setProdProg(prodProg)'])[1]")).click();
       // driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();

    }

    @After
    public void afterTest(){
        //driver.quit();
    }
}
