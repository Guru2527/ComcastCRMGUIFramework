package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
    WebDriverUtility wLib;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wLib = new WebDriverUtility(driver);
	}
	
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//textarea[@name='ship_street']")
	private WebElement addressEdt;
	
	@FindBy(xpath="(//input[contains(@value,'Save')])[2]")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(id="phone")
	private WebElement phoneEdt;

	public void createNewOrganization(String org_Name, String address) {
		
		orgNameEdt.sendKeys(org_Name);
		addressEdt.sendKeys(address);
		saveBtn.click();
	}
	
	public void createNewOrganization(String org_Name, String industry, String type, String address) {
		orgNameEdt.sendKeys(org_Name);
		wLib.waitForElementToClick(industryDD);
		wLib.selectByText(industryDD, industry );
		wLib.waitForElementToClick(typeDD);
		wLib.selectByText(typeDD, type);
		addressEdt.sendKeys(address);
		wLib.waitForElementToClick(saveBtn);
		saveBtn.click();
	}
	
	public void createNewOrganization(String org_Name, String phNum, String address) {
		orgNameEdt.sendKeys(org_Name);
		phoneEdt.sendKeys(phNum);
		addressEdt.sendKeys(address);
		saveBtn.click();
	}

}



















