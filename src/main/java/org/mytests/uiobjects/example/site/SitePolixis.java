package org.mytests.uiobjects.example.site;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import org.mytests.uiobjects.example.site.pages.*;
import org.mytests.uiobjects.example.site.section.BCOForm;
import org.mytests.uiobjects.example.site.section.PopUpWindow;

@JSite("http://www.bcra.gov.ar")
public class SitePolixis {


    public static HomePage homePage;
    public static PopUpWindow popUpWindow;
    public static FinancialEntitiesPage financialEntitiesPage;
    public static DirectorsPage directorsPage;
    public static ShareholdersPage shareholdersPage;
    public static AuditorsPage auditorsPage;
    public static BCOForm bcoForm;
}
