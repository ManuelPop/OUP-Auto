package com.ixxus.oup.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ixxus.ipm.automation.alfresco.Test01_Login;
import com.ixxus.ipm.automation.alfresco.Test05_AddNewComment;
import com.ixxus.ipm.automation.alfresco.Test06_UploadNewVersion;
import com.ixxus.oup.tests.Test01_OUP_Login;

@RunWith(Suite.class)
@SuiteClasses({

        //Vanilla Alfresco Tests
        Test01_OUP_Login.class,
        Test01_Login.class,
        Test06_UploadNewVersion.class,
        Test05_AddNewComment.class,
// comment

})
public class TestSuiteOUP {
}
