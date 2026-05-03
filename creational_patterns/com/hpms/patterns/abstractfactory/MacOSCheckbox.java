package com.hpms.patterns.abstractfactory;

public class MacOSCheckbox implements Checkbox {
    @Override
    public String render() {
        return "MacOSCheckbox";
    }
}