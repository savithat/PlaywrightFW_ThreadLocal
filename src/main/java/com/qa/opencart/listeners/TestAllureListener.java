package com.qa.opencart.listeners;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.PlaywrightFactory;

import io.qameta.allure.Attachment;


public class TestAllureListener implements ITestListener {
	
	 @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
	        attachScreenshot();
	    }

	    @Attachment(value = "Failure Screenshot", type = "image/png")
	    public byte[] attachScreenshot() {
	   // 	return PlaywrightFactory.attachScreenshot();
	    	PlaywrightFactory playwrightFactory = new PlaywrightFactory();
	    	return playwrightFactory.attachScreenshot();
	    }
}
