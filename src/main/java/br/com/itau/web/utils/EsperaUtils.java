package br.com.itau.web.utils;

import static br.com.itau.web.webdriver.Driver.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EsperaUtils {

	public  static WebElement waitPresenceOfElement(WebElement locator, int timeout){
		 return new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOf(locator));
	}

}
