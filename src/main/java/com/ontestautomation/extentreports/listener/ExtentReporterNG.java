package com.ontestautomation.extentreports.listener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.HTMLReporter;

import static com.relevantcodes.extentreports.LogStatus.*;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;

 
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "ExtentReportsTestNG.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), PASS);
                buildTestNodes(context.getFailedTests(), FAIL);
                buildTestNodes(context.getSkippedTests(), SKIP);
            }
        }
 
        extent.flush();
        extent.close();
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
                test.log(LogStatus.INFO,"Test Started");

                test.getTest().getStartedTime();
                test.getTest().getEndedTime();

//                test.getTest().startedTime = getTime(result.getStartMillis());
//                test.getTest().endedTime = getTime(result.getEndMillis());
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
                test.log(status, message);
                if (status.toString().equalsIgnoreCase("FAIL")){}
                test.log(LogStatus.PASS,"Adding ScreenShot",test.addScreenCapture("C:\\Users\\Ranjani\\Desktop\\Ranjani\\Photos\\DSC_0055.JPG"));
                extent.endTest(test);
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}