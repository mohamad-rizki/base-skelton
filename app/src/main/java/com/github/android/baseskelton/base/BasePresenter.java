package com.github.android.baseskelton.base;

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewNotAttachedException();
    }

    public static class BaseViewNotAttachedException extends RuntimeException {

        BaseViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
