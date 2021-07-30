package com.yumsarap.mvp.presenter;

import com.google.common.base.Preconditions;
import com.yumsarap.mvp.view.BaseView;

public abstract class BasePresenter<BV extends BaseView> {
    protected BV view;

    public void attachView(BV view) {
        Preconditions.checkNotNull(view, "view to be attached must not be null");
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    protected boolean isViewAttached() {
        return view != null;
    }
}
