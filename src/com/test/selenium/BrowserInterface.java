package com.test.selenium;

import org.openqa.selenium.WebDriver;

public interface BrowserInterface {
	public void Back();
	public void Navigate(String url);
	public void Click(String xpath);
	public void MaximiseWindow();
	public boolean ScrollToElement(String xpath);
}
