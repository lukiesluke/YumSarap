package com.yumsarap.adapter;

import android.util.Log;

import androidx.collection.SparseArrayCompat;

import com.yumsarap.R;
import com.yumsarap.delegates.AddsDelegateAdapter;
import com.yumsarap.delegates.HeaderTitleAndDescriptionDelegateAdapter;
import com.yumsarap.delegates.MainHeaderDelegateAdapter;
import com.yumsarap.delegates.MenuItemDelegateAdapter;
import com.yumsarap.model.AddsItem;
import com.yumsarap.model.HeaderTitleAndDescriptionItem;
import com.yumsarap.model.MenuItem;
import com.yumsarap.utils.RecyclerViewConstants;

import java.util.List;

public class MenuListAdapter extends BaseViewAdapter implements HeaderTitleAndDescriptionDelegateAdapter.ListItemListener, MainHeaderDelegateAdapter.MainHeaderItemListener {
    AddsDelegateAdapter addsDelegateAdapter = new AddsDelegateAdapter();

    public MenuListAdapter(MenuItemDelegateAdapter.ListItemMenuListener listItemMenuListener) {

        delegateAdapters = new SparseArrayCompat<>();
        delegateAdapters.put(RecyclerViewConstants.SELECTED_MAIN_HEADER_ITEM, new MainHeaderDelegateAdapter(this, R.layout.item_main_header));
        delegateAdapters.put(RecyclerViewConstants.SELECTED_TITLE_ORDER, new HeaderTitleAndDescriptionDelegateAdapter(this, R.layout.item_header_text_and_description));
        delegateAdapters.put(RecyclerViewConstants.SELECTED_MENU_ITEM, new MenuItemDelegateAdapter(listItemMenuListener));
        delegateAdapters.put(RecyclerViewConstants.SELECTED_ADDS_ITEM, addsDelegateAdapter);
    }

    public void displayMainHeader(HeaderTitleAndDescriptionItem item) {
        addViewTypeOnceAndNotify(item);
    }

    public void displayHeaderTitleDescription(HeaderTitleAndDescriptionItem item) {
        addViewTypeOnceAndNotify(item);
    }

    public void displayItemMenu(List<MenuItem> itemList) {
        for (MenuItem item : itemList) {
            addViewTypeOnceAndNotify(item);
        }
    }

    public void displayAdds(List<AddsItem> addsItemList) {
        addsDelegateAdapter.setListAddsItem(addsItemList);
        addViewTypeOnceAndNotify(new AddsItem());
    }

    @Override
    public void onListItemClicked(int position, CharSequence title) {
        Log.d("lwg", "Menu Item clicked() position: " + position + " : " + title);
    }

    @Override
    public void onMainHeaderItemClicked(int position, CharSequence title) {
        Log.d("lwg", "onMainHeaderItemClicked position: " + position + " : " + title);
    }
}
