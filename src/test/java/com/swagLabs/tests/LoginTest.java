package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.listeners.TestNGListeners;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.HomePage;
import com.swagLabs.pages.InformationPage;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.swagLabs.utils.TimestampUtils.getTimestamp;

@Listeners(TestNGListeners.class)
public class LoginTest {
    private GUIDriver driver;
    JsonUtils testData;
    String FIRST_NAME;
    String LAST_NAME;

    @Test
    public void successfulLogin() {
        new LoginPage(driver).
                enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLogin().assertSuccessfulLogin();
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addProductToCart() {
        new HomePage(driver).
                addSpecificProductToCart(testData.getJsonData("products.item1.name")).
                assertProductAddedToCart(testData.getJsonData("products.item1.name"));
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void checkoutProductCart() {
        new HomePage(driver).clickOnCartIcon().assertProductDetails
                (testData.getJsonData("products.item1.name"
                ), testData.getJsonData("products.item1.prise"));
    }

    @Test(dependsOnMethods = "checkoutProductCart")
    public void fillInformationForm() {
        new CartPage(driver).clickCheckoutButton().fullfillInfo(FIRST_NAME, LAST_NAME, testData.getJsonData("information-form.postCode")).
                assertInformationPage(FIRST_NAME,LAST_NAME, testData.getJsonData("information-form.postCode"));
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckOut() {
        new InformationPage(driver).clickOnContinueButton().clickFinishButton().
                assertConformationMessage(testData.getJsonData("conformation-message"));
    }

    @BeforeClass
    public void setUp() {
        testData = new JsonUtils("test-data");
         FIRST_NAME = testData.getJsonData("information-form.firstName")+getTimestamp();
         LAST_NAME =  testData.getJsonData("information-form.lastName")+getTimestamp();
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterClass
    public void tearUp() {
        driver.browser().closeBrowser();
    }


}
