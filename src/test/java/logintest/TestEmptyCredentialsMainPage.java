package logintest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestEmptyCredentialsMainPage {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;
    private final String USERNAME = System.getenv("USERNAME");
    private final String VALIDPASSWORD = System.getenv("PASSWORD");
    private final String EMPTYPASSWORD = "";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
    }

    @Test
    public void loginTest() {
        mainPage = new MainPage(driver, wait);
        mainPage.login(USERNAME, EMPTYPASSWORD);
        Assertions.assertNotNull(mainPage.getErrorMessage());
        mainPage.login(USERNAME, VALIDPASSWORD);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
