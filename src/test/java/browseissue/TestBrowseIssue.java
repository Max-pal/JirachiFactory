package browseissue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IssuePage;
import pages.MainPage;
//TODO: No selenium import in Test class, Page object no junit import

import java.util.concurrent.TimeUnit;

public class TestBrowseIssue {

    private static final String BASEURL = "https://jira.codecool.codecanvas.hu/";
    //TODO no "/" at the end of BASEURL
    private static final String USERNAME = System.getenv("USERNAME_TEST");
    private static final String PASSWORD = System.getenv("PASSWORD_TEST");
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MainPage mainPage;
    private static IssuePage issuePage;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        //TODO util class, getWebdriver func switch case with ENV variable
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        issuePage = new IssuePage(driver, wait);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASEURL);
        mainPage.login(USERNAME, PASSWORD);
    }

    @AfterAll
    public static void teardown() {
        driver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_issue_id.csv")
    public void BrowseIssueTest(String issueId) {
        navigateTo(BASEURL + "browse/" + issueId);
        Assertions.assertEquals(issueId, issuePage.getKeyValue());
    }

    private void navigateTo(String url) {
        driver.get(url);
    }
}
