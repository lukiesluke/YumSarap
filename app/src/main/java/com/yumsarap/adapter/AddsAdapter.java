package com.yumsarap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.model.AddsItem;

import java.util.List;

public class AddsAdapter extends RecyclerView.Adapter<AddsAdapter.ViewHolder> {
    private List<AddsItem> menuList;
    private final OnAddsClickListener listener;
    private Context context;
    @LayoutRes
    private int layoutRes;

    public AddsAdapter(List<AddsItem> menuList, OnAddsClickListener listener, @LayoutRes int layoutRes) {
        this.menuList = menuList;
        this.listener = listener;
        this.layoutRes = layoutRes;
    }

    public void setMenu(List<AddsItem> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (layoutRes == -1) {
            layoutRes = R.layout.item_menu;
        }

        View view = inflater.inflate(layoutRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddsAdapter.ViewHolder holder, int position) {
        AddsItem menu = menuList.get(position);
        holder.title.setText(menu.getTitle());
        holder.description.setText(menu.getDescription());
        holder.price.setText(menu.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnAddsClickListener {
        void addsItemClicked(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView description;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            price = itemView.findViewById(R.id.item_price);

            title.setTypeface(Utils.typefaceSemiBold(context));
            description.setTypeface(Utils.typefaceLight(context));
            price.setTypeface(Utils.typefaceRegular(context));
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                v.setTag(menuList.get(getAdapterPosition()));
                listener.addsItemClicked(v, getAdapterPosition());
            }
        }
    }
}
