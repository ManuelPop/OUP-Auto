package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.steps.alfresco.DocumentDetailsSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentLibrarySteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.VersionControl.VerifyUserTimeCommentForVersions.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test42_OUP_VerifyUserTimeCommentForVersions.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class STest42_OUPVerifyUserTimeCommentForVersions extends
		AbstractBaseSiteTestOUP {

    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;
    @Steps
    private DocumentDetailsSteps documentDetailsSteps;

    String fileName, menuOption, itemName, versionType, comment, noComment, timeBefore, timeAfter;

    @Test
	@Title("Test42 - OUP - Verify username, time and comment are displayed for every version in Version History")
    public void test42_VerifyUserTimeCommentForVersions() {
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName);
        documentLibrarySteps.assetFromListClick(fileName);
		documentDetailsSteps.verifyUsernameTimeAndCommentForLastVersion(
				ConstantsOUP.ALFRESCO_USER_USERNAME, timeBefore, noComment);
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.moreActionMenuClick(menuOption, fileName);
		documentLibrarySteps
				.uploadNewVersionDocument(ConstantsOUP.TESTDATA_FILES_PATH
						+ itemName);
        documentLibrarySteps.selectTheVersionAndAddCommentForTheNewUploadedFileInDocumentLibrary(versionType, comment);
		documentLibrarySteps.assetFromListClick(itemName);
		documentDetailsSteps.verifyUsernameTimeAndCommentForLastVersion(
				ConstantsOUP.ALFRESCO_USER_USERNAME, timeAfter, comment);
    }
}