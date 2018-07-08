package com.emirates.crux;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.emirates.superhelper.JsonReaderfile;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumHelper {

	public static int StepNumber;
	static public WebDriver driver = null;
	WebElement element;
	DateFormat defDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat defTimeFormat = new SimpleDateFormat("HH:mm:ss a");
	DateFormat defDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

	public void settDriver(WebDriver driver) {
		SeleniumHelper.driver = driver;
	}

	public WebDriver getDriver() {
		return SeleniumHelper.driver;
	}

	public String getAttribute_ButtonName(String locatorName, String attribute) {
		element = getElement(locatorName);
		return element.getAttribute(attribute);
	}


	/**
	 **************************************************************************************************************
	 * @Function Name: setTextBoxValue
	 * @Description: This function will enter the value in textBox.
	 *               
	 * @Param: locatorName
	 * @Param :textToInput
	 * @Return:
	 * @Date:
	 * @Author:
	 **************************************************************************************************************
	 */
	public void setTextBoxValue(String locatorName, String fieldName) {
		String textToInput = "";
		try {
			this.element = getElement(locatorName);

			textToInput = JsonReaderfile.getTestCaseData(fieldName);
			element.clear();
			element.sendKeys(textToInput);
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - " + StepNumber++ + "</b>",
					textToInput + " has been entered in text box : " + "<b>" + locatorName + "</b> " + "</b> "
							+ "   successfully.");
		} catch (ElementNotVisibleException e) {
			BaseTest.test.log(LogStatus.ERROR,
					"<b style=" + '"' + "color:red" + '"' + ">Step No - " + StepNumber++ + "</b>",
					textToInput + " has been entered in text box : " + "<b>" + locatorName + "</b> " + "</b> "
							+ " because " + "<b>" + locatorName + "</b> " + "</b> "
							+ " is not visible on Page. So Selenium is not able to perform click.");
		} catch (StaleElementReferenceException e) {
			// driver.executeScript("arguments[0].setAttribute('value', '" + longstring
			// +"')", inputField);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + textToInput + "')",
					element);
		}

	}
	/**
	 **************************************************************************************************************
	 * @Function Name: setTextBoxValue_Direct
	 * @Description: This function will enter the value in textbox without using
	 *               Json.
	 * @Param: locatorName
	 * @Param :textToInput
	 * @Return:
	 * @Date:
	 * @Author:
	 **************************************************************************************************************
	 */
	public void setTextBoxValue_Direct(String locatorName, String textToInput) {

		try {
			this.element = getElement(locatorName);

			element.clear();
			element.sendKeys(textToInput);
			BaseTest.test.log(LogStatus.INFO, "<b>Step No - " + StepNumber++ + "</b>",
					textToInput + " has been entered in text box : " + "<b>" + locatorName + "</b> " + "</b> "
							+ "   successfully.");
		} catch (ElementNotVisibleException e) {
			BaseTest.test.log(LogStatus.ERROR,
					"<b style=" + '"' + "color:red" + '"' + ">Step No - " + StepNumber++ + "</b>",
					textToInput + " has been entered in text box : " + "<b>" + locatorName + "</b> " + "</b> "
							+ " because " + "<b>" + locatorName + "</b> " + "</b> "
							+ " is not visible on Page. So Selenium is not able to perform click.");
		} catch (StaleElementReferenceException ne) {
			BaseTest.test.log(LogStatus.ERROR,
					"<b style=" + '"' + "color:red" + '"' + ">Step No - " + StepNumber++ + "</b>",
					"<b>" + locatorName + "</b> " + "</b> " + " has not been clicked because " + "<b>" + locatorName
							+ "</b> " + "</b> "
							+ " Web page's HTM has been refreshed, changed or updated since it was looked up");

		} catch (InvalidElementStateException e) {
			// driver.executeScript("arguments[0].setAttribute('value', '" + longstring
			// +"')", inputField);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + textToInput + "')",
					element);
		}

	}
	public void validateTableDataAsc(String locatorName, String ColumnName) {
		BaseTest.test.log(LogStatus.INFO, "",
				"<b style= " + '"' + "font-size: 15px;color:blue" + '"' + ">" + BaseTest.testStep + "</b>");
		this.element = getElement(locatorName);
		List<String> BeforeColumnList_Sorted = new ArrayList<String>();
		List<WebElement> BeforeColumnList = getElementsList(locatorName);
		for (WebElement element : BeforeColumnList) {
			BeforeColumnList_Sorted.add(element.getText());
		}
		Collections.sort(BeforeColumnList_Sorted);

		click(locatorName);
		holdOn(1000);

		List<String> afterColumnList = new ArrayList<String>();
		List<WebElement> AfterColumnList = getElementsList(locatorName);
		for (WebElement element : AfterColumnList) {
			afterColumnList.add(element.getText());
		}

		if (afterColumnList.equals(BeforeColumnList_Sorted) == true) {
			BaseTest.test.log(LogStatus.PASS,
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>Step No - " + "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:green" + '"' + ">" + "<b>" + locatorName + "</b> "
							+ "</b> " + "</b>" + " Text Validation Passed" + ".  Table Data Sorted Successfully.");
		} else {
			BaseTest.test.log(LogStatus.FAIL,
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>Step No - " + "</b>" + "</b>",
					"<b style= " + '"' + "font-size: 15px;color:red" + '"' + ">" + "<b>" + locatorName + "</b> "
							+ "</b> " + "</b>" + " Text Validation Failed " + ".   Table Data are not Sorted.");
			Assert.fail();
		}
	}

	public void click(String locatorName) {

		try {
			this.element = getElement(locatorName);
			element.click();

			BaseTest.test.log(LogStatus.INFO, "<b>Step No - " + StepNumber++ + "</b>",
					"<b>" + locatorName + "</b> " + "</b> " + " has been clicked successfully");
		} catch (ElementNotVisibleException e) {
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - " + StepNumber++ + "</b>",
					"<b>" + locatorName + "</b> " + " has not been clicked because " + "<b>" + locatorName + "</b> "
							+ "</b> " + " is not visible on Page. So Selenium is not able to perform click.");

		} catch (StaleElementReferenceException e) {
			try {
				this.element = getElement(locatorName);
				element.click();

			} catch (StaleElementReferenceException ne) {
				BaseTest.test.log(LogStatus.ERROR, "<b>Step No - " + StepNumber++ + "</b>",
						"<b>" + locatorName + "</b> " + "</b> " + " has not been clicked because " + "<b>" + locatorName
								+ "</b> " + "</b> "
								+ " Web page's HTM has been refreshed, changed or updated since it was looked up");

			}
		} catch (WebDriverException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);

		}

		holdOn(1000);
	}
	public By getLocatorBy(String locatorName, String locatorType, String locatorValue) {
		By locatorBy = null;
		try {
			switch (locatorType.trim().toLowerCase()) {
			case "xpath":
				locatorBy = By.xpath(locatorValue);
				break;
			case "css":
				locatorBy = By.cssSelector(locatorValue);
				break;
			case "id":
				locatorBy = By.id(locatorValue);
				break;
			case "name":
				locatorBy = By.name(locatorValue);
				break;
			case "linkText":
				locatorBy = By.linkText(locatorValue);
				break;
			case "class":
				locatorBy = By.className(locatorValue);
				break;
			case "tag":
				locatorBy = By.tagName(locatorValue);
				break;
			case "plt":
				locatorBy = By.tagName(locatorValue);
				break;
			}
		} catch (Exception e) {

		}

		return locatorBy;
	}


	public WebElement getElement(String locatorName) {

		try {
			String locatorType = JsonReaderfile.getLocator(locatorName)[0];
			String locatorValue = JsonReaderfile.getLocator(locatorName)[1];
			By locator = getLocatorBy(locatorName, locatorType, locatorValue);
			int count = 1;
			System.out.println(
					"Waiting upto " + 10000 + "ms for element with locator: \"" + locator + "\" to appear on page.");
			while (driver.findElements(locator).size() == 0 && count <= 5) {
				Thread.sleep(1000);
				System.out.println("Waiting " + 1 + "000 ms for count " + count);
				count++;
			}
			return (driver.findElement(locator));
		} catch (NoSuchElementException e) {
			BaseTest.test.log(LogStatus.ERROR,
					"<b style=" + '"' + "color:red" + '"' + ">Step No - " + StepNumber++ + "</b>" + "</b>",
					"<b>" + locatorName + "</b> " + "</b> " + "  is not found on Page Source. Test Failed");
			e.printStackTrace();
		} catch (NullPointerException e) {
			BaseTest.test.log(LogStatus.ERROR,
					"<b style=" + '"' + "color:red" + '"' + ">Step No - " + StepNumber++ + "</b>" + "</b>",
					"NullPointerException for " + "<b>" + locatorName + "</b> " + "</b> "
							+ "  Please Match Script's locator Name with OR Locator Name. Test Failed");
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			BaseTest.test.log(LogStatus.ERROR, "<b>Step No - " + StepNumber++ + "</b>",
					"<b>" + locatorName + "</b> " + "  is not attached to the page document. Test Failed");
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<WebElement> getElementsList(String locatorName) {
		String locatorType = JsonReaderfile.getLocator(locatorName)[0];
		String locatorValue = JsonReaderfile.getLocator(locatorName)[1];
		return driver.findElements(getLocatorBy(locatorName, locatorType, locatorValue));
	}
	public void holdOn(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 **************************************************************************************************************
	 * @Function Name: getFuture_dateFormatDDMMYYYY
	 * @Description: This function Will return a previous date based on the
	 *               parameter in mm/dd/yyyy format
	 * @Param: Number of days
	 * @Param : N/A
	 * @Return: Returns a String
	 * @Date:
	 * @Author:
	 **************************************************************************************************************
	 */

	public static String getFuture_dateFormatDDMMYYYY(int noOfDays) {

		// String date = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = new Date();
		String todate = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +noOfDays);
		Date todate1 = cal.getTime();
		String fromdate = dateFormat.format(todate1);
		return fromdate;
	}
	
	// For taking ScreenShot
		public void takeScreenShot(String folderPath, String methodName) {
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File file = new File(folderPath + "//snapshots");
				if (file.exists() == false) {

					file.mkdir();
				}
				folderPath = folderPath + "//snapshots";
				methodName = getTimeStamp() + methodName;
				FileUtils.copyFile(scrFile, new File(folderPath + "\\" + methodName + ".png"));
				System.out.println("***Placed screen shot ***");
				BaseTest.test.log(LogStatus.FAIL,
						"Screencast below: " + BaseTest.test.addScreenCapture("snapshots" + "\\" + methodName + ".png"));
			} catch (IOException e) {
				BaseTest.test.log(LogStatus.ERROR, e.getMessage());
				e.printStackTrace();
			} catch (NoSuchWindowException e) {
				BaseTest.test.log(LogStatus.ERROR, e.getMessage());
				e.printStackTrace();
			} catch (WebDriverException e) {
				BaseTest.test.log(LogStatus.ERROR, e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				BaseTest.test.log(LogStatus.ERROR, e.getMessage());
				e.printStackTrace();
			}
		}
		
		public String getCurrentTime() {
			return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm a"));
		}
		public static String getTimeStamp() {
			SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yy-HH_mm_ss");
			return sdf.format(new Date());
		}
}
