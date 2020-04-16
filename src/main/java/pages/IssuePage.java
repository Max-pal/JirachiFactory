package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiter.Waiter;

public class IssuePage {

    private WebDriver driver;
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
    @FindBy(xpath = "//*[contains(@id, 'submit') and contains(@id, 'edit')]")
    private WebElement updateIssueButton;

    public IssuePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getKeyValue() {
        String key = null;
        try {
            key = issueKey.getAttribute("data-issue-key");
            System.out.println(key);
        } catch (NoSuchElementException e) {
            throw new AssertionError("Issue is not accessible", e);
        }
        return key;
    }

    public void editSummary(String newSummary, Waiter waiter, String baseURL) throws InterruptedException {

        // todo: fix the waitings

//        wait.until(ExpectedConditions.visibilityOf(editIssueButton));
//        waiter.waitForPageLoadComplete(driver);

        waiter.get(baseURL, driver);
        editIssueButton.click();
//        waiter.waitForPageLoadComplete(driver);
//        wait.until(ExpectedConditions.visibilityOf(issueDialog));
        summaryField.sendKeys(newSummary);
        updateIssueButton.click();

//        waiter.waitForPageLoadComplete(driver);
    }

    public String getSummary() {
        return summaryFieldCheck.getText();
    }

    public boolean isEditable() {
        boolean issueIsEditable = false;
        try {
            issueIsEditable = editIssueButton.isDisplayed();
            System.out.println("Issue is editable");
        } catch (NoSuchElementException e) {
            throw new AssertionError("Issue is not editable", e);
        }
        return issueIsEditable;
    }
}
