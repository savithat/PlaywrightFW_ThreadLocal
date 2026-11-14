package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest{
	

	@Test
	public void isLogoutExistTest() {
		loginPage = homePage.navigateToLoginPage();	
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Boolean logOutLinkExist  = accountPage.isLogOutExist();
		System.out.println(logOutLinkExist);
		Assert.assertTrue(logOutLinkExist);
	}

}
