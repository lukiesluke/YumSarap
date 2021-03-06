package com.yumsarap.model;

import com.lwg.commons.adapter.RecyclerViewType;
import com.yumsarap.utils.RecyclerViewConstants;

public class AddsItem implements RecyclerViewType {
    private String title;
    private String description;
    private String price;
    private int viewType = RecyclerViewConstants.SELECTED_ADDS_ITEM;

    public AddsItem() {
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }
}
