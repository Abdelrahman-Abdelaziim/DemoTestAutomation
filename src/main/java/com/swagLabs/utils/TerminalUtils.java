package com.swagLabs.utils;
public class TerminalUtils {
    public static void executeCommand(String ... command){
        try{
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            LogsUtil.info("command is executed successfully"+ String.join(" ",command));
        }catch (Exception e){
            LogsUtil.error("your executed command is failed"+ e.getMessage());
        }
    }
}
