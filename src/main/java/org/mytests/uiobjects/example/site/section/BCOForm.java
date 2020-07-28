package org.mytests.uiobjects.example.site.section;

import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.ByText;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Link;

public class BCOForm extends Form {

    @UI("[name=bco]")
    public Dropdown financialEntitiesList;

    @UI("[type = input]")
    public Button inputButton;

    @ByText("Directivos de las Entidades Financieras")
    public Link directorsLink;

    @ByText("Accionistas de las Entidades Financieras")
    public Link shareholderLink;

    @ByText("Auditores de las Entidades Financieras")
    public Link auditorsLink;
}
