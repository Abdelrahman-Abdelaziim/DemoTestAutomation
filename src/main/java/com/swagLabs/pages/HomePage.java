package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    private GUIDriver driver;
    private final By cartIconLocator = By.cssSelector("path[fill='currentColor']");

    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }

/*    @Step("Navigate  to the home page")
    public HomePage navigateToHomePage() {
        BrowserActions.navigateToUrl(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }*/

    @Step(" Add specific product to the cart")
    public HomePage addSpecificProductToCart(String productName) {
        LogsUtil.info("you are here getting :" + productName);
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        driver.element().click( addToCartButton);
        return this;
    }

    @Step(" Click on the cart icon")
    public CartPage clickOnCartIcon() {
        driver.element().click( cartIconLocator);
        return new CartPage(driver);
    }

    //validation
    @Step("Assert that the product were added to the cart")
    public HomePage assertProductAddedToCart(String productName) {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        String actualValue = driver.element().getText( addToCartButton);
        LogsUtil.info("Actual value is " + actualValue);
        driver.validate().validateEqual(actualValue, "REMOVE", "the product is not added");
        LogsUtil.info(productName + " added to cart successfully");
        return this;
    }

}
