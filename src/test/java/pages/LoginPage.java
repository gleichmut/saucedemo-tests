package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private final WebDriver driver;
    private final By loginContainer = By.id("login_button_container"); // контейнер формы
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод логина: {value}")
    public LoginPage enterUsername(String value) {
        log.info("Enter username: {}", value);
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(value);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String value) {
        log.info("Enter password");
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(value);
        return this;
    }

    @Step("Нажатие кнопки Login")
    public void clickLogin() {
        log.info("Click Login button");
        driver.findElement(loginButton).click();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        try {
            String error = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                    .getText();
            log.error("Login error message: {}", error);
            return error;
        } catch (TimeoutException e) {
            log.error("Error message not displayed");
            return "";
        }
    }

    @Step("Ожидание открытия страницы логина")
    public void waitUntilOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginContainer));
    }
}
