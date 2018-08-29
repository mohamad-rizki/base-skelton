package com.fusi24.android.baseskelton.ui.user;

import com.fusi24.android.baseskelton.base.BasePresenter;
import com.fusi24.android.baseskelton.data.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class UserPresenter extends BasePresenter<UserView> {

    private CompositeDisposable disposable = new CompositeDisposable();

    private DataManager manager;

    UserPresenter(DataManager manager) {
        this.manager = manager;
    }

    @Override
    public void attachView(UserView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        disposable.dispose();
    }

    void getUserAll() {
        disposable.add(manager.getUserAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(userList -> {
                    if (isViewAttached()) {
                        getView().showUserAll(userList);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable);
                    }
                }));
    }
}
