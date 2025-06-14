package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.ElementActions;
import com.swagLabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConformationPage {
    private GUIDriver driver;
    private By conformationMessageLocator = By.cssSelector(".complete-header");
    public ConformationPage(GUIDriver driver) {
        this.driver = driver;
    }
    @Step("GET CONFORMATION MESSAGE")
    private String getConformationMessage(){
        return driver.element().getText(conformationMessageLocator);
    }

    @Step("Assert conformation message")
    public void assertConformationMessage(String expectedMessage){
        String actualMessage= getConformationMessage();
        driver.validate().validateEqual(actualMessage,expectedMessage,"conformation message is wrong");

    }

}
