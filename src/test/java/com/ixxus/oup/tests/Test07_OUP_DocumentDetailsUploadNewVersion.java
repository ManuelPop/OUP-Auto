package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.alfresco.AbstractBaseSiteTest;
import com.ixxus.ipm.automation.steps.alfresco.ActionsSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentLibrarySteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.tools.alfresco.Application;
import com.ixxus.ipm.automation.tools.alfresco.Constants;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(Application.VersionControl.DocumentDetailsUploadNewVersion.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test07_OUP_DocumentDetailsUploadNewVersion.csv", separator = Constants.CSV_SEPARATOR)
public class Test07_OUP_DocumentDetailsUploadNewVersion extends AbstractBaseSiteTest {

    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;
    @Steps
    private ActionsSteps actionsSteps;

    String fileName, itemName, versionType, comment, versionNumber, selectAction;

    @Test
	@Title("Test07_OUP - Document Details Upload New Version")
    public void test07_DocumentDetailsUploadNewVersion() {
        documentLibrarySteps.uploadDocument(Constants.TESTDATA_FILES_PATH + fileName);
        documentLibrarySteps.selectDocumentFromDocumentLibrary(fileName);
        actionsSteps.clickOnUploadNewVersion();
        documentLibrarySteps.uploadNewVersionDocument(Constants.TESTDATA_FILES_PATH + itemName);
        documentLibrarySteps.selectTheVersionAndAddCommentForTheNewUploadedFile(versionType, comment);
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.checkIfAssetIsPresent(fileName);
        documentLibrarySteps.checkTheVersionOfTheFile(fileName, versionNumber);
        documentLibrarySteps.selectAnOptionFromSelectDropDown(selectAction);
        documentLibrarySteps.deleteItemsFromSelectedItemsClick();
    }

}
