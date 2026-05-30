package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./configAppData/CommonData_Vtiger.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		
		String data = pObj.getProperty(key);
		return data;
	}

}
