package base;

import driver.DriverFactory;
import listeners.TestListener;
import pages.InventoryPage;
import pages.LoginPage;
import pages.AdminHomePage;
import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AdminHomePage adminHomePage;
    protected InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.baseUrl());

        loginPage = new LoginPage(driver);
        adminHomePage = new AdminHomePage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    protected InventoryPage loginAsStoreAdmin() {
        return loginPage.loginAs(ConfigReader.Admin.username(), ConfigReader.Admin.password());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}