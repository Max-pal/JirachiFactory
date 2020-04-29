package createissue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateIssueGeneral {
    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private String projectName;
    private String issueType;
    private String summaryText;
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
        mainPage = new MainPage(driver, wait);
        mainPage.login(USERNAME, PASSWORD);
    }

    @ParameterizedTest(name = "\"{0}\"")
    @CsvFileSource(resources = "/data_create_issue.csv", numLinesToSkip = 1)
    public void createIssueTest(String porjectName, String issueType, String summaryText) {
        mainPage.clickCreateIssue();
        mainPage.selectProject("\"{0}\"");
        mainPage.selectTask(issueType);
        mainPage.fillSummaryField(summaryText);
        mainPage.submitNewIssue();
        mainPage.redirectToSubmittedIssue();
        assertTrue(mainPage.getSubmittedIssueSummary().getText().contains(summaryText));
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
}
