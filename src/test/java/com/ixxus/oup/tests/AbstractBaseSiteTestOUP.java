package com.ixxus.oup.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ixxus.ipm.automation.pages.alfresco.ListViewsPage;
import com.ixxus.ipm.automation.pages.alfresco.LoginPage;
import com.ixxus.ipm.automation.pages.alfresco.ShareHeaderPage;
import com.ixxus.ipm.automation.pages.alfresco.SiteFinderPage;
import com.ixxus.ipm.automation.pages.alfresco.SiteHeaderPage;
import com.ixxus.ipm.automation.pages.alfresco.myprofile.MyProfilePage;
import com.ixxus.ipm.automation.pages.alfresco.myprofile.UserTrashcanPage;
import com.ixxus.ipm.automation.tools.alfresco.Constants;
import com.ixxus.oup.tools.ConstantsOUP;

@RunWith(SerenityParameterizedRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractBaseSiteTestOUP {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseSiteTestOUP.class);

    @Managed(uniqueSession = false)
    public WebDriver driver;
    @ManagedPages(defaultUrl = ConstantsOUP.ALFRESCO_URL)
    public Pages pages;

    protected String siteName = "";

    //    Test timeout
    //    1. @Test(timeout = Constants.TEST_TIMEOUT_MILLISECONDS) applies only to @Test and not to @Before and @After
    //    2. @Rule    public Timeout globalTimeout = new Timeout(Long.parseLong(System.getProperty("customTestTimeoutInSeconds")), TimeUnit.SECONDS);
    //    if test timeout is reached then it works
    //    but in case of test pass, thucydides/serenity marks the test as skipped/pending, because it runs in other thread

    @Before
    public void setup() {
        pages.currentPageAt(LoginPage.class).login(Constants.ALFRESCO_USER_USERNAME, Constants.ALFRESCO_USER_PASSWORD);
        LOG.info("Done: login");
        siteName = pages.currentPageAt(ShareHeaderPage.class).createNewSite();
        LOG.info("Done: createNewSite");
        // pages.currentPageAt(SiteDashboardPage.class).verifyIfTheSiteWasCreated(siteName); LOG.info("Done: verifyIfTheSiteWasCreated");
        pages.currentPageAt(SiteHeaderPage.class).clickOnDocumentLibraryButton();
        LOG.info("Done: clickOnDocumentLibraryButton");
        pages.currentPageAt(ListViewsPage.class).selectAViewFromOptions(ListViewsPage.DETAILED_VIEW);
        LOG.info("Done: selectAViewFromOptions");
        pages.currentPageAt(ListViewsPage.class).verifyCurrentView(ListViewsPage.DETAILED_VIEW);
        LOG.info("Done: verifyCurrentView");
        pages.currentPageAt(ListViewsPage.class).ensureFoldersAreShown();
        LOG.info("Done: ensureFoldersAreShown");
    }

    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        LOG.info("Done: clearStepFailures");
        pages.currentPageAt(ShareHeaderPage.class).siteFinderButton();
        LOG.info("Done: siteFinderButton");
        pages.currentPageAt(SiteFinderPage.class).searchForASite(siteName);
        LOG.info("Done: searchForASite");
        pages.currentPageAt(SiteFinderPage.class).deleteSite(siteName, false);
        LOG.info("Done: deleteSite");
        pages.currentPageAt(ShareHeaderPage.class).myProfileButtonClick();
        LOG.info("Done: myProfileButtonClick");
        pages.currentPageAt(MyProfilePage.class).trashcanMenuClick();
        LOG.info("Done: trashcanMenuClick");
        pages.currentPageAt(UserTrashcanPage.class).emptyTrashcan();
        LOG.info("Done: emptyTrashcan");
        pages.currentPageAt(ShareHeaderPage.class).logoutButtonClick();
        LOG.info("Done: logoutButtonClick");
    }
}