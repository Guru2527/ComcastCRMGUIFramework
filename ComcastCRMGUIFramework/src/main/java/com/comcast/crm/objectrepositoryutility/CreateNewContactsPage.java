package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactsPage {
	
	WebDriver driver;
	
	public CreateNewContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="(//input[@type='submit'])[2]")
	private WebElement saveContactBtn;
	
	@FindBy(xpath="//input[@name='account_name']//following-sibling::img")
	private WebElement orgLookupIcon;
	
	@FindBy(name="support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name="support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(id="search_txt")
	private WebElement searchTextEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	public void createNewContact(String last_Name) {
		lastNameEdt.sendKeys(last_Name);
		saveContactBtn.click();
	}
	
	public void clickOrgLookupIcon() {
	    orgLookupIcon.click();
	}
	
	public void createNewContactWithOrg(String last_Name) {
        lastNameEdt.sendKeys(last_Name);
	}
	public void saveContact() {
	    saveContactBtn.click();
	}
	
	public void createNewContactWithSupportDate(String last_Name, String start_date, String end_date) {
		lastNameEdt.sendKeys(last_Name);
		startDateEdt.clear();
		startDateEdt.sendKeys(start_date);
		endDateEdt.clear();
		endDateEdt.sendKeys(end_date);
		saveContactBtn.click();
		
	}


}
