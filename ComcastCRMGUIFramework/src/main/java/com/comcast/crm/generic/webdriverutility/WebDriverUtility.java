package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor Injection
    public WebDriverUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void setImplicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void waitForElementPresent(WebElement element) {
	    wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitAndClick(WebElement element) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}
	
	public void waitForNumberOfWindows(int expectedCount) {
	    wait.until(ExpectedConditions.numberOfWindowsToBe(expectedCount));
	}
	
	// ===== Frames =====
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	 // ===== Alerts =====
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	 // ===== Dropdown =====
	public void selectByIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void selectByText(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	// ===== Mouse Actions =====
	public void mouseMoveOnElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	// To switch windows different methods
	// Switch to window using partial title
	public void switchToWindowPT(String partialTitle) {
		
		Set<String> windows = driver.getWindowHandles();

		for (String win : windows) {
			driver.switchTo().window(win);
			String title = driver.getTitle();

			if (title.contains(partialTitle)) {
				break;
			}
		}
	}

	public void switchToTabOnURL(String partialURL) {
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actURL = driver.getCurrentUrl();
			if (actURL.equals(partialURL)) {
				break;
			}
		}
	}

	public void switchToTabOnTitle(String partialTitle) {
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actURL = driver.getTitle();
			if (actURL.equals(partialTitle)) {
				break;
			}
		}

	}

}
