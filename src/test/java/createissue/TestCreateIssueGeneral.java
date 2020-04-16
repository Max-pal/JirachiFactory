package createissue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateIssueGeneral {
    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");
    private final String projectName = System.getenv("projectName");
    private final String issueType = System.getenv("issueType");
    private final String summaryText = System.getenv("summaryText");
    private WebDriver driver;
    private MainPageFactory mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/");
        mainPage = new MainPageFactory(driver, wait);
        mainPage.login(USERNAME, PASSWORD);
    }

    @Test
    public void createIssueTest(String porjectName, String issueType, String summaryText){
        mainPage.clickCreateIssue();
        mainPage.selectProject("COALA Project (COALA)");
        mainPage.selectTask("Task");
        mainPage.fillSummaryField("Test from selenium");
        mainPage.submitNewIssue();
        mainPage.redirectToSubmittedIssue();
        assertTrue(mainPage.getSubmittedIssueSummary().getText().contains("Test from selenium"));
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
}
