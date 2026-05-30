package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="Organizations")
	private WebElement organizationBtn;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsBtn;

	public WebElement getOrganizationBtn() {
		return organizationBtn;
	}
	
	public void clickContactsBtn() {
		contactsBtn.click();
	}
	
}
