package org.mytests.uiobjects.example.site.pages;

import com.epam.jdi.light.elements.complex.table.Table;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JTable;

public class AuditorsPage extends WebPage {

    @JTable(root ="table:last-of-type.table.table-BCRA.table-bordered.table-hover.table-responsive>tbody",
            row = "//tr[%s]/td",
            column = "//tr/td[%s]",
            cell = "//tr[{1}]/td[{0}]",
            allCells = "td")
    public Table auditorsTable;
}
