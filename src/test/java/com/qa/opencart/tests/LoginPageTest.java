package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

public class LoginPageTest extends BaseTest{
	
	@Test (priority = 1)
	public void loginPageTitleTest() {
		loginPage = homePage.navigateToLoginPage();
		String loginPageTitleString = loginPage.getTitle();
		Assert.assertEquals(loginPageTitleString, AppConstants.LOGIN_PAGE_TITLE, "loginPageTitleString is not equals to expected");
	}
	
	@Test (priority = 2)
	public void forgotPasswordlinkExistTest() {	
		Assert.assertTrue(loginPage.isforgotPasswordLinkExist());
	}
	
	@Test (priority = 3)
	public AccountPage loginPageTest() {
		return loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

}
