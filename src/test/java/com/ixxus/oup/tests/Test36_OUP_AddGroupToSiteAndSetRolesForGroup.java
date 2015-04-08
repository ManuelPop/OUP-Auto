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
import com.ixxus.ipm.automation.steps.alfresco.GroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.MembersSteps;
import com.ixxus.ipm.automation.steps.alfresco.ShareHeaderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteFinderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteGroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.tools.alfresco.Application;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(Application.Groups.AddGroupToSiteAndSetRolesForGroup.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test36_OUP_AddGroupsToSitesAndAddRolesToGroups.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class Test36_OUP_AddGroupToSiteAndSetRolesForGroup extends
		AbstractBaseSiteTestOUP {
   
    @Steps
    private SiteHeaderSteps siteHeaderSteps;
    @Steps
    private ShareHeaderSteps shareHeaderSteps;
    @Steps
    private SiteFinderSteps siteFinderSteps;
    @Steps
    private AdminToolsNavigationSteps adminToolsNavigationSteps;
    @Steps
    private GroupsSteps groupsSteps;
    @Steps
    private MembersSteps membersSteps;
    @Steps
    private SiteGroupsSteps siteGroupsSteps;

    String newGroup, roleName;

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
    @Title("Test36 - Add Groups to sites and add roles to groups")
    public void test36_AddGroupsToSitesAndAddRolesToGroups() {
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsGroupsMenuClick();
        groupsSteps.browseButtonClick();
        groupsSteps.newGroupButtonClick();
        groupsSteps.groupIdentifierInsert(newGroup);
        groupsSteps.groupNameInsert(newGroup);
        groupsSteps.createGroupButtonClick();

        //    	Groups Thing
        shareHeaderSteps.siteFinderButtonClick();
        siteFinderSteps.searchForASite(siteName);
        siteFinderSteps.openTheSiteFound(siteName);
		siteHeaderSteps.inviteUsersHeaderMenuClick();
        membersSteps.groupsPaneButtonClick();
        siteGroupsSteps.addGroupsButtonClick();
        siteGroupsSteps.searchGroup(newGroup);
        siteGroupsSteps.addGroupsToSite(newGroup);
        siteGroupsSteps.checkIfGroupWasAdded(newGroup);
        siteGroupsSteps.selectRole(newGroup, roleName);
        siteGroupsSteps.addGroupsToSiteBtnClick();

        membersSteps.groupsPaneButtonClick();

        siteGroupsSteps.checkSiteGroups(newGroup, roleName);

        //	delete group thing
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsGroupsMenuClick();
        groupsSteps.browseButtonClick();
        groupsSteps.deleteGroup(newGroup);
    }

}
