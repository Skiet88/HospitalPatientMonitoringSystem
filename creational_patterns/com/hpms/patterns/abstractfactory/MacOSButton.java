package com.hpms.patterns.abstractfactory;

public class MacOSButton implements Button {
    @Override
    public String render() {
        return "MacOSButton";
    }
}