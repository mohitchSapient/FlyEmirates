
package com.emirates.crux;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	protected static ExtentReports extent;
	String startClassTime, endClassTime, duration;
	public static String testcaseName;
	static SeleniumHelper baseobj = new SeleniumHelper();
	static public ExtentTest test;
	public static String testCaseName;
	public static String classResult = "Pass";
	public int loop = 0;
	public static String TCID;

	static public ExtentTest parentTest;

	public static int iterationCounter;
	public static String testStep = "";
	static String testMethodName;

	@BeforeSuite
	public void beforeSuite() throws IOException {

		String ts = SeleniumHelper.getTimeStamp();
		String resultFoler = "Automation_Reports";
		String resultFilePath = System.getProperty("user.home") + "\\" + resultFoler;
		resultFoler = createFolder(resultFilePath);
		folderPath = resultFoler + "\\" + ts + "_ExecutionReport";
		folderPath = createFolder(folderPath);
		String extentReportPath = folderPath + "\\" + "Automation_Report.html";
		extent = new ExtentReports(extentReportPath, true);
		extent.loadConfig(new File("extent-config.xml"));
		SeleniumHelper.StepNumber= 1;

		System.out.println("------------------@BeforeSuite fired------------------------");

	}

	public static String folderPath;

	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		System.out.println("------------------ @BeforeMethod fired------------------------");
		startClassTime = baseobj.getCurrentTime();

		Test testMethod = method.getAnnotation(Test.class);
		String MethodName = method.getName();
		test.setDescription(testMethod.description());
		test.assignCategory(testMethod.groups());

	}

	public String createFolder(String folderPath) {
		File file = new File(folderPath);
		if (file.exists() == false) {
			file.mkdir();
		}
		return folderPath;
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("------------------ @BeforeClass fired------------------------");
		String testCaseName = this.getClass().getSimpleName();
		System.out.println("---- Starting TC - " + testCaseName);
		startClassTime = baseobj.getCurrentTime();
		TCID = "TC_" + testCaseName.split("_")[3];
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Version", "3.0");
		sysInfo.put("Environment", "NST-Internal");
		extent.addSystemInfo(sysInfo);
		test = extent.startTest(testCaseName);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("------------------ @AfterClass fired------------------------");

		if (parentTest != null) {
			extent.endTest(parentTest);
			parentTest = null;
		}
		extent.flush();
	}

	
	/*@AfterTest
	public void afterTest() {
		System.out.println("------------------ @AfterTest fired------------------------");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {
		System.out.println("------------------ @AfterMethod fired------------------------");
		if (parentTest != null) {
			test.getTest().setName(
					result.getName() + "<br/><h5>Dataset: " + Arrays.toString(result.getParameters()) + "</h5>");
		}
		if (!result.isSuccess()) {
			System.out.println("ITestResult");
			String methodName = result.getName().toString().trim();
			baseobj.takeScreenShot(folderPath, methodName);
		} else {
			test.log(LogStatus.PASS, result.getMethod().getMethodName());
		}
		extent.endTest(test);
		extent.flush();
		BaseDriver.StopDriver();
		baseobj.holdOn(3000);
	}*/

	/*@AfterSuite
	protected void afterSuite() {
		BaseDriver.StopDriver();
		System.out.println("------------------	@AfterSuite fired -----------------------");
		try {
			FileUtils.copyFile(new File(folderPath + "\\" + "Automation_Report.html"),
					new File(System.getProperty("user.dir") + "\\" + "Automation_Report.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// mailReport.sendEmail();
	}*/

}
