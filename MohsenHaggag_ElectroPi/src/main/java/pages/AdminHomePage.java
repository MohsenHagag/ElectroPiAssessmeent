package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;
import utils.WaitUtils;

public class AdminHomePage extends BasePage {

    private final By inventoryButton = By.id("inventory-btn");

    public AdminHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickInventoryButton() {
        waitUtils.waitForClickable(inventoryButton);
        click(inventoryButton);
    }
}