package org.mytests.uiobjects.example.site.section;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Image;

public class PopUpWindow extends Section {

    @UI(".close")
    public Button closeButton;

    @UI(".img-responsive")
    public Image popUpWindowImage;
}
