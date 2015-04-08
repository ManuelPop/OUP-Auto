package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ixxus.ipm.automation.pages.alfresco.LoginPage;
import com.ixxus.ipm.automation.pages.alfresco.ShareHeaderPage;
import com.ixxus.ipm.automation.steps.alfresco.AdminToolsNavigationSteps;
import com.ixxus.ipm.automation.steps.alfresco.CreateUserSteps;
import com.ixxus.ipm.automation.steps.alfresco.GroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.MembersSteps;
import com.ixxus.ipm.automation.steps.alfresco.ShareHeaderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteDashboardSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteFinderSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteGroupsSteps;
import com.ixxus.ipm.automation.steps.alfresco.SiteHeaderSteps;
import com.ixxus.ipm.automation.steps.alfresco.UsersSteps;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.Groups.AddGroupToSiteAndSetRoleForUsersInGroup.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH
		+ "Test46_OUP_AddGroupToSiteAndSetRoleForUsersInGroup.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class Test46_OUP_AddGroupToSiteAndSetRoleForUsersInGroup extends
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
    @Steps
    private UsersSteps usersSteps;
    @Steps
    private CreateUserSteps createUserSteps;
    @Steps
    private SiteDashboardSteps siteDashboardSteps;

    String username1, username2, adminName, adminRole, userEmail, userPassword, newGroup, roleName;

    @Override
    @Before
    public void setup() {
		pages.currentPageAt(LoginPage.class).login(
				ConstantsOUP.ALFRESCO_ADMIN_USERNAME,
				ConstantsOUP.ALFRESCO_ADMIN_PASSWORD);
        siteName = pages.currentPageAt(ShareHeaderPage.class).createNewSite();
    }

    @Test
	@Title("Test46 - OUP - Add Groups to sites and set role for users from group")
    public void test46_AddGroupToSiteAndSetRoleForUsersInGroup() {
        //create group
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsGroupsMenuClick();
        groupsSteps.browseButtonClick();
        groupsSteps.newGroupButtonClick();
        groupsSteps.groupIdentifierInsert(newGroup);
        groupsSteps.groupNameInsert(newGroup);
        groupsSteps.createGroupButtonClick();

        // create user1
        shareHeaderSteps.adminToolsButtonClick();
        adminToolsNavigationSteps.adminToolsUsersMenuClick();
        usersSteps.deleteUserIfExist(username1);
        usersSteps.newUserButtonClick();
        createUserSteps.createNewUser(username1, "", userEmail, username1,
                userPassword, userPassword, newGroup, false);

        // create user2
        usersSteps.deleteUserIfExist(username2);
        usersSteps.newUserButtonClick();
        createUserSteps.createNewUser(username2, "", userEmail, username2,
                userPassword, userPassword, newGroup, false);

        //add group to site
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

        siteHeaderSteps.clickOnSiteDashboardButton();

        siteDashboardSteps.checkSiteMember(adminName, adminRole);
        siteDashboardSteps.checkSiteMember(username1, roleName);
        siteDashboardSteps.checkSiteMember(username2, roleName);

    }

}
