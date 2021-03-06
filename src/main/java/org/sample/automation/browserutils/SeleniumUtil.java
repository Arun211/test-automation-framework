package org.sample.automation.browserutils;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author arunkumar
 * 
 * The Class SeleniumUtil - contains basic reusable browser interaction components.
 * 
 */
public class SeleniumUtil {

	private static final Logger LOGGER = Logger.getLogger(SeleniumUtil.class);
	
	public WebDriver driver;
	
	/**
	 * Instantiates a new selenium util object.
	 *
	 * @param driver the driver
	 */
	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Wait for element to be displayed.
	 *
	 * @param locator the locator
	 * @param timeToWait the time to wait
	 * @return true, if successful
	 */
	public boolean waitForElementToBeDisplayed(Locator locator, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
			return true;
		} catch(Exception exception) {
			LOGGER.error("waitForElementToBeDisplayed - "+exception.getMessage());
			return false;
		}
	}
	
	/**
	 * Wait for element to be clickable.
	 *
	 * @param locator the locator
	 * @param timeToWait the time to wait
	 * @return true, if successful
	 */
	public boolean waitForElementToBeClickable(Locator locator, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			return true;
		} catch(Exception exception) {
			LOGGER.error("waitForElementToBeClickable - "+exception.getMessage());
			return false;
		}
	}
	
	/**
	 * Wait for element to disappear.
	 *
	 * @param locator the locator
	 * @param timeToWait the time to wait
	 * @return true, if successful
	 */
	public boolean waitForElementToDisappear(Locator locator, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));
			return true;
		} catch(Exception exception) {
			LOGGER.error("waitForElementToDisappear - "+exception.getMessage());
			return false;
		}
	}
	
	/**
	 * Enter text into text field.
	 *
	 * @param locator the locator
	 * @param value the value
	 * @throws NoSuchElementException the no such element exception
	 */
	public void enterText(Locator locator, String value) throws NoSuchElementException {
		try {
		driver.findElement(locator.getBy()).sendKeys(value);
		} catch(NoSuchElementException noSuchElementException) {
			LOGGER.error("enterText - "+noSuchElementException.getMessage());
		}
	}
	
	/**
	 * Click on the element.
	 *
	 * @param locator the locator
	 * @throws Exception the exception
	 */
	public void click(Locator locator) throws Exception {
		driver.findElement(locator.getBy()).click();
	}
}
