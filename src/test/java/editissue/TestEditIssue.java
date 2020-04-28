package test.editissue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.IssuePage;
import pages.MainPage;
import test.TestUtil;

public class TestEditIssue {

    private final String BASEURL = "https://jira.codecool.codecanvas.hu/";
    public static MainPage mainPage;
    private static IssuePage issuePage;

    @BeforeAll
    public static void setup() {
        TestUtil.setup(new FirefoxDriver(), BASEURL);
        mainPage = new MainPage(TestUtil.driver, TestUtil.wait);
        issuePage = new IssuePage(TestUtil.driver, TestUtil.wait);
        mainPage.login(TestUtil.USERNAME, TestUtil.PASSWORD);
    }

    @AfterAll
    public void teardown() {
        TestUtil.teardown();
    }

    @Test
    public void generalEditIssueTest() throws InterruptedException {

        String baseURL = "https://jira.codecool.codecanvas.hu/browse/MTP-656";
        TestUtil.driver.get(baseURL);
        issuePage.editSummary("Big issue", baseURL);
        Assertions.assertEquals("Big issue", issuePage.getSummary());
        // clean-up:
        issuePage.editSummary("Small issue", baseURL);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_edit_issue_url.csv")
    public void issueEditableTest(String url) {
        TestUtil.driver.get(url);
        Assertions.assertTrue(issuePage.isEditable());
    }

}
