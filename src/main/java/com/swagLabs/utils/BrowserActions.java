package com.swagLabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private  WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Navigate to URL :  {url}")
    public   void navigateToUrl ( String url){
        driver.get(url);
        LogsUtil.info("Navigate to URL : " , url);

    }

    // get url
    @Step("the current URL ")
    public  String getCurrentBaseUrl(){
        LogsUtil.info("the current url is " , driver.getCurrentUrl());

        return driver.getCurrentUrl();
    }
    @Step("Getting page title ")
    public  String getPageTitle(){
        LogsUtil.info("the page title is : ", driver.getTitle());
        return driver.getTitle();
    }
    @Step("Refreshing the page")
    public  void refreshPage(){
        LogsUtil.info("Refreshing the page : ");

        driver.navigate().refresh();
    }
    @Step("Closing the browser")
    public  void  closeBrowser (){
        LogsUtil.info("closing browser");
        driver.quit();

    }
}
