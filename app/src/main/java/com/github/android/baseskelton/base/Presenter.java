package com.github.android.baseskelton.base;

public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
