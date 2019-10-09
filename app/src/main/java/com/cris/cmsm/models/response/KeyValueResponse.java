package com.cris.cmsm.models.response;

import com.cris.cmsm.models.KeyValue;

import java.util.List;

public class KeyValueResponse {


    private List<KeyValue> keyValueList;

    public List<KeyValue> getKeyValueList() {
        return keyValueList;
    }

    public void setKeyValueList(List<KeyValue> keyValueList) {
        this.keyValueList = keyValueList;
    }
}
