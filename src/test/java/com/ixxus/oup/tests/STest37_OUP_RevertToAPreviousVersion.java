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
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.VersionControl.RevertToAPreviousVersion.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test37_OUP_RevertToAPreviousVersion.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class STest37_OUP_RevertToAPreviousVersion extends
		AbstractBaseSiteTestOUP {

	@Steps
	private SiteHeaderSteps siteHeaderSteps;
	@Steps
	private DocumentLibrarySteps documentLibrarySteps;

	String fileName, menuOption, itemName, versionType, comment, versionNumber, selectAction, versionNumber1;

	@Test
	@Title("Test37 - OUP - Revert to a previous version")
	public void test37_RevertToAPreviousVersion() {
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName);
		documentLibrarySteps.moreActionMenuClick(menuOption, fileName);
		documentLibrarySteps
				.uploadNewVersionDocument(ConstantsOUP.TESTDATA_FILES_PATH
						+ itemName);
		documentLibrarySteps.selectTheVersionAndAddCommentForTheNewUploadedFileInDocumentLibrary(versionType, comment);
		siteHeaderSteps.clickOnDocumentLibraryButton();
		documentLibrarySteps.checkIfAssetIsPresent(itemName);
		documentLibrarySteps.checkTheVersionOfTheFile(itemName, versionNumber);
		documentLibrarySteps.assetFromListClick(itemName);
		documentLibrarySteps.revertToAPreviousVersion(itemName);
		siteHeaderSteps.clickOnDocumentLibraryButton();
		documentLibrarySteps.checkIfAssetIsPresent(fileName);
		documentLibrarySteps.checkTheVersionOfTheFile(fileName, versionNumber1);
	}
}
