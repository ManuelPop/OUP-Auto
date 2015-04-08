package com.ixxus.oup.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

import com.ixxus.ipm.automation.steps.AbstractSteps;
import com.ixxus.oup.pages.EditPropertiesDocumentPageOUP;

public class EditPropertiesDocumentStepsOUP extends
		AbstractSteps<EditPropertiesDocumentPageOUP> {

    private static final long serialVersionUID = -854607174541078184L;

    public EditPropertiesDocumentStepsOUP(Pages pages) {
		super(pages, EditPropertiesDocumentPageOUP.class);
    }

	@Step("Edit supplier name")
	public void editSupplierName(String supplierName) {
		currentPage().editSupplierName(supplierName);
    }
}
