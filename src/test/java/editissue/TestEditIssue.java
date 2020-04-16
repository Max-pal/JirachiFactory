package editissue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IssuePage;
import pages.MainPage;
import waiter.Waiter;

import java.util.concurrent.TimeUnit;

public class TestEditIssue {

    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static WebDriver driver;
    private static MainPage mainPage;
    private static IssuePage issuePage;
    private static WebDriverWait wait;
    private static Waiter waiter;

    @BeforeAll
    static void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        waiter = new Waiter();
        mainPage = new MainPage(driver, wait);
        issuePage = new IssuePage(driver, wait);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
        mainPage.login(USERNAME, PASSWORD);
    }

    @AfterAll
    static void teardown() {
        driver.close();
    }

    @Test
    public void generalEditIssueTest() throws InterruptedException {
        String baseURL = "https://jira.codecool.codecanvas.hu/browse/MTP-656";

//        driver.get(baseURL);
        issuePage.editSummary("Big issue", waiter, baseURL);
        Assertions.assertEquals("Big issue", issuePage.getSummary());
        // clean-up:
        issuePage.editSummary("Small issue", waiter, baseURL);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_edit_issue_url.csv")
    public void issueEditableTest(String url) {
        driver.get(url);
        Assertions.assertTrue(issuePage.isEditable());
    }

}
