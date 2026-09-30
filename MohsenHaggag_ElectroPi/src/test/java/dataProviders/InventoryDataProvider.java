package dataProviders;

import utils.CsvReader;
import org.testng.annotations.DataProvider;


public class InventoryDataProvider {

    private static final String CSV_PATH = "src/test/resources/testdata/inventory_data.csv";

    private InventoryDataProvider() {
    }

    @DataProvider(name = "inventoryData")
    public static Object[][] getInventoryData() {
        // Each row: { productName, price, expectedResult }
        return CsvReader.toDataProviderArray(CSV_PATH);
    }
}
