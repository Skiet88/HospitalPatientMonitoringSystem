package com.hpms.patterns;

import com.hpms.patterns.abstractfactory.GUIFactory;
import com.hpms.patterns.abstractfactory.MacOSGUIFactory;
import com.hpms.patterns.abstractfactory.WindowsGUIFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractFactoryTest {

    @Test
    void createsWindowsFamily() {
        GUIFactory factory = new WindowsGUIFactory();
        assertEquals("WindowsButton", factory.createButton().render());
        assertEquals("WindowsCheckbox", factory.createCheckbox().render());
    }

    @Test
    void createsMacFamily() {
        GUIFactory factory = new MacOSGUIFactory();
        assertEquals("MacOSButton", factory.createButton().render());
        assertEquals("MacOSCheckbox", factory.createCheckbox().render());
    }
}