package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	WebDriver driver;
	
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//a[text()='Organizations'])[2]/../..//img[@title='Create Organization...']")
	private WebElement createOrganizationBtn;
	
	@FindBy(name="search_text")
	private WebElement searchTextEdt;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement submitBtn;

	public WebElement getCreateOrganizationBtn() {
		return createOrganizationBtn;
	}

	public WebElement getSearchTextEdt() {
		return searchTextEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

}
