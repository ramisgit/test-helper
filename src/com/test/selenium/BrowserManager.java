package com.test.selenium;

import java.io.File;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.fx.MainController;
import com.test.utils.CSV_Handler;

public class BrowserManager implements BrowserInterface {
	private WebDriver driver = null;
	
	public BrowserManager(String browser) {
		MainController.log("Starting " + browser + " driver.");
		switch (browser) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				this.driver = new ChromeDriver();
			break;
			case "Mozilla":
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				this.driver = new FirefoxDriver();
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public CSV_Handler handler = null;
	
	public void load_csv(String file_name) {
		handler = new CSV_Handler(file_name);
	}
	
	public void Back() {
		MainController.log("Clicking back button.");
		driver.navigate().back();
	}
	private WebElement getElement(String xpath) {
		MainController.log("Getting element of xpath = " + xpath);
		return driver.findElement(By.xpath(xpath));
	}
	public void Navigate(String url) {
		MainController.log("Navigating to " + url);
		driver.get(url);
	}
	public String getElement_String(String xpath) {
		WebElement temp_ele = driver.findElement(By.xpath(xpath));
		return temp_ele.getText();
	}
	public boolean isElementPresent(String xpath) {
		List<WebElement> ele_list = driver.findElements(By.xpath(xpath));
		if (ele_list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	public List<WebElement> getElementList(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	public void MaximiseWindow() {
		MainController.log("Maximising window.");
		driver.manage().window().maximize();
	}
	public void waitForElement(String xpath) {
		for (int i = 0; !isElementPresent(xpath); i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 50) {
				QuitSession();
				break;
			}
		}
	}
	public void Click(String xpath) {
		MainController.log("Clicking on " + xpath);
		waitForElement(xpath);
		getElement(xpath).click();
	}
	public boolean ScrollToElement(String xpath) {
		List<WebElement> list = getElementList(xpath);
		if (list.size() > 0) {
			//System.out.println("Element found + " + xpath);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", list.get(0));
			return true;
		}else {
			//System.out.println("Element doesnt exist");
			return false;
		}
	}
	public boolean QuitSession() {
		MainController.log("Quitting driver.");
		if (driver != null) {
			driver.quit();
			return true;
		} else {
			return false;
		}
	}
}
