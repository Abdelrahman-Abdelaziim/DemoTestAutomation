package com.swagLabs.pages;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    private GUIDriver driver;
    private By finsihLocator = By.linkText("FINISH");
    public OverviewPage(GUIDriver driver) {
        this.driver = driver;
    }
    @Step(" Click on finish button")
    public ConformationPage clickFinishButton(){
        driver.element().click(finsihLocator);
        return new ConformationPage(driver);
    }
}
