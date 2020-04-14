package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id= "login-form-username") private WebElement usernameField;
    @FindBy(id= "login-form-password") private WebElement passwordField;
    @FindBy(id= "login") private WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(),'Sorry, your username and password are incorrect')]") private WebElement errorMessage;
//    @FindBy(xpath = "//a[@id='header-details-user-fullname']//img") private WebElement userProfilePic;

    public MainPage(WebDriver driver, WebDriverWait wait) {
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

//    public WebElement getProfilePic() {
//        return wait.until(ExpectedConditions.visibilityOf(userProfilePic));
//    }
}
