package org.sample.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.apache.log4j.Logger;
import org.sample.automation.browserutils.BrowserFactory;
import org.sample.facebook.pages.FacebookAppLoginPage;
import org.sample.facebook.pages.FacebookAppUserHomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FacebookAppPostVerifyTest {
	private static final Logger LOGGER = Logger.getLogger(FacebookAppPostVerifyTest.class);

	private AppiumDriver<MobileElement> driver = null;
	
	public FacebookAppLoginPage facebookAppLoginPage;
	public FacebookAppUserHomePage  facebookAppUserHomePage;
	
	/**
	 * Initialize the browser driver from BrowserFactory;
	 * Creates required page objects to be used inside test script.
	 * 
	 */
	@BeforeClass
	public void initBrowserDriver() {
		driver =  BrowserFactory.createAndroidDriver();
		facebookAppLoginPage = new FacebookAppLoginPage(driver);
		facebookAppUserHomePage = new FacebookAppUserHomePage(driver);
	}
	
	/**
	 * Retrieve login credentials and post message from Test Data source for Facebook app login;
	 * hard-coded in our test scripts(Just for sample).
	 *
	 * @return the login credentials and message to post
	 * 
	 * Note:- Test data source can be xls, csv, json, YAML, databases etc...
	 */
	@DataProvider
	public String[][] getLoginCredentials() {
		String[][] loginCredentials = {{"taskscircles.life@gmail.com", "Seniorqa1", "First Test Post"}};
		return loginCredentials;
	}
	
	/**
	 * Verify message posted in Facebook wall using App
	 * 
	 * 1. Login to Facebook App 
	 * 2. verify post in wall
	 *
	 * @param email - email to login
	 * @param password - password to login
	 * @param postMessage - message to verify
	 * @throws Exception the exception
	 */
	@Test(dataProvider="getLoginCredentials")
	public void verifyPostInFacebookApp(String email, String password, String postMessage) throws Exception {
		facebookAppLoginPage.loginToFacebook(email, password);
		
		Assert.assertTrue(facebookAppUserHomePage.isUserLoggedIn(), "User not logged in, write post section not displayed.");
		
		Assert.assertTrue(facebookAppUserHomePage.verifyPost(postMessage), "Post is not displayed");
	}
	
	/**
	 * Close browser driver.
	 */
	@AfterClass
	public void closeBrowserDriver() {
		try {
			driver.quit();
		} catch(Exception exception) {
			LOGGER.error("Exception while closing Android driver after test execution -"+exception.getMessage());
		}
	}
}
