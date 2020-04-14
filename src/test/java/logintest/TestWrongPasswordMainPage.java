package logintest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestWrongPasswordMainPage {

    WebDriver driver;
    MainPage mainPage;
    String username = System.getenv("username");
    String password = System.getenv("password");
    String wrongPassword = "wrongPass";

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
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
        mainPage = new MainPage(driver);

        mainPage.login(username, password);

    }
}
