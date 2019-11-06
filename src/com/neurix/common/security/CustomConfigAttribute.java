package com.neurix.common.security;

import org.springframework.security.access.ConfigAttribute;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 06/02/13
 * Time: 8:48
 * To change this template use File | Settings | File Templates.
 */
public class CustomConfigAttribute implements ConfigAttribute {

    private String attribute;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }
}
