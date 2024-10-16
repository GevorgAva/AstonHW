import junit.framework.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Lesson8And9Test {
    private static WebDriver driver;

    @BeforeEach
    public void setUP() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        new WebDriverWait(driver, 5);
    }

    @Test
    public void blockNameTest() {

        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(., 'Онлайн пополнение')][contains(., 'без комиссии')]"));
        Assert.assertEquals("Онлайн пополнение\n" +
                "без комиссии", blockTitle.getText());

    }

    @Test
    public void blockPayPartnersLogosTest() {

        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
        WebElement logos = driver.findElement(By.xpath("//div[@class='pay__wrapper']/div[@class='pay__partners']"));
        logos.isDisplayed();

    }

    @Test
    public void linkServiceDetailsTest() {
        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
        WebElement link = driver.findElement(By.xpath("//div[@class='pay__wrapper']/a[contains(text(), 'Подробнее о сервисе')]"));
        link.click();
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("Порядок оплаты и безопасность интернет платежей", title);
        Assert.assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", url);

    }

    @Test
    public void buttonContinueTest() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 2);

        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
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
        new WebDriverWait(driver, 20);
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        frame.isDisplayed();

    }

    @Test
    public void lesson9Part1Checks() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 2);

        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();

        WebElement numberPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='phone'][@id='connection-phone']"));
        String numberPlaceholderValue = numberPlaceholder.getAttribute("placeholder");
        Assert.assertEquals("Номер телефона", numberPlaceholderValue);

        WebElement sumPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='total_rub'][@id='connection-sum']"));
        String sumPlaceholderValue = sumPlaceholder.getAttribute("placeholder");
        Assert.assertEquals("Сумма", sumPlaceholderValue);

        WebElement emailPlaceholder = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='email'][@id='connection-email']"));
        String emailPlaceholderValue = emailPlaceholder.getAttribute("placeholder");
        Assert.assertEquals("E-mail для отправки чека", emailPlaceholderValue);


        WebElement paymentSelect = driver.findElement(By.xpath("//div[@class='pay__form']//button[@class='select__header']"));
        paymentSelect.click();

        WebElement homeInternetSelectButton = driver.findElement(By.xpath("//div[@class='pay__form']//ul[@class='select__list']/li/p[contains(text(), 'Домашний интернет')]"));
        homeInternetSelectButton.click();

        WebElement numberPlaceholderInternet = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='phone'][@id='internet-phone']"));
        String numberPlaceholderInternetValue = numberPlaceholderInternet.getAttribute("placeholder");
        Assert.assertEquals("Номер абонента", numberPlaceholderInternetValue);

        WebElement sumPlaceholderInternet = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='total_rub'][@id='internet-sum']"));
        String sumPlaceholderInternetValue = sumPlaceholderInternet.getAttribute("placeholder");
        Assert.assertEquals("Сумма", sumPlaceholderInternetValue);

        WebElement emailPlaceholderInternet = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='email'][@id='internet-email']"));
        String emailPlaceholderInternetValue = emailPlaceholderInternet.getAttribute("placeholder");
        Assert.assertEquals("E-mail для отправки чека", emailPlaceholderInternetValue);

        paymentSelect.click();

        WebElement rassrochkaSelectButton = driver.findElement(By.xpath("//div[@class='pay__form']//ul[@class='select__list']/li/p[contains(text(), 'Рассрочка')]"));
        rassrochkaSelectButton.click();

        WebElement numberOfAccount = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='score'][@id='score-instalment']"));
        String numberOfAccountValue = numberOfAccount.getAttribute("placeholder");
        Assert.assertEquals("Номер счета на 44", numberOfAccountValue);

        WebElement sumForRassrochka = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='total_rub'][@id='instalment-sum']"));
        String sumForRassrochkaValue = sumForRassrochka.getAttribute("placeholder");
        Assert.assertEquals("Сумма", sumForRassrochkaValue);

        WebElement emailPlaceholderRassrochka = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='email'][@id='instalment-email']"));
        String emailPlaceholderRassrochkaValue = emailPlaceholderRassrochka.getAttribute("placeholder");
        Assert.assertEquals("E-mail для отправки чека", emailPlaceholderRassrochkaValue);

        paymentSelect.click();

        WebElement debtSelectButton = driver.findElement(By.xpath("//div[@class='pay__form']//ul[@class='select__list']/li/p[contains(text(), 'Задолженность')]"));
        debtSelectButton.click();

        WebElement numberOfAccountDebt = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='score'][@id='score-arrears']"));
        String numberOfAccountDebtValue = numberOfAccountDebt.getAttribute("placeholder");
        Assert.assertEquals("Номер счета на 2073", numberOfAccountDebtValue);

        WebElement sumForDebt = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='total_rub'][@id='arrears-sum']"));
        String sumForDebtValue = sumForDebt.getAttribute("placeholder");
        Assert.assertEquals("Сумма", sumForDebtValue);

        WebElement emailPlaceholderDebt = driver.findElement(By.xpath("//div[@class='pay__forms']//input[@class='email'][@id='arrears-email']"));
        String emailPlaceholderDebtValue = emailPlaceholderDebt.getAttribute("placeholder");
        Assert.assertEquals("E-mail для отправки чека", emailPlaceholderDebtValue);

    }

    @Test
    public void lesson9Part2Checks() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 2);

        WebElement buttonAgree = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        buttonAgree.click();
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

        new WebDriverWait(driver, 20);
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
        driver.switchTo().frame(frame);

        WebElement logos = driver.findElement(By.xpath("//div[contains(@class, 'card')]//div[contains(@class, 'cards-brands ng-tns-c46-1')]"));

        wait.until(d -> logos.isDisplayed());

        WebElement numberText = driver.findElement(By.xpath("//div[@class='pay-description__text']/span[contains(text(), 'Номер:375297777777')]"));

        WebElement buttonText = driver.findElement(By.xpath("//button[@class='colored disabled'][contains(text(), ' Оплатить  55.00 BYN ')]"));

        WebElement sumTextTop = driver.findElement(By.xpath("//div[@class='pay-description__cost']/span[contains(text(), '55.00 BYN')]"));

        WebElement cardNumberInput = driver.findElement(By.xpath("//app-card-input[@class='ng-tns-c61-0 ng-star-inserted']//label[@class='ng-tns-c46-1 ng-star-inserted']"));
        WebElement expirationDateInput = driver.findElement(By.xpath("//app-card-input[@class='ng-tns-c61-0 ng-star-inserted']//label[@class='ng-tns-c46-4 ng-star-inserted']"));
        WebElement cvcInput = driver.findElement(By.xpath("//app-card-input[@class='ng-tns-c61-0 ng-star-inserted']//label[@class='ng-tns-c46-5 ng-star-inserted']"));
        WebElement holderInfoInput = driver.findElement(By.xpath("//app-card-input[@class='ng-tns-c61-0 ng-star-inserted']//label[@class='ng-tns-c46-3 ng-star-inserted']"));

        String cardNumberInputValue = cardNumberInput.getAttribute("innerHTML");
        String buttonTextValue = buttonText.getAttribute("innerHTML");
        String numberTextValue = numberText.getAttribute("innerHTML");
        String sumTextTopValue = sumTextTop.getAttribute("innerHTML");
        String expirationDateInputValue = expirationDateInput.getAttribute("innerHTML");
        String cvcInputValue = cvcInput.getAttribute("innerHTML");
        String holderInfoInputValue = holderInfoInput.getAttribute("innerHTML");


        Assert.assertEquals(" Оплатить  55.00 BYN <!---->", buttonTextValue);
        Assert.assertEquals("Оплата: Услуги связи\n" +
                "Номер:375297777777", numberTextValue);
        Assert.assertEquals("55.00 BYN", sumTextTopValue);
        Assert.assertEquals("Номер карты", cardNumberInputValue);
        Assert.assertEquals("Срок действия", expirationDateInputValue);
        Assert.assertEquals("CVC", cvcInputValue);
        Assert.assertEquals("Имя держателя (как на карте)", holderInfoInputValue);

    }


}
