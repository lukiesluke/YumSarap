package com.yumsarap.delegates;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwg.commons.adapter.DelegateAdapter;
import com.yumsarap.R;
import com.yumsarap.adapter.AddsAdapter;
import com.yumsarap.model.AddsItem;

import java.util.List;

public class AddsDelegateAdapter implements DelegateAdapter<AddsDelegateAdapter.AddsViewHolder, AddsItem> {

     List<AddsItem> addsItems;

    public AddsDelegateAdapter() {

    }

    public void setListAddsItem(List<AddsItem> addsItems) {
        this.addsItems = addsItems;
    }

    @Override
    public AddsViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new AddsViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(AddsViewHolder holder, AddsItem AddsItem) {
        holder.adapter.setMenu(addsItems);
    }

    class AddsViewHolder extends RecyclerView.ViewHolder implements AddsAdapter.OnAddsClickListener {
        private final AddsAdapter adapter;

        public AddsViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_adds_list, viewGroup, false));
            Context context = itemView.getContext();

            RecyclerView recyclerView = itemView.findViewById(R.id.recycler);
            adapter = new AddsAdapter(addsItems, this, R.layout.item_menu_b);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void addsItemClicked(View view, int position) {
            Log.d("lwg", "AddsDelegateAdapter: " + addsItems.get(position).getTitle());
        }
    }
}
