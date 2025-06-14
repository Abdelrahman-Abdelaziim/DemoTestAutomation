package com.swagLabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {
    private WebDriver driver;
    private BrowserActions browserActions;
    public Validation(WebDriver driver) {
        this.driver = driver;
        browserActions = new BrowserActions(driver);
    }



    @Step("Validate ture")
    public  void validateTure(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Step("Validate false")
    public  void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Step("Validate equal  : {expected}")
    public  void validateEqual(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Validate not equal : {expected}")
    public  void validateNotEqual(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    @Step("Validate Page url: {expected}")
    public  void validatePageUrl( String expected) {
        Assert.assertEquals(browserActions.getCurrentBaseUrl(), expected);
    }

    @Step("Validate page title : {expected} ")
    public  void validateTitle( String expected) {
        Assert.assertEquals(browserActions.getPageTitle(), expected);
    }
}
