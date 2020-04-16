package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MainPageFactory {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "login-form-username")
    private WebElement usernameField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your username and password are incorrect')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    private WebElement userProfilePic;

    @FindBy(xpath = "//*[@id=\"create_link\"]")
    private WebElement createIssueButton;

    @FindBy(xpath = "//*[@id=\"create-issue-dialog\"]")
    private WebElement createIssueDialoge;

    @FindBy(xpath = "//*[@id=\"project-field\"]")
    private WebElement projectSelectorDropdown;

    @FindBy(xpath = "//*[@id=\"issuetype-field\"]")
    private WebElement issueTypeSelectorDropdown;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "create-issue-submit")
    private WebElement createIssueSubmitButton;

    @FindBy(xpath = "//*[contains(@class, 'issue-created-key')]")
    private WebElement issueSubmittedMessage;

    @FindBy(id = "summary-val")
    private WebElement submittedIssueSummary;

    public MainPageFactory(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public String getLoggedInUserName() {
        wait.until(ExpectedConditions.visibilityOf(userProfilePic));
        System.out.println(userProfilePic.getAttribute("data-username"));
        return userProfilePic.getAttribute("data-username");
    }

    public void clickCreateIssue(){
        createIssueButton.click();
        wait.until(ExpectedConditions.visibilityOf(createIssueDialoge));
    }

    public void selectProject(String projectName){
        projectSelectorDropdown.sendKeys(projectName);
    }

    public void selectTask(String issueType){
        issueTypeSelectorDropdown.click();
        issueTypeSelectorDropdown.clear();
        issueTypeSelectorDropdown.sendKeys(issueType);
    }

    public void fillSummaryField(String message){
        summaryField.click();
        summaryField.sendKeys(message);
    }

    public void submitNewIssue(){
        createIssueSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'issue-created-key')]")));
    }
    public void redirectToSubmittedIssue(){
        wait.until(ExpectedConditions.visibilityOf(issueSubmittedMessage));
        issueSubmittedMessage.click();
        wait.until(ExpectedConditions.visibilityOf(submittedIssueSummary));
    }

    public WebElement getSubmittedIssueSummary() {
        return submittedIssueSummary;
    }
}
