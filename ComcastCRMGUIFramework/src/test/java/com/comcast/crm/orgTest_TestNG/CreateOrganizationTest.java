package com.comcast.crm.orgTest_TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseUtility.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

@Listeners(com.comcast.crm.generic.listenerutility.ListImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = {"smokeTest", "regressionTest"})
	public void createOrganizationTest() throws Throwable {

		// Click on Organization link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.getOrganizationBtn().click();

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new organization page");
		OrganizationPage op = new OrganizationPage(UtilityClassObject.getDriver());
		op.getCreateOrganizationBtn().click();

		// Create Organization
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		int randomInt = jLib.getRandomNumber();

		String org_Name = eLib.getDataFromExcel("org", 1, 2) + randomInt;
		String address = eLib.getDataFromExcel("org", 1, 3);

		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(UtilityClassObject.getDriver());
		cno.createNewOrganization(org_Name, address);
		UtilityClassObject.getTest().log(Status.INFO, "New Organization created succesfully");

		// Verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(UtilityClassObject.getDriver());
		
		String headerInfo = oip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(org_Name), "Organization header verification failed");

		String orgDetails = oip.getOrgDetails().getText();
		Assert.assertEquals(orgDetails, org_Name);

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNumber() throws Throwable {

		// Click on Organization link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.getOrganizationBtn().click();

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new organization page");
		OrganizationPage op = new OrganizationPage(UtilityClassObject.getDriver());
		op.getCreateOrganizationBtn().click();

		// Create Organization
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		int randomInt = jLib.getRandomNumber();

		String org_Name = eLib.getDataFromExcel("org", 7, 2) + randomInt;
		String address = eLib.getDataFromExcel("org", 7, 3);
		String phNum = eLib.getDataFromExcel("org", 7, 4);

		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(UtilityClassObject.getDriver());
		cno.createNewOrganization(org_Name, phNum, address);
		UtilityClassObject.getTest().log(Status.INFO, "New Organization created succesfully with Phone Number");

		// Verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(UtilityClassObject.getDriver());

		String headerInfo = oip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(org_Name), "Organization header verification failed");

		String orgDetails = oip.getOrgDetails().getText();
		Assert.assertEquals(orgDetails, org_Name);

		String contactDetails = oip.getPhoneData().getText();
		Assert.assertEquals(contactDetails, phNum);

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustries() throws Throwable {

		// Click on Organization link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.getOrganizationBtn().click();

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new organization page");
		OrganizationPage op = new OrganizationPage(UtilityClassObject.getDriver());
		op.getCreateOrganizationBtn().click();

		// Create Organization
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		int randomInt = jLib.getRandomNumber();

		String org_Name = eLib.getDataFromExcel("org", 4, 2) + randomInt;
		String address = eLib.getDataFromExcel("org", 4, 3);
		String industry = eLib.getDataFromExcel("org", 4, 4);
		String type = eLib.getDataFromExcel("org", 4, 5);

		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(UtilityClassObject.getDriver());
		cno.createNewOrganization(org_Name, industry, type, address);
		UtilityClassObject.getTest().log(Status.INFO, "New Organization created succesfully with Industries");

		// Verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(UtilityClassObject.getDriver());

		String headerInfo = oip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(org_Name), "Organization header verification failed");
		
		String orgDetails = oip.getOrgDetails().getText();
		Assert.assertEquals(orgDetails, org_Name);

		String indData = oip.getIndData().getText();
		Assert.assertEquals(indData, industry);

		String typeData = oip.getTypeData().getText();
		Assert.assertEquals(typeData, type);

	}

}
