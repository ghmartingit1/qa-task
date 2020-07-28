package org.mytests.tests.UITests;

import org.mytests.tests.TestsInit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.jdi.light.elements.composite.WebPage.back;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static org.mytests.uiobjects.example.site.SitePolixis.*;

public class DataComparisionUITests extends TestsInit {

    SoftAssert softAssert = new SoftAssert();

    Map<String, ArrayList<String>> directors = new HashMap<>();
    ArrayList<String> directorsTempList = new ArrayList<>();
    Map<String, ArrayList<String>> auditors = new HashMap<>();
    ArrayList<String> auditorsTempList = new ArrayList<>();
    Map<String, ArrayList<String>> shareholders = new HashMap<>();
    ArrayList<String> shareholdersTempList = new ArrayList<>();



    @Test(description = "Open Web Application And Navigate To Financial Entities")
    public void testOpenSiteAndFinancialEntitiesPage(){

        popUpWindow.closeButton.click();
        popUpWindow.popUpWindowImage.assertThat().notVisible();
        homePage.navigationMenu.select("Sistema Financiero");
        homePage.entities.iCore().click();
        financialEntitiesPage.checkOpened();
    }

    @Test(description = "Get Directors, Auditors And Shareholders Names", dependsOnMethods = "testOpenSiteAndFinancialEntitiesPage")
    public void testDirectorsAuditorsShareholders(){

        bcoForm.financialEntitiesList.show();
       for(int i = 2; i<=25; i++){ // will be selected only 24 banks from list. For full list please change 25 to bcoForm.financialEntitiesList.size()
           bcoForm.financialEntitiesList.select(i);
           String bankName = bcoForm.financialEntitiesList.selected();
            bcoForm.inputButton.click();
            bcoForm.directorsLink.show();
            bcoForm.directorsLink.click();
            try {
                for (int j = 0; j <= directorsPage.directorsTable.count(); j++) {

                     directorsTempList.add(directorsPage.directorsTable.getCell(2, j).getText());

                }
            } catch (RuntimeException ignore){
                logger.toLog("Directors List is missing ");
            }
           directors.put(bankName, directorsTempList);
           directorsPage.directorsTable.offCache();
            back();

           bcoForm.shareholderLink.show();
           bcoForm.shareholderLink.click();
           try {
               for (int j = 0; j <= shareholdersPage.shareholdersTable.count(); j++) {
                   shareholdersTempList.add( shareholdersPage.shareholdersTable.getCell(1, j).getText());
               }
           } catch (RuntimeException ignore){
               logger.toLog("Shareholders List is missing ");
           }
           shareholders.put(bankName, shareholdersTempList);
           shareholdersPage.shareholdersTable.offCache();
           back();

           bcoForm.auditorsLink.show();
           bcoForm.auditorsLink.click();
           try {
               for (int j = 0; j <= auditorsPage.auditorsTable.count(); j++) {
                   auditorsTempList.add( auditorsPage.auditorsTable.getCell(2, j).getText());
               }
           } catch (RuntimeException ignore) {
               logger.toLog("Auditors List is missing");
           }
           auditors.put(bankName, auditorsTempList);
           auditorsPage.auditorsTable.offCache();
           back();

       }
    }


    @Test(description = "Comparision Directors List With Auditors", dependsOnMethods = "testDirectorsAuditorsShareholders")
    public void testDirectorsAuditors() {

        int count=0;
        for (Map.Entry<String, ArrayList<String>> aud : auditors.entrySet()) {
            for (int i = 0; i < aud.getValue().size(); i++) {
                if (directors.entrySet().toString().contains(aud.getValue().get(i))) {
                    logger.toLog(aud.getKey() + " = " + aud.getValue().get(i));

                    count++;
                }
            }

        }
        softAssert.assertTrue(count > 0, "Comparision Director With Auditors");

    }


    @Test(description = "Comparision Directors List With Shareholders", dependsOnMethods = "testDirectorsAuditors")
    public void testDirectorsShareholders(){
        int count=0;
        for (Map.Entry<String, ArrayList<String>> sh : shareholders.entrySet()) {
            for (int i = 0; i < sh.getValue().size(); i++) {
                if (directors.entrySet().toString().contains(sh.getValue().get(i))) {
                    logger.toLog(sh.getKey() + " = " + sh.getValue().get(i));
                    count++;
                }
            }

        }
        softAssert.assertTrue(count > 0, "Comparision Director With Auditors");
    }
    @Test(description = "Checking If There Are Entities Owned By The Same Shareholders", dependsOnMethods = "testDirectorsShareholders")
    public void testShareholdersChecking(){
         HashMap<ArrayList<String>, List<String>> valueToKeyMapCounter = new HashMap<>();

        for (Map.Entry<String, ArrayList<String>> entry : shareholders.entrySet()) {
            if (valueToKeyMapCounter.containsKey(entry.getValue())) {
                valueToKeyMapCounter.get(entry.getValue()).add(entry.getKey());
            } else {
                List<String> keys = new ArrayList<>();
                keys.add(entry.getKey());
                valueToKeyMapCounter.put(entry.getValue(), keys);
            }
        }
        for (Map.Entry<ArrayList<String>, List<String>> counterEntry : valueToKeyMapCounter.entrySet()) {
            if (counterEntry.getValue().size() > 1) {
                logger.toLog("Duplicated Value:" + counterEntry.getKey() + " for Keys:" + counterEntry.getValue());
            }
        }
        softAssert.assertTrue(!valueToKeyMapCounter.isEmpty(), "Shareholders duplicate records in the list");
        softAssert.assertAll();

    }

}
