package logout;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestLogout {

    private static final String BASEURL = "https://jira.codecool.codecanvas.hu/";
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MainPage mainPage;

    @BeforeAll
    public static void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASEURL);
        mainPage.login(USERNAME, PASSWORD);
    }

    @AfterAll
    public static void teardown() {
        driver.close();
    }

    @Test
    public void logoutTest() {
        mainPage.logout();

        Assertions.assertTrue(mainPage.userIsLoggedOut());
    }
}
