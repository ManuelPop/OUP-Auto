package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.pages.alfresco.ListViewsPage;
import com.ixxus.ipm.automation.pages.alfresco.LoginPage;
import com.ixxus.ipm.automation.pages.alfresco.ShareHeaderPage;
import com.ixxus.ipm.automation.pages.alfresco.SiteHeaderPage;
import com.ixxus.ipm.automation.steps.alfresco.AdminToolsNavigationSteps;
import com.ixxus.ipm.automation.steps.alfresco.AdvancedSearchSteps;
import com.ixxus.ipm.automation.steps.alfresco.CreateContentSteps;
import com.ixxus.ipm.automation.steps.alfresco.CreateUserSteps;
import com.ixxus.ipm.automation.steps.alfresco.DashboardSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentDetailsSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentLibraryLeftPanelSteps;
import com.ixxus.ipm.automation.steps.alfresco.DocumentLibrarySteps;
import com.ixxus.ipm.automation.steps.alfresco.GroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.InviteUsersSteps;
import com.ixxus.ipm.automation.steps.alfresco.LoginSteps;
import com.ixxus.ipm.automation.steps.alfresco.MembersSteps;
import com.ixxus.ipm.automation.steps.alfresco.SearchResultsSteps;
import com.ixxus.ipm.automation.steps.alfresco.ShareHeaderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteDashboardSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteFinderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteGroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.steps.alfresco.UserEditProfileSteps;
import com.ixxus.ipm.automation.steps.alfresco.UsersSteps;
import com.ixxus.ipm.automation.steps.alfresco.workflows.EditTaskSteps;
import com.ixxus.ipm.automation.steps.alfresco.workflows.TasksSteps;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.VersionControl.UnlockContentLockedByAnotherUser.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test61_OUP_UnlockContentLockedByAnotherUser.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class Test61_OUP_UnlockContentLockedByAnotherUser extends
		AbstractBaseSiteTestOUP {

    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private DocumentLibrarySteps documentLibrarySteps;
    @Steps
    private DocumentDetailsSteps documentDetailsSteps;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private DashboardSteps dashboardSteps;
    @Steps
    private DocumentLibraryLeftPanelSteps documentLibraryLeftPanelSteps;
    @Steps
    private ShareHeaderSteps shareHeaderSteps;
    @Steps
    private SiteFinderSteps siteFinderSteps;
    @Steps
    private AdvancedSearchSteps advancedSearchSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;
    @Steps
    private AdminToolsNavigationSteps adminToolsNavigationSteps;
    @Steps
    private GroupsSteps groupsSteps;
    @Steps
    private UsersSteps usersSteps;
    @Steps
    private CreateUserSteps createUserSteps;
    @Steps
    private UserEditProfileSteps editUserProfileSteps;
    @Steps
    private MembersSteps membersSteps;
    @Steps
    private SiteGroupsSteps siteGroupsSteps;
    @Steps
    private InviteUsersSteps inviteUserSteps;
    @Steps
    private TasksSteps taskSteps;
    @Steps
    private EditTaskSteps editTaskSteps;
    @Steps
    private SiteDashboardSteps siteDashboardSteps;
    @Steps
    private CreateContentSteps createContentSteps;

    String fileName, editOfflineAction, cancelEditingAction, newUserName, userEmail, userPassword, managerLockMessage, userLockMessage, roleName, taskName;

    @Override
    @Before
    public void setup() {
		pages.currentPageAt(LoginPage.class).login(
				ConstantsOUP.ALFRESCO_ADMIN_USERNAME,
				ConstantsOUP.ALFRESCO_ADMIN_PASSWORD);
        siteName = pages.currentPageAt(ShareHeaderPage.class).createNewSite();
        pages.currentPageAt(SiteHeaderPage.class).clickOnDocumentLibraryButton();
        pages.currentPageAt(ListViewsPage.class).selectAViewFromOptions(ListViewsPage.DETAILED_VIEW);
        pages.currentPageAt(ListViewsPage.class).verifyCurrentView(ListViewsPage.DETAILED_VIEW);
        pages.currentPageAt(ListViewsPage.class).ensureFoldersAreShown();
    }

    @Test
	@Title("Test61 - OUP -Unlock content locked by another user")
    public void test61_UnlockContentLockedByAnotherUser() {
        //create new user
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsUsersMenuClick();
        usersSteps.deleteUserIfExist(newUserName);
        usersSteps.newUserButtonClick();
        createUserSteps.createNewUser(newUserName, "", userEmail,
                newUserName, userPassword, userPassword, "", false);
        shareHeaderSteps.siteFinderButtonClick();
        siteFinderSteps.searchForASite(siteName);
        siteFinderSteps.openTheSiteFound(siteName);
		siteHeaderSteps.inviteUsersHeaderMenuClick();
		// membersSteps.invitePeopleButtonClick();
        inviteUserSteps.addUser(newUserName);
        inviteUserSteps.checkIfUserWasAddedToLeftPanel(newUserName);
        siteGroupsSteps.selectRole(newUserName, roleName);
        inviteUserSteps.clickToInvite();
        shareHeaderSteps.logoutButtonClick();

        //login and upload file as new user
        loginSteps.login(newUserName, userPassword);
        dashboardSteps.selectTaskFromMyTask(taskName + " " + siteName);
        editTaskSteps.acceptButtonClick();
        shareHeaderSteps.siteFinderButtonClick();
        siteFinderSteps.searchForASite(siteName);
        siteFinderSteps.openTheSiteFound(siteName);
        siteHeaderSteps.clickOnDocumentLibraryButton();
		documentLibrarySteps.uploadDocument(ConstantsOUP.TESTDATA_FILES_PATH
				+ fileName);
        documentLibrarySteps.checkIfLockBannerIsNotDisplayed(fileName);

        //lock content
        documentLibrarySteps.assetFromListClick(fileName);
        documentDetailsSteps.documentActionClick(editOfflineAction);
        documentDetailsSteps.checkIfDocumentIsLocked(managerLockMessage);
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.checkIfLockBannerIsDisplayed(fileName, managerLockMessage);
        shareHeaderSteps.logoutButtonClick();

        //unlock content
		loginSteps.login(ConstantsOUP.ALFRESCO_ADMIN_USERNAME,
				ConstantsOUP.ALFRESCO_ADMIN_PASSWORD);
        shareHeaderSteps.siteFinderButtonClick();
        siteFinderSteps.searchForASite(siteName);
        siteFinderSteps.openTheSiteFound(siteName);
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.checkIfLockBannerIsDisplayed(fileName, userLockMessage);
        documentLibrarySteps.assetFromListClick(fileName);
        documentDetailsSteps.checkIfDocumentIsLocked(userLockMessage);
        documentDetailsSteps.documentActionClick(cancelEditingAction);
        documentDetailsSteps.checkIfDocumentIsNotLocked();
        siteHeaderSteps.clickOnDocumentLibraryButton();
        documentLibrarySteps.checkIfLockBannerIsNotDisplayed(fileName);
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsUsersMenuClick();
        usersSteps.searchUser(newUserName);
        usersSteps.selectUserFromSearchResults(newUserName);
        usersSteps.deleteUserButtonClick();
    }
}
