package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgDetails;
	
	@FindBy(id="dtlview_Industry")
	private WebElement indData;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeData;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneData;

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getOrgDetails() {
		return orgDetails;
	}

	public WebElement getIndData() {
		return indData;
	}

	public WebElement getTypeData() {
		return typeData;
	}

	public WebElement getPhoneData() {
		return phoneData;
	}
	
	
}
