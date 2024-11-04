import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Lesson8And9Test {
    private static WebDriver driver;
    WebElement buttonAgree;

    @BeforeEach
    public void setUP() {

        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
        buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        new WebDriverWait(driver, 5);
    }

    @Test
    public void blockNameTest() {

        WebElement blockTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        Assertions.assertEquals("Онлайн пополнение\n" +
                "без комиссии", blockTitle.getText());

    }

    @Test
    public void blockPayPartnersLogosTest() {

        WebElement logos = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul"));
        logos.isDisplayed();
        WebElement logoVisa = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li/img[@alt='Visa']"));
        logoVisa.isDisplayed();
        WebElement logosVerifVisa = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li/img[@alt='Verified By Visa']"));
        logosVerifVisa.isDisplayed();
        WebElement logosMasterCard = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li/img[@alt='MasterCard']"));
        logosMasterCard.isDisplayed();
        WebElement logosMasterCardSec = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li/img[@alt='MasterCard Secure Code']"));
        logosMasterCardSec.isDisplayed();
        WebElement logosBelcard = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li/img[@alt='Белкарт']"));
        logosBelcard.isDisplayed();

        int logosCount = driver.findElements(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']/ul/li")).size();
        Assertions.assertEquals(5, logosCount);

    }

    @Test
    public void linkServiceDetailsTest() {

        WebElement link = driver.findElement(By.xpath("//div[@class='pay__wrapper']/a[contains(text(), 'Подробнее о сервисе')]"));
        link.click();
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("Порядок оплаты и безопасность интернет платежей", title);
        Assertions.assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", url);

    }

    @Test
    public void buttonContinueTest() {

        WebElement numberPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='phone'][@id='connection-phone']"));
        numberPlaceholder.click();
        numberPlaceholder.sendKeys("297777777");
        WebElement sumPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='total_rub'][@id='connection-sum']"));
        sumPlaceholder.click();
        sumPlaceholder.sendKeys("55");
        WebElement emailPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='email'][@id='connection-email']"));
        emailPlaceholder.click();
        emailPlaceholder.sendKeys("test@gmail.com");
        WebElement button = driver.findElement(By.xpath("//div[@class='pay__forms']//button[contains(text(), 'Продолжить')]"));
        button.click();
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        frame.isDisplayed();

    }

}
