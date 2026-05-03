package com.hpms.patterns.abstractfactory;

public class WindowsCheckbox implements Checkbox {
    @Override
    public String render() {
        return "WindowsCheckbox";
    }
}