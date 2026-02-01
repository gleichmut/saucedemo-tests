package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Login Tests")
public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

    private LoginPage loginPage;

    @BeforeEach
    void initPages() {
        loginPage = new LoginPage(driver);
        loginPage.waitUntilOpened();
    }

    @Test
    @Story("Успешная авторизация")
    @Feature("Success login functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Пользователь может войти с валидными данными")
    void successfulLogin() {
        log.info("START test: successfulLogin");

        loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        InventoryPage inventory = new InventoryPage(driver);

        assertTrue(inventory.isOpened(), "Inventory page should be opened after login");

        log.info("Login successful. User is on Inventory page");
        log.info("END test: successfulLogin");
    }

    @Test
    @Story("Ошибка при неверном пароле")
    @Feature("Wrong password functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Отображается ошибка при вводе неверного пароля")
    void wrongPassword() {
        log.info("START test: wrongPassword");

        loginPage.enterUsername("standard_user")
                .enterPassword("wrong")
                .clickLogin();

        String error = loginPage.getErrorText();
        log.error("Login failed with error: {}", error);

        assertTrue(error.contains("do not match"), "Expected error message for wrong password");

        log.info("END test: wrongPassword");
    }

    @Test
    @Story("Заблокированный пользователь")
    @Feature("Locked user functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Заблокированный пользователь не может войти в систему")
    void lockedUser() {
        log.info("START test: lockedUser");

        loginPage.enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        String error = loginPage.getErrorText();
        log.error("Login failed with error: {}", error);

        assertTrue(error.contains("locked out"), "Expected error message for locked user");

        log.info("END test: lockedUser");
    }

    @Test
    @Story("Валидация пустых полей")
    @Feature("Empty fields functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Отображается ошибка при попытке логина с пустыми полями")
    void emptyFields() {
        log.info("START test: emptyFields");

        loginPage.clickLogin();

        String error = loginPage.getErrorText();
        log.error("Login failed with error: {}", error);

        assertTrue(error.contains("Username is required"), "Expected error message for empty username");

        log.info("END test: emptyFields");
    }

    @Test
    @Story("Performance glitch user")
    @Feature("Performance glitch functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Успешный вход performance пользователя, несмотря на задержки")
    void performanceUser() {
        log.info("START test: performanceUser");

        loginPage.enterUsername("performance_glitch_user")
                .enterPassword("secret_sauce")
                .clickLogin();
        InventoryPage inventory = new InventoryPage(driver);

        assertTrue(inventory.isOpened(), "Inventory page should be opened for performance user");

        log.info("Login successful (performance user). Inventory page opened");
        log.info("END test: performanceUser");
    }
}
