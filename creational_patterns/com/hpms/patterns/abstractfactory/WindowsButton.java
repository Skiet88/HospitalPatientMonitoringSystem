package com.hpms.patterns.abstractfactory;

public class WindowsButton implements Button {
    @Override
    public String render() {
        return "WindowsButton";
    }
}