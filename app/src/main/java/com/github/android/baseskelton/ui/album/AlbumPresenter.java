package com.github.android.baseskelton.ui.album;

import com.github.android.baseskelton.base.BasePresenter;
import com.github.android.baseskelton.data.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class AlbumPresenter extends BasePresenter<AlbumView> {

    private CompositeDisposable disposable = new CompositeDisposable();

    private DataManager manager;

    AlbumPresenter(DataManager manager) {
        this.manager = manager;
    }

    @Override
    public void attachView(AlbumView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        disposable.dispose();
    }

    void getAlbumAllByUser(Integer userId) {
        disposable.add(manager.getAlbumAllByUser(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(albumList -> {
                    if (isViewAttached()) {
                        getView().showAlbumAll(albumList);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable);
                    }
                }));
    }
}
