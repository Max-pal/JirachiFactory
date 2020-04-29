package browseprojects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.ProjectPage;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;

public class TestBrowseProjectsFunctionalities {

    private static final String BASEURL = "https://jira.codecool.codecanvas.hu/";
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MainPage mainPage;
    public static ProjectPage projectPage;

    @BeforeAll
    public static void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        projectPage = new ProjectPage(driver, wait);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASEURL);
        mainPage.login(USERNAME, PASSWORD);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_project_name.csv")
    public void BrowseIssueTest(String projectName) {
        projectPage = new ProjectPage(driver, wait);
        driver.get(BASEURL + "projects/" + projectName + "/summary");
        assertTrue(String.format("ERROR: '%s' does not contain '%s'!", projectPage.getProjectNameValue(), projectName), projectPage.getProjectNameValue().contains(projectName));
    }

    @AfterAll
    public static void teardown() {
        driver.close();
    }

}
