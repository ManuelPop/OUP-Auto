package com.ixxus.oup.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ixxus.oup.tests.STest23_OUP_AddTagsFromDocumentDetailsPage;
import com.ixxus.oup.tests.STest37_OUP_RevertToAPreviousVersion;
import com.ixxus.oup.tests.STest42_OUPVerifyUserTimeCommentForVersions;
import com.ixxus.oup.tests.STest45_QuickSearchOUP;
import com.ixxus.oup.tests.Test01_OUP_Login;
import com.ixxus.oup.tests.Test06_OUP_UploadNewVersion;
import com.ixxus.oup.tests.Test07_OUP_DocumentDetailsUploadNewVersion;
import com.ixxus.oup.tests.Test15_OUP_VersionHistorySection;
import com.ixxus.oup.tests.Test36_OUP_AddGroupToSiteAndSetRolesForGroup;
import com.ixxus.oup.tests.Test38_OUP_CheckLockedContent;
import com.ixxus.oup.tests.Test46_OUP_AddGroupToSiteAndSetRoleForUsersInGroup;
import com.ixxus.oup.tests.Test61_OUP_UnlockContentLockedByAnotherUser;

@RunWith(Suite.class)
@SuiteClasses({

// Vanilla Alfresco Tests
		STest23_OUP_AddTagsFromDocumentDetailsPage.class,
		STest37_OUP_RevertToAPreviousVersion.class,
		STest42_OUPVerifyUserTimeCommentForVersions.class,
		STest45_QuickSearchOUP.class, Test01_OUP_Login.class,
		Test06_OUP_UploadNewVersion.class,
		Test07_OUP_DocumentDetailsUploadNewVersion.class,
		Test15_OUP_VersionHistorySection.class,
		Test36_OUP_AddGroupToSiteAndSetRolesForGroup.class,
		Test38_OUP_CheckLockedContent.class,
		Test46_OUP_AddGroupToSiteAndSetRoleForUsersInGroup.class,
		Test61_OUP_UnlockContentLockedByAnotherUser.class,
})
public class TestSuiteOUP {

}
