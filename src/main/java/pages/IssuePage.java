package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "key-val")
    private WebElement issueKey;

    public IssuePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getKeyValue() {
        System.out.println(issueKey.getAttribute("data-issue-key"));
        return issueKey.getAttribute("data-issue-key");
    }
}