package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class InventoryPage extends BasePage {

    private final By inventoryTitle = By.className("title");
    private final By addItemButton = By.className("add_new_item");
    private final By itemNameInputField = By.className("new_item_name");
    private final By itemPriceInputField = By.className("new_item_price");
    private final By saveButton = By.className("save_button");
    private final By successToast = By.className("success_toast");
    private final By loadingSpinner = By.className("loading_spinner");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        waitUtils.waitForInvisibility(loadingSpinner);
        return isVisible(inventoryTitle);
    }

    public void addNewItem(String name, String price) {
        click(addItemButton);
        type(itemNameInputField, name);
        type(itemPriceInputField, price);
        click(saveButton);
    }

    public boolean isSuccessToastDisplayed() {
        return isVisible(successToast);
    }

    public String getSuccessToastText() {
        return getText(successToast);
    }
}