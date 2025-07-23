package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentManager;

public class ExtentTestListener implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getExtentReports();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();
        test.get().log(Status.FAIL, "Test failed: " + throwable);
        if (throwable != null) {
            for (StackTraceElement element : throwable.getStackTrace()) {
                test.get().log(Status.FAIL, element.toString());
            }
        }
        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test skipped");
        test.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Extent report flushed on finish.");
        System.out.println("Report should be generated at: " + new java.io.File("reports/extent-report.html").getAbsolutePath());
    }
}
