package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.steps.alfresco.DocumentLibrarySteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.tools.alfresco.Constants;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.VersionControl.UploadNewVersion.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH + "Test06_OUP_UploadNewVersion.csv", separator = Constants.CSV_SEPARATOR)
public class Test06_OUP_UploadNewVersion extends AbstractBaseSiteTestOUP {

    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;

    String fileName, menuOption, itemName, versionType, comment, versionNumber, selectAction;

    @Test
    @Title("Test06 - OUP - Upload New Version")
    public void test06_UploadNewVersion() {
        documentLibrarySteps.uploadDocument(Constants.TESTDATA_FILES_PATH + fileName);
        documentLibrarySteps.moreActionMenuClick(menuOption, fileName);
        documentLibrarySteps.uploadNewVersionDocument(Constants.TESTDATA_FILES_PATH + itemName);
        documentLibrarySteps.selectTheVersionAndAddCommentForTheNewUploadedFileInDocumentLibrary(versionType, comment);
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.checkIfAssetIsPresent(itemName);
        documentLibrarySteps.checkTheVersionOfTheFile(itemName, versionNumber);
        documentLibrarySteps.selectAnOptionFromSelectDropDown(selectAction);
        documentLibrarySteps.deleteItemsFromSelectedItemsClick();
    }
}