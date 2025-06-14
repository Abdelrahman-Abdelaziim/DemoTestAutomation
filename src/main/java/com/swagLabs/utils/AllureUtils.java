package com.swagLabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    static String REPORT_PATH = "test-outputs/allure-report";
    static String USER_HOME = System.getProperty("user.home");
    static String ALLURE_PATH = USER_HOME + File.separator + ".m2" + File.separator + "repository" + File.separator +
            "allure" + File.separator + "allure-2.30.0" + File.separator + "bin" + File.separator + "allure";

    private AllureUtils() {
    }

    public static void generateAllureReport() {
        //allure, generate, //path ,-o ,//path ,--single-file
        if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
        {
            String WIN = ALLURE_PATH + ".bat";
            TerminalUtils.executeCommand(WIN, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH, "clean", "--single-file");
            LogsUtil.info("Allure report generated successfully on Windows");
        } else {
            TerminalUtils.executeCommand(ALLURE_PATH, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH, "clean", "--single-file");
            LogsUtil.info("Allure report generated successfully on " + PropertiesUtils.getPropertyValue("os.name"));
        }


    }

    public static String renameReport() {
        File oldName = new File(REPORT_PATH + File.separator + "index.html");
        File newName = new File("Report_" + TimestampUtils.getTimestamp() + ".html");
        FilesUtils.renameFile(oldName,newName);
        return newName.getName();
    }

    public static void openReport(String fileName) {
        //allure serve //path
        String reportPath = REPORT_PATH + File.separator + fileName;
        File reportFile = new File(reportPath);
        if (!reportFile.exists()) {
            LogsUtil.error("Report file not found: " + reportFile.getPath());
            return;
        }
        if (PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")) {
            if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
            {
                TerminalUtils.executeCommand("cmd.exe", "/c", "start", reportPath);
                LogsUtil.info("the path is generated " + reportPath);
            } else //linux
            {
                TerminalUtils.executeCommand("open", reportPath);
            }
        }
    }

    public static void attacheLogsToAllureReport() {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);
            if (!logFile.exists()) {
                LogsUtil.warn("Log file is not exists in : " + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.warn("Failed to attach logs to Allure report " + e.getMessage());
        }
    }

    public static void attacheScreenshotToAllureReport(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            LogsUtil.error("Unable to attache the screenshot to the Allure report" + e.getMessage());
        }
    }
}

