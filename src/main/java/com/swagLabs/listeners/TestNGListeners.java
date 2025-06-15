package com.swagLabs.listeners;

import com.swagLabs.drivers.GUIDriver;
import com.swagLabs.utils.*;
import org.testng.*;

import java.io.File;

import static com.swagLabs.utils.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {
    File allure_results = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/Logs");
    File screenshots = new File("test-outputs/screenshots");
    File allure_report = new File("test-outputs/allure-report");


    @Override
    public void onExecutionStart() {
        LogsUtil.info(" The test execution is started");
        loadProperties();
        FilesUtils.deleteFiles(allure_results);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(allure_report);
        FilesUtils.cleanDirectory(screenshots);
        LogsUtil.info("the directories are cleaned");
        LogsUtil.info("the directory for the logs is cleaned");
        LogsUtil.info("the directory for the screenshots is cleaned");
        LogsUtil.info("the directory for the allure results is cleaned");
        LogsUtil.info("the directory for the allure report is cleaned");
        FilesUtils.createDirectory(allure_results);
        FilesUtils.createDirectory(logs);
        FilesUtils.createDirectory(screenshots);
        FilesUtils.createDirectory(allure_report);

    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test Execution finished");
        AllureUtils.generateAllureReport();
       String reportName = AllureUtils.renameReport();
        AllureUtils.openReport(reportName);
        LogsUtil.info("the file is opened");
    }
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

       if(method.isTestMethod()){
            CustomSoftAssertion.customAssetAll(testResult);
           switch (testResult.getStatus()){
               case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"Passed- " +testResult.getName());
               case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"Failed- " +testResult.getName());
               case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"Skipped- " +testResult.getName());

           }
           AllureUtils.attacheLogsToAllureReport();
       }

    } @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("your testcase ' " , result.getName() ,"'has passed");

    }
    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("your testcase ' " , result.getName() ,"'has failed");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("your testcase ' " , result.getName() ,"'has skipped");
    }

}
