package main.java.Util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    public int retry=0;
    public int retryanalyser=1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        while(retry<retryanalyser)
        {
            retry++;
            return true;
        }
        return false;
    }
}
