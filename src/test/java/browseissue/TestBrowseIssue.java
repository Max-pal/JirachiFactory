package browseissue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IssuePage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestBrowseIssue {

    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private WebDriver driver;
    private MainPage mainPage;
    private IssuePage issuePage;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
        mainPage.login(USERNAME, PASSWORD);
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_browse_issue_url.csv")
    public void BrowseIssueTest(String issueUrl, String issueKey) {
        issuePage = new IssuePage(driver, wait);

        driver.get(issueUrl);
        Assertions.assertEquals(issuePage.getKeyValue(), issueKey);
    }
}
