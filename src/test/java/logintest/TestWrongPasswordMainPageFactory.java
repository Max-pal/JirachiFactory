package logintest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPageFactory;

import java.util.concurrent.TimeUnit;

public class TestWrongPasswordMainPageFactory {

    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private final String WRONGPASSWORD = System.getenv("WRONGPASSWORD");
    private WebDriver driver;
    private MainPageFactory mainPageFactory;
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
        mainPageFactory = new MainPageFactory(driver, wait);

        mainPageFactory.login(USERNAME, WRONGPASSWORD);
        Assertions.assertNotNull(mainPageFactory.getErrorMessage());

        mainPageFactory.login(USERNAME, PASSWORD);
        Assertions.assertEquals(USERNAME, mainPageFactory.getLoggedInUserName());
    }
}
