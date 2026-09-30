package tests;


import base.BaseTest;
import dataProviders.InventoryDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTests extends BaseTest {

    @Test(groups = {"smoke", "regression"},
            description = "Store Admin adds a single product and sees a success toast")
    public void storeAdminCanAddProduct() {
        loginAsStoreAdmin();
        adminHomePage.clickInventoryButton();
        inventoryPage.addNewItem("Wireless Mouse", "25.00");

        Assert.assertTrue(inventoryPage.isSuccessToastDisplayed(),
                "Expected a success toast after saving a valid product");
        Assert.assertTrue(inventoryPage.getSuccessToastText().toLowerCase().contains("successfully"),
                "Toast text should confirm the save action");
    }

    @Test(groups = {"regression"},
            dataProvider = "inventoryData",
            dataProviderClass = InventoryDataProvider.class,
            description = "Data-driven add-product flow covering valid and invalid inputs from CSV")
    public void addProductFromCsv(String productName, String price, String expectedResult) {
        loginAsStoreAdmin();
        adminHomePage.clickInventoryButton();
        inventoryPage.addNewItem(productName, price);

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(inventoryPage.isSuccessToastDisplayed(),
                    "Expected success toast for input: " + productName + " / " + price);
        } else if ("VALIDATION_ERROR".equalsIgnoreCase(expectedResult)) {
            Assert.assertFalse(inventoryPage.isSuccessToastDisplayed(),
                    "Did not expect a success toast for invalid input: " + productName + " / " + price);
        } else {
            Assert.fail("Unknown expectedResult in CSV: " + expectedResult);
        }
    }
}
