package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class LogOut {
	
	WebDriver driver;

	public LogOut(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLinkBtn;
	
	public void logOut() {
		
		Actions action = new Actions(UtilityClassObject.getDriver());
		action.moveToElement(adminImg).perform();
		signOutLinkBtn.click();
	}

}
