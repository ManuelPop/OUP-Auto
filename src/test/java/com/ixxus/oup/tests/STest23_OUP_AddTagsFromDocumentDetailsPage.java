package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.steps.alfresco.DocumentDetailsSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentLibrarySteps;
import com.ixxus.ipm.automation.steps.alfresco.EditPropertiesDocumentSteps;
import com.ixxus.ipm.automation.steps.alfresco.SelectTagsPopUpMenuSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.tools.alfresco.Application;
import com.ixxus.oup.steps.EditPropertiesDocumentStepsOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(Application.Tagging.AddTagsFromDocumentDetailsPage.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test23_OUP_AddTagsFromDocumentDetailsPage.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class STest23_OUP_AddTagsFromDocumentDetailsPage extends
		AbstractBaseSiteTestOUP {

    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;
    @Steps
    private DocumentDetailsSteps documentDetailsSteps;
    @Steps
    private EditPropertiesDocumentSteps editPropertiesDocumentSteps;
    @Steps
    private SelectTagsPopUpMenuSteps selectTagsPopUpMenuSteps;
	@Steps
	private EditPropertiesDocumentStepsOUP editPropertiesDocumentStepsOUP;

	String documentName, existingTag, multiwordtag, removetag, documentName2,
			supplierName;
	String newtag = ConstantsOUP.PREFIXS_TAG
			+ RandomStringUtils.randomNumeric(7);

    @Test
	@Title("Test23 - OUP - Add Tags From Document Details Page")
    public void test23_AddTagsFromDocumentDetailsPage() {
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ documentName);
        documentLibrarySteps.assetFromListClick(documentName);
        documentDetailsSteps.editTagsButtonClick();
        editPropertiesDocumentSteps.selectTagsButtonClick();
        selectTagsPopUpMenuSteps.verifyTagNotInListOfExistingTags(newtag);
        selectTagsPopUpMenuSteps.addTag(newtag);

        editPropertiesDocumentSteps.verifySelectedTagsInEditPropertiesPage(newtag);
		editPropertiesDocumentStepsOUP.editSupplierName(supplierName);
        editPropertiesDocumentSteps.saveButtonClick();
        documentDetailsSteps.verifySelectedTagsInDocumentDetailsPage(newtag);

        // existing tag
        siteHeaderSteps.clickOnDocumentLibraryButton();
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ documentName2);
        documentLibrarySteps.assetFromListClick(documentName2);
        documentDetailsSteps.editTagsButtonClick();
        editPropertiesDocumentSteps.selectTagsButtonClick();
        selectTagsPopUpMenuSteps.addExistingTag2(newtag);
        editPropertiesDocumentSteps.verifySelectedTagsInEditPropertiesPage(newtag);
		editPropertiesDocumentStepsOUP.editSupplierName(supplierName);
        editPropertiesDocumentSteps.saveButtonClick();
        documentDetailsSteps.verifySelectedTagsInDocumentDetailsPage(newtag);

        documentDetailsSteps.editTagsButtonClick();
        editPropertiesDocumentSteps.selectTagsButtonClick();

        selectTagsPopUpMenuSteps.removeAttachedTagFromAssetRightList(newtag);
        selectTagsPopUpMenuSteps.verifyTagIsRemovedFromTagsList(newtag);
        selectTagsPopUpMenuSteps.okButtonOnTagsPopUpClick();
        editPropertiesDocumentSteps.verifyTagNotPresentInTagsListEditPropertiesPage(newtag);
        editPropertiesDocumentSteps.saveButtonClick();
        documentDetailsSteps.verifyTagIsNotDisplayed(newtag);
    }
}
