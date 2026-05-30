package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class OrgPopupPage {
	
	WebDriver driver;

    public OrgPopupPage(WebDriver driver) {
    	this.driver = driver;
		PageFactory.initElements(driver, this);
    }

    @FindBy(id="search_txt")
    private WebElement searchBox;

    @FindBy(name="search")
    private WebElement searchBtn;
    
    // dynamic locator (thread safe)
    private WebElement getOrgLink(String orgName) {
        return UtilityClassObject.getDriver().findElement(By.linkText(orgName));
    }
    
    public WebElement getSearchBox() {
        return searchBox;
    }
    
    public void searchAndSelectOrg(String orgName) {

        searchBox.sendKeys(orgName);
        searchBtn.click();
        
        getOrgLink(orgName).click();
    }

}
