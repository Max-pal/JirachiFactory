package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public IssuePage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getKeyValue() {
        try {
            wait.until(ExpectedConditions.visibilityOf(issueKey));
            return issueKey.getAttribute("data-issue-key");
        } catch (TimeoutException e) {
            return null;
        }
    }

    public void editSummary(String newSummary) {
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
}
