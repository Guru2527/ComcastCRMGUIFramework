package com.comcast.crm.contactTest_TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseUtility.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrgPopupPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

@Listeners(com.comcast.crm.generic.listenerutility.ListImpClass.class)
public class CreateContactTest extends BaseClass {

	@Test(groups = {"smokeTest", "regressionTest"})
	public void createContactTest() throws Throwable {
		
		// Click on Contact link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contact page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.clickContactsBtn();

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new Contact page");
		ContactsPage cp = new ContactsPage(UtilityClassObject.getDriver());
		cp.getCreateContactsBtn().click();

		// Create Contacts
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		int randomInt = jLib.getRandomNumber();

		String last_Name = eLib.getDataFromExcel("contactTest", 1, 2) + randomInt;

		CreateNewContactsPage cnc = new CreateNewContactsPage(UtilityClassObject.getDriver());
		cnc.createNewContact(last_Name);
		UtilityClassObject.getTest().log(Status.INFO, "New Contacts created succesfully");

		// Verify Header Contacts info Expected Result

		ContactInfoPage cInfo = new ContactInfoPage(UtilityClassObject.getDriver());

		String conHeaderInfo = cInfo.getheaderInfoContact().getText();
		Assert.assertTrue(conHeaderInfo.contains(last_Name), "Contact name verification failed");

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {

		// Click on Contact link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contact page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.clickContactsBtn();;

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new Contact page");
		ContactsPage cp = new ContactsPage(UtilityClassObject.getDriver());
		cp.getCreateContactsBtn().click();

		// Create Contacts
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		int randomInt = jLib.getRandomNumber();

		String start_date = jLib.getSystemDateYYYYMMDD();
		String end_date = jLib.getRequiredDateYYYYMMDD(30);
		String last_Name = eLib.getDataFromExcel("contactTest", 1, 2) + randomInt;

		CreateNewContactsPage cnc = new CreateNewContactsPage(UtilityClassObject.getDriver());
		cnc.createNewContactWithSupportDate(last_Name, start_date, end_date);
		UtilityClassObject.getTest().log(Status.INFO, "New Contacts created succesfully with Support date");

		// Verify Header phone number info Expected Result
		ContactInfoPage cInfo = new ContactInfoPage(UtilityClassObject.getDriver());
		
		String conHeaderInfo = cInfo.getheaderInfoContact().getText();
		Assert.assertTrue(conHeaderInfo.contains(last_Name),"Contact header verification failed");

		String actStartDate = cInfo.getActStartDate().getText();
		Assert.assertEquals(actStartDate, start_date, "Start date verification failed");

		String actEndDate = cInfo.getActEndDate().getText();
		Assert.assertEquals(actEndDate, end_date, "End date verification failed");

	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {

		// Click on Organization link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization page");
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.getOrganizationBtn().click();

		// Navigate to CreateOrganizationPage
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create new Organization page");
		OrganizationPage op = new OrganizationPage(UtilityClassObject.getDriver());
		op.getCreateOrganizationBtn().click();

		// Create Organization
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		int randomInt = jLib.getRandomNumber();

		String org_Name = eLib.getDataFromExcel("contactTest", 7, 3) + randomInt;
		String address = eLib.getDataFromExcel("contactTest", 7, 4);

		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(UtilityClassObject.getDriver());
		cno.createNewOrganization(org_Name, address);
		UtilityClassObject.getTest().log(Status.INFO, "New Organization created succesfully");

		// Wait until Org Info page loads (THIS replaces your hardcoded wait)
		OrganizationInfoPage oip = new OrganizationInfoPage(UtilityClassObject.getDriver());
		String orgHeader = oip.getHeaderInfo().getText();
		Assert.assertTrue(orgHeader.contains(org_Name), "Organization header verification failed");

		// Click on Contacts link
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contacts page");
		hp.clickContactsBtn();

		// Navigate to Create Contacts page
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to crate new Contacts page");
		ContactsPage cp = new ContactsPage(UtilityClassObject.getDriver());
		cp.getCreateContactsBtn().click();

		// Create new Contact
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String last_Name = eLib.getDataFromExcel("contactTest", 7, 2);
		
		CreateNewContactsPage cnp = new CreateNewContactsPage(UtilityClassObject.getDriver());
		cnp.createNewContactWithOrg(last_Name);
		
		// ================= POPUP HANDLING STARTS HERE =================
		cnp.clickOrgLookupIcon();
		
		// WAIT + SWITCH (THIS IS WHERE IT GOES)
		wLib.waitForNumberOfWindows(2);
		wLib.switchToWindowPT("Accounts&action");

		// search & select org (like your working script)
		OrgPopupPage opp = new OrgPopupPage(UtilityClassObject.getDriver());
		wLib.waitForElementToClick(opp.getSearchBox());
		
		opp.searchAndSelectOrg(org_Name);

		// switch back to contact page
		wLib.switchToWindowPT("Contacts&action");
		// ================= POPUP HANDLING ENDS HERE =================

		// Save Contact
		cnp.saveContact();
		UtilityClassObject.getTest().log(Status.INFO, "New Contacts created succesfully with Organization");
		
		// Verify Contact Header info Expected Result
		ContactInfoPage cip = new ContactInfoPage(UtilityClassObject.getDriver());
		
		String contactHeader = cip.getheaderInfoContact().getText();
		Assert.assertTrue(contactHeader.contains(last_Name), "Contacts header verification failed");

	}

}
