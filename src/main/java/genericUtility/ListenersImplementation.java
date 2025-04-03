package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test; 
	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Started");
		 test = report.createTest(methodname);
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Passed");
		test.log(Status.PASS, methodname+"----Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Failed");
		test.log(Status.FAIL, methodname+"----Failed");
		test.log(Status.INFO, result.getThrowable());
		
		webDriverUtility wutil = new webDriverUtility();
		JavaUtility jutil = new JavaUtility();
		
		String screenshotname = methodname+"-"+jutil.toGetSystemDataAndTime();
		try {
			String path = wutil.toTakeScreenShot(BaseClass.sDriver, screenshotname);
			test.addScreenCaptureFromPath(path);
		}catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---skipped");
		test.log(Status.SKIP, methodname+"----skipped");
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started--");
		
		//ExtentsReports
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReports/Reports-"+ new JavaUtility().toGetSystemDataAndTime()+".html");
		htmlReport.config().setDocumentTitle("Vtiger Excution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("VTIGER EXECUTION REPORT");
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BaseUrl", "http://localhost:8888/");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("Password", "123");
		report.setSystemInfo("Reporter Name", "Saibabu");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finished--");
		report.flush();
	}
	

}
