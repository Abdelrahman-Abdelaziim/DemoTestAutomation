package com.swagLabs.drivers;

import com.swagLabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends  AbstractDriver implements WebDriverOptionsAbstract<FirefoxOptions>{
    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized"); // Starts Chrome in maximized mode
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.addArguments("--disable-infobars");
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            firefoxOptions.addArguments("--headless");}
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }
    @Override
    public WebDriver startDriver() {
        return new FirefoxDriver(getOptions());
    }
}
