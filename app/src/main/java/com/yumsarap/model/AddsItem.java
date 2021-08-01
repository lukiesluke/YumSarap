package com.yumsarap.model;

import com.lwg.commons.adapter.RecyclerViewType;
import com.yumsarap.utils.RecyclerViewConstants;

public class AddsItem implements RecyclerViewType {
    private String title;
    private String description;
    private String price;

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

    @Override
    public int getViewType() {
        return RecyclerViewConstants.SELECTED_ADDS_ITEM;
    }
}
