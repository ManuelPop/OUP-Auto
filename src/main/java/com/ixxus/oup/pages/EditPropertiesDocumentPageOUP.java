package com.ixxus.oup.pages;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ixxus.ipm.automation.pages.AbstractPage;

public class EditPropertiesDocumentPageOUP extends AbstractPage {

    public EditPropertiesDocumentPageOUP(WebDriver driver) {
        super(driver);
    }

	@FindBy(css = "input[id*='default_prop_oup_supplierName']")
	private WebElement supplierNameInput;
    
	public void editSupplierName(String supplierName) {
		element(supplierNameInput).waitUntilVisible();
		supplierNameInput.clear();
		supplierNameInput.sendKeys(supplierName);
	}


}
