package com.swagLabs.drivers;
import com.swagLabs.utils.BrowserActions;
import com.swagLabs.utils.ElementActions;
import com.swagLabs.utils.LogsUtil;
import com.swagLabs.utils.Validation;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver(String browserName) {
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);

    }
    private  AbstractDriver getDriver(String browserName) {
        return switch (browserName) {
            case "chrome" -> new ChromeFactory();
            case "edge" -> new EdgeFactory();
            case "firefox" -> new FirefoxFactory();
            default -> throw new IllegalArgumentException();
        };
    }
    public static WebDriver  getInstance(){
        return driverThreadLocal.get();
    }

   private void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }
    public WebDriver get(){
        if(driverThreadLocal.get() == null){
            LogsUtil.error(" Driver is null ");
            fail("Driver is null");
            return null;
        }
        return driverThreadLocal.get();
    }
    public ElementActions element (){
        return new ElementActions(get());
    }
    public BrowserActions browser(){
        return new BrowserActions(get());
    }
    public Validation validate(){
        return new Validation(get());
    }

}
