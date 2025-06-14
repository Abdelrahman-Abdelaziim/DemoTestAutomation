package com.swagLabs.utils;

import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();
    public static void customAssetAll(ITestResult result){
        try{

            softAssertion.assertAll("custom soft assertion");
        }catch (AssertionError e){
            softAssertion.assertAll(" custom soft assertion fail");
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }finally {
            reInitializeSofAssertion();
        }
    }

    private static  void reInitializeSofAssertion(){
        softAssertion = new CustomSoftAssertion();
    }
}
