package com.fusi24.android.baseskelton.data;

import com.fusi24.android.baseskelton.data.api.RestService;

public class DataManager {

    private RestService restService;

    public DataManager(RestService restService) {
        this.restService = restService;
    }
}
