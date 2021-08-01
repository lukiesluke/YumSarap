package com.yumsarap.model;


import android.view.View;

import androidx.annotation.StringRes;

import com.lwg.commons.adapter.RecyclerViewType;
import com.yumsarap.utils.RecyclerViewConstants;

public class HeaderTitleAndDescriptionItem implements RecyclerViewType {

    private String title;
    private String description;
    private int viewType = RecyclerViewConstants.SELECTED_TICKET_TITLE;
    @StringRes
    private int titleRes = View.NO_ID;
    @StringRes
    private int descriptionRes = View.NO_ID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(@StringRes int titleRes) {
        this.titleRes = titleRes;
    }

    public int getDescriptionRes() {
        return descriptionRes;
    }

    public void setDescriptionRes(@StringRes int descriptionRes) {
        this.descriptionRes = descriptionRes;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
