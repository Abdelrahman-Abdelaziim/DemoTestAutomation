package com.swagLabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    // I want to create three types of wait > present . visible , clickable
private WebDriver driver;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }


    // Wait to be presented
    public  WebElement waitForElementPresent(By locator) {
        LogsUtil.info("Waiting for element to present : ", locator.toString());

        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> driver1.findElement(locator));

    }

    public  WebElement waitForElementVisible(By locator) {
        LogsUtil.info("Waiting for element to Visible : ", locator.toString());

        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                            WebElement element = waitForElementPresent( locator);
                            return element.isDisplayed() ? element : null;
                        }
                );
    }

    public  WebElement waitForElementClickable( By locator) {
        LogsUtil.info("Waiting for element to clickable : ", locator.toString());

        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> {
                    WebElement element = waitForElementVisible( locator);
                    return element.isEnabled() ? element : null;
                });
    }
}
