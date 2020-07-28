package org.mytests.tests;

import org.mytests.uiobjects.example.site.SitePolixis;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static org.mytests.uiobjects.example.site.SitePolixis.homePage;

public class TestsInit {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {

        initSite(SitePolixis.class);
        homePage.open();
        logger.info("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {

        getDriver().quit();

    }
}
