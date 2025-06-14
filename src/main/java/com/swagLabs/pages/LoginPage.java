package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.BrowserActions;
import com.swagLabs.utils.CustomSoftAssertion;
import com.swagLabs.utils.ElementActions;
import com.swagLabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.swagLabs.utils.PropertiesUtils.getPropertyValue;


public class LoginPage {

    private final GUIDriver driver;
    //locators
    private final By usernameLocator = By.id("user-name");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By messageErrorLocator = By.tagName("h3");
    // one of best practises is that your testcase start with navigate and ends it with assertion

    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        driver.browser().navigateToUrl( getPropertyValue("baseURL"));
    }

    // action >>
    @Step("Enter useName {0}")
    public LoginPage enterUserName(String username) {
        driver.element().type( usernameLocator, username);
        return this;
    }

    @Step("Enter password {0}")
    public LoginPage enterPassword(String password) {
        driver.element().type( passwordLocator, password);
        return this;
    }

    @Step("Click on login page")
    public LoginPage clickLogin() {
        driver.element().click( loginButtonLocator);
        return this;

    }

    @Step("Get error message")
    public String getErrorMessage() {
        return driver.element().getText( messageErrorLocator);
    }

    // validation
    @Step("Assert login page url")
    public LoginPage assertLoginPageUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browser().getCurrentBaseUrl(),
                getPropertyValue("homeURL"), "the url is not as expected");
        return this;
    }

    @Step("Assert login page title")
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browser().getPageTitle(), getPropertyValue("appTitle"), "the title is not as expected");
        return this;
    }

    @Step("Assert successful login softAssertion")
    public LoginPage assertSuccessfulLoginSoft() {
        assertLoginPageUrl().assertLoginPageTitle();
        return this;

    }

    @Step("Assert successful login")
        public HomePage assertSuccessfulLogin() {
        driver.validate().validatePageUrl( getPropertyValue("homeURL"));
        return new HomePage(driver);
    }

    @Step("Assert unsuccessful login")
    public LoginPage assertUnsuccessfulLogin() {
        driver.validate().validateEqual(getErrorMessage(), getPropertyValue("errorMSG"), "unsuccessful login  data");
        return this;
    }
}
