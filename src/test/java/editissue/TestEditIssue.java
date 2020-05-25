package editissue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IssuePage;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Execution(ExecutionMode.CONCURRENT)
public class TestEditIssue {

    private static final String BASEURL = System.getenv("BASEURL");
    protected static final String BROWSER = System.getenv("BROWSER");
    protected static final String GRID_URL = System.getenv("GRID_URL");
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MainPage mainPage;
    private static IssuePage issuePage;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        switch (BROWSER) {
            case "chrome":
                driver = new RemoteWebDriver(new URL(GRID_URL.replace("{PASSWORD}", PASSWORD)), new ChromeOptions());
                break;
            case "firefox":
                driver = new RemoteWebDriver(new URL(GRID_URL.replace("{PASSWORD}", PASSWORD)), new FirefoxOptions());
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver, wait);
        issuePage = new IssuePage(driver, wait);
        driver.get(BASEURL);
        mainPage.login(USERNAME, PASSWORD);
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void generalEditIssueTest() {
        String url = BASEURL + "browse/MTP-656";
        navigateTo(url);
        issuePage.editSummary("Big issue", url);
        Assertions.assertEquals("Big issue", issuePage.getSummary());
        // clean-up:
        issuePage.editSummary("Small issue", url);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_issue_id.csv")
    public void issueEditableTest(String issueId) {
        String url = BASEURL + "browse/" + issueId;
        navigateTo(url);
        Assertions.assertTrue(issuePage.isEditable());
    }

    private void navigateTo(String url) {
        driver.get(url);
    }

}
