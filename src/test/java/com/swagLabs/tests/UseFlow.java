package com.swagLabs.tests;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.listeners.TestNGListeners;
import com.swagLabs.pages.LoginPage;
import com.swagLabs.utils.BrowserActions;
import com.swagLabs.utils.JsonUtils;
import com.swagLabs.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)

public class UseFlow {
    private GUIDriver driver;
    JsonUtils testData;

    @Test
    public void endToEnd() {
        new LoginPage(driver).enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLogin().assertSuccessfulLogin().addSpecificProductToCart(testData.getJsonData("products.item1.name")).
                assertProductAddedToCart(testData.getJsonData("products.item1.name")).clickOnCartIcon().assertProductDetails
                        (testData.getJsonData("products.item1.name"
                        ), testData.getJsonData("products.item1.prise")).clickCheckoutButton().
                fullfillInfo(testData.getJsonData("information-form.firstName"),
                        testData.getJsonData("information-form.lastName"),
                        testData.getJsonData("information-form.postCode")).
                assertInformationPage(testData.getJsonData("information-form.firstName"),
                        testData.getJsonData("information-form.lastName"),
                        testData.getJsonData("information-form.postCode")).clickOnContinueButton().clickFinishButton().
                assertConformationMessage(testData.getJsonData("conformation-message"));
    }


    @BeforeClass
    public void setUp() {
        testData = new JsonUtils("test-data");
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterClass
    public void tearUp() {driver.browser().closeBrowser();

    }
}
