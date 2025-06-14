package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.CustomSoftAssertion;
import com.swagLabs.utils.ElementActions;
import com.swagLabs.utils.LogsUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private GUIDriver driver;
    private final By productNameLocator = By.cssSelector(".inventory_item_name");
    private final By productPriceLocator = By.cssSelector(".inventory_item_price");
    private final By checkoutButtonLocator = By.linkText("CHECKOUT");

    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }

    @Step("Get product Name")
    private String getProductName() {
        return driver.element().getText( productNameLocator);
    }

    @Step("Get product Price")
    private String getProductPrice() {
        return driver.element().getText( productPriceLocator);
    }

    @Step("Click on the checkout button")
    public InformationPage clickCheckoutButton() {
        driver.element().click( checkoutButtonLocator);
        return new InformationPage(driver);
    }

    // validation
    @Step("Assert product details")
    public CartPage assertProductDetails(String productName, String productPrice) {
        String actualPrice = getProductPrice();
        String actualProductName = getProductName();
        CustomSoftAssertion.softAssertion.assertEquals(actualPrice, productPrice, "Product price is incorrect");
        LogsUtil.info("the price is " + productPrice);
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName, productName, "Product name is incorrect");
        LogsUtil.info("the productName  is " + productName);
        return this;
    }
}
