package org.mytests.uiobjects.example.site.pages;

import com.epam.jdi.light.elements.complex.Menu;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.ByText;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Text;

@Url("/default.asp") @Title("Banco Central de la República Argentina")
public class HomePage extends WebPage {

    @UI("ul.nav.navbar-nav")
    public Menu navigationMenu;

    @ByText("Por entidad")
    public Text entities;

}
