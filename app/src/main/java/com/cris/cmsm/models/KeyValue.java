package com.cris.cmsm.models;

/**
 *
 */

public class KeyValue {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String key;
    String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
