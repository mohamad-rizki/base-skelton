package com.fusi24.android.baseskelton.ui.photo;

import com.fusi24.android.baseskelton.base.BasePresenter;
import com.fusi24.android.baseskelton.data.DataManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class PhotoPresenter extends BasePresenter<PhotoView> {

    private CompositeDisposable disposable = new CompositeDisposable();

    private DataManager manager;

    PhotoPresenter(DataManager manager) {
        this.manager = manager;
    }

    @Override
    public void attachView(PhotoView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        disposable.dispose();
    }

    void getPhotoAllByAlbum(Integer albumId) {
        disposable.add(manager.getPhotoAllByAlbum(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(photoList -> {
                    if (isViewAttached()) {
                        getView().showPhotoAll(photoList);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable);
                    }
                }));
    }
}
