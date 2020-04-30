package createissue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.IssuePage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateIssueGeneral {
    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private final String BROWSERTYPE = System.getenv("BROWSERTYPE");
    private String projectName;
    private String issueType;
    private String summaryText;
    private WebDriver driver;
    private MainPage mainPage;
    private static IssuePage issuePage;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
        mainPage = new MainPage(driver, wait);
        issuePage = new IssuePage(driver, wait);
        mainPage.login(USERNAME, PASSWORD);
    }

    @ParameterizedTest(name = "\"{0}\"")
    @CsvFileSource(resources = "/data_create_issue.csv", numLinesToSkip = 1)
    public void createIssueGeneralTest(String porjectName, String issueType, String summaryText) {
        issuePage.clickCreateIssue();
        issuePage.selectProject("\"{0}\"");
        issuePage.selectTask(issueType);
        issuePage.fillSummaryField(summaryText);
        issuePage.submitNewIssue();
        issuePage.redirectToSubmittedIssue();
        assertTrue(issuePage.getSubmittedIssueSummary().getText().contains(summaryText));
    }

//    @ParameterizedTest(name = "\"{0}\"")
//    @CsvFileSource(resources = "/data_create_issue2.csv", numLinesToSkip = 1)
//    public void createIssueIssueSelectTest(String porjectName, String issueType, String summaryText) {
//        boolean thrown = false;
//        issuePage.clickCreateIssue();
//        issuePage.selectProject("\"{0}\"");
//        try {
//            issuePage.selectTask(issueType);
//        } catch (Exception e){
//            System.out.println(issueType + " cannot be selected at " + projectName);
//            thrown = true;
//        }
//        assertTrue(thrown);
//    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
}
