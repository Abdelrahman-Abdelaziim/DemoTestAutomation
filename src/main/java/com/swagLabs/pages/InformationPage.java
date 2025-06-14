package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.CustomSoftAssertion;
import com.swagLabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
    private GUIDriver driver;
    private By firstNameLocator = By.id("first-name");
    private By lastNameLocator = By.id("last-name");
    private By postalCodeLocator = By.id("postal-code");
    private By continueButton = By.cssSelector("input[type='submit']");

    public InformationPage(GUIDriver driver) {
        this.driver = driver;
    }

    @Step("Fill information form  : First name and last name and postcode")
    public InformationPage fullfillInfo(String firstName, String lastName, String postCode){
        driver.element().type( firstNameLocator,firstName);
        driver.element().type(lastNameLocator,lastName);
        driver.element().type(postalCodeLocator,postCode);
        return this;
    }

    @Step("click continue Button")
    public OverviewPage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new OverviewPage(driver);
    }

    public InformationPage  assertInformationPage(String firstName, String lastName, String postCode){
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(firstNameLocator),firstName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(lastNameLocator),lastName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(postalCodeLocator),postCode);
return this;
    }
}
