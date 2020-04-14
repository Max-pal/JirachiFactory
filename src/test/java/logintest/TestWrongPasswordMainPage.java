package logintest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestWrongPasswordMainPage {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;
    private String username = System.getenv("username");
    private String validPassword = System.getenv("password");
    private String wrongPassword = "wrongPass";

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

//    @ParameterizedTest
//    @CsvFileSource(resources = "/data_login_wrong_password.csv")
//    String username, String validPassword, String wrongPassword
    @Test
    public void loginTest() {
        mainPage = new MainPage(driver, wait);

        mainPage.login(username, wrongPassword);
        Assertions.assertNotNull(mainPage.getErrorMessage());
        mainPage.login(username, validPassword);
    }
}
