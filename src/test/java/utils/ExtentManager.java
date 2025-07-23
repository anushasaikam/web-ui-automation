package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    
    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {

            // 1) Build an absolute path to <project‑root>/reports
            String reportDir = System.getProperty("user.dir") + File.separator + "reports";
            // 2) Create the folder if needed
            new File(reportDir).mkdirs();

           ExtentSparkReporter spark = new ExtentSparkReporter(reportDir + File.separator + "extent-report.html");
            spark.config().setReportName("Automation Results");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Anusha");
        }
        return extent;
    }
    

}
