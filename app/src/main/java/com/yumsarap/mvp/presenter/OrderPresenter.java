package com.yumsarap.mvp.presenter;

import android.view.View;

import com.yumsarap.mvp.view.OrderInformationView;

public class OrderPresenter extends OrderInformationPresenter<OrderInformationView> {

    public void init(View view) {
        this.view.init(view);
    }
}
