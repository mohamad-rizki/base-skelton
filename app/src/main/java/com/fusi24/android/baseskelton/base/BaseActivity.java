package com.fusi24.android.baseskelton.base;

import com.fusi24.android.baseskelton.data.DataManager;
import com.fusi24.android.baseskelton.data.api.RestService;
import com.fusi24.android.baseskelton.data.api.RestServiceFactory;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private RestService restService;
    private DataManager manager;

    protected RestService getRestService() {
        if (restService == null) {
            restService = RestServiceFactory.create();
        }
        return restService;
    }

    protected DataManager getManager() {
        if (manager == null) {
            manager = new DataManager(getRestService());
        }
        return manager;
    }
}
