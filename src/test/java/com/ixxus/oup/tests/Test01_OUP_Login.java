package com.ixxus.oup.tests;

import junit.framework.TestCase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.ixxus.ipm.automation.steps.alfresco.LoginSteps;
import com.ixxus.oup.tools.ApplicationOUP;
import com.ixxus.oup.tools.ConstantsOUP;

@Story(ApplicationOUP.Login.LoginIntoShare.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = ConstantsOUP.CSV_FILES_PATH + "Test01_OUP_Login.csv", separator = ConstantsOUP.CSV_SEPARATOR)
public class Test01_OUP_Login extends TestCase {
    //public class Test01_OUP_Login extends AbstractBaseSiteTest {

    @Managed(uniqueSession = false)
    public WebDriver driver;
    @ManagedPages(defaultUrl = ConstantsOUP.ALFRESCO_URL)
    public Pages pages;

    @Steps
    private LoginSteps loginSteps;

    String username, password;

    @Test
    @Title("Test01 - OUP - Login")
    public void test01_Login() {
        loginSteps.login(username, password);
    }
}