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

    @FindBy(xpath = "//html/body/div/section/div[1]/div/div[1]/header/div/div[2]/h1/div/div/a")
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
