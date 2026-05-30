package com.comcast.crm.generic.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		int count = 0;
		int maxRetry = 5;

		if (count < maxRetry) {
			count++;
			return true; // re-run test
		}
		return false;
	}

}
