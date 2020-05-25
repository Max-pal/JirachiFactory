package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {

    private WebDriverWait wait;

    @FindBy(id = "key-val")
    private WebElement issueKey;
    @FindBy(id = "summary-val")
    private WebElement summaryFieldCheck;
    @FindBy(id = "edit-issue")
    private WebElement editIssueButton;
    @FindBy(id = "edit-issue-dialog")
    private WebElement issueDialog;
    @FindBy(id = "summary")
    private WebElement summaryField;
    @FindBy(id = "edit-issue-submit")
    private WebElement updateIssueButtonModal;
    @FindBy(id = "issue-edit-submit")
    private WebElement updateIssueButtonDefault;
    @FindBy(xpath = ".//a[@id=\"create_link\"]")
    private WebElement createIssueButton;
    @FindBy(xpath = ".//div[@id=\"create-issue-dialog\"]")
    private WebElement createIssueDialoge;
    @FindBy(xpath = ".//input[@id=\"project-field\"]")
    private WebElement projectSelectorDropdown;
    @FindBy(xpath = ".//input[@id=\"issuetype-field\"]")
    private WebElement issueTypeSelectorDropdown;
    @FindBy(id = "create-issue-submit")
    private WebElement createIssueSubmitButton;
    @FindBy(xpath = ".//input[@id=\"create-issue-submit\"]")
//    TODO: " -> '

    private WebElement issueSubmittedMessage;
    @FindBy(id = "summary-val")
    private WebElement submittedIssueSummary;

    public IssuePage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
//        AjaxElementLocatorFactory;
    }
    //TODO: Selenium PageFactory AJAX wait library

    public String getKeyValue() {
        try {
            wait.until(ExpectedConditions.visibilityOf(issueKey));
            return issueKey.getAttribute("data-issue-key");
        } catch (TimeoutException e) {
            return null;
        }
    }

    public void editSummary(String newSummary, String baseURL) {
        wait.until(ExpectedConditions.elementToBeClickable(editIssueButton));
        editIssueButton.click();
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.sendKeys(newSummary);

        updateIssueButtonModal.click();
    }


    public String getSummary() {
        try {
            wait.until(ExpectedConditions.visibilityOf(summaryFieldCheck));
            return summaryFieldCheck.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean isEditable() {
        try {
            wait.until(ExpectedConditions.visibilityOf(editIssueButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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
