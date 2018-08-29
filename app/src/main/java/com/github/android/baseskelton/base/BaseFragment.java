package com.github.android.baseskelton.base;

import android.os.Bundle;

import com.github.android.baseskelton.data.DataManager;
import com.github.android.baseskelton.data.api.RestService;
import com.github.android.baseskelton.data.api.RestServiceFactory;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private RestService restService;
    private DataManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
