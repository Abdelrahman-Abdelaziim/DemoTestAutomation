package com.swagLabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;

public class ElementActions {
    private WebDriver driver;
    private Waits waits;
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }


    @Step("Sending data : {data} to the element {locator}")
    public  void type( By locator, String data) {
        waits.waitForElementVisible( locator);
       scrollToElement(locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Data entered : ", data, " in the field : ", locator.toString());
    }
    @Step("click on element {locator}")
    public void click( By locator) {
       waits.waitForElementClickable( locator);
        scrollToElement( locator);
        findElement( locator).click();
        LogsUtil.info("clicked in element : ", locator.toString());
    }
    @Step("Getting text from the element {locator}")
    public  String getText( By locator) {
        waits.waitForElementVisible( locator);
       scrollToElement( locator);
        LogsUtil.info("Getting text from the element : ",
                locator.toString(), "text is : ", findElement(locator).getText());
        return findElement( locator).getText();
    }

    //find element
    public  WebElement findElement( By locator) {
        LogsUtil.info(" finding element : ", locator.toString());
        return driver.findElement(locator);
    }
     public String getTextFromInput(  By locator){
        waits.waitForElementVisible(locator);
       scrollToElement(locator);
        LogsUtil.info("Getting text from the input field  :" + locator.toString()+ "text "
                + findElement( locator).getDomAttribute("value"));
        return findElement( locator).getDomAttribute("value");
     }
    @Step("Scrolling to element: {0}")

    public void scrollToElement( By locator){
        LogsUtil.info("Scrolling to the element",locator.toString());
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        findElement( locator));
    }
}
