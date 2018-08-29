package com.fusi24.android.baseskelton;

import timber.log.Timber;

public class DebugApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeTimber();
    }

    private void initializeTimber() {

        Timber.plant(new Timber.DebugTree());
    }
}
