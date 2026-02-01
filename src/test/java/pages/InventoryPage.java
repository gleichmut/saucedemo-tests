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

public class InventoryPage {

    private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

    private final WebDriverWait wait;
    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Проверка открытия страницы Inventory")
    public boolean isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
            return true;
        } catch (TimeoutException e) {
            log.error("Inventory page not opened");
            return false;
        }
    }
}
