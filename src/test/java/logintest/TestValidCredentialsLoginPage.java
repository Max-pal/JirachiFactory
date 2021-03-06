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
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class TestValidCredentialsLoginPage {

    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }

    @Test
    public void loginTest() {
        mainPage = new MainPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        loginPage.login(USERNAME, PASSWORD);
        Assertions.assertNotNull(mainPage.getLoggedInUserName());
    }
}