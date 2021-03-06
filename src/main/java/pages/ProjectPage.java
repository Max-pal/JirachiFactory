package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectPage {

    private WebDriverWait wait;

    @FindBy(xpath = ".//div[@class='aui-item project-title']/a")
    private WebElement projectName;

    public ProjectPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getProjectNameValue() {
        try {
            wait.until(ExpectedConditions.visibilityOf(projectName));
            return projectName.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
