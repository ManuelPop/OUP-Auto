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
import com.ixxus.ipm.automation.steps.alfresco.EditPropertiesDocumentSteps;
import com.ixxus.ipm.automation.steps.alfresco.RepositorySteps;
import com.ixxus.ipm.automation.steps.alfresco.SearchResultsSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.oup.steps.EditPropertiesDocumentStepsOUP;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.Search.QuickSearch.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test45_OUP_QuickSearch.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class STest45_QuickSearchOUP extends AbstractBaseSiteTestOUP {

    @Steps
    private DocumentDetailsSteps documentDetailsSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;
    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private RepositorySteps repositorySteps;
    @Steps
    private EditPropertiesDocumentSteps editPropertiesDocumentSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;
    @Steps
	private EditPropertiesDocumentStepsOUP editPropertiesDocumentStepsOUP;

	String fileName1, fileName2, fileName3, title, supplierName;
    int manualScrollSeconds;

    @Test
	@Title("Test45 - OUP - Quick Search")
    public void test45_QuickSearch() {
        siteHeaderSteps.clickOnDocumentLibraryButton();
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName1);
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName2);
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName3);
        documentLibrarySteps.assetFromListClick(fileName1);
        documentDetailsSteps.editPropertiesButtonDocumentActionsClick();
		editPropertiesDocumentStepsOUP.editSupplierName(supplierName);
        editPropertiesDocumentSteps.editTitle(title);
        editPropertiesDocumentSteps.saveButtonClick();
        siteHeaderSteps.clickOnDocumentLibraryButton();

        documentLibrarySteps.enterTextInSearchBox("-" + fileName1);
        searchResultsSteps.searchInSite(siteName);
        searchResultsSteps.isElementPresentInSearchResult(fileName1, manualScrollSeconds, false);

        documentLibrarySteps.enterTextInSearchBox("+" + fileName1);
        searchResultsSteps.isElementPresentInSearchResult(fileName1, manualScrollSeconds, true);

        documentLibrarySteps.enterTextInSearchBox(fileName1 + " AND " + title);
        searchResultsSteps.isElementPresentInSearchResult(fileName1, manualScrollSeconds, true);

        documentLibrarySteps.enterTextInSearchBox(fileName2 + " OR " + fileName3);
        searchResultsSteps.isElementPresentInSearchResult(fileName2, manualScrollSeconds, true);
        searchResultsSteps.isElementPresentInSearchResult(fileName3, manualScrollSeconds, true);

        documentLibrarySteps.enterTextInSearchBox(title);
        searchResultsSteps.isElementPresentInSearchResult(fileName1, manualScrollSeconds, true);

        documentLibrarySteps.enterTextInSearchBox("NOT " + fileName1);
        searchResultsSteps.searchInSite(siteName);
        searchResultsSteps.isElementPresentInSearchResult(fileName1, manualScrollSeconds, false);
    }
}
