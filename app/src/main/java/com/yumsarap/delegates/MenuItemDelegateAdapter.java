package com.yumsarap.delegates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwg.commons.adapter.DelegateAdapter;
import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.model.MenuItem;

public class MenuItemDelegateAdapter implements DelegateAdapter<MenuItemDelegateAdapter.MenuItemViewHolder, MenuItem> {
    private final ListItemMenuListener listener;

    public MenuItemDelegateAdapter(ListItemMenuListener listener) {
        this.listener = listener;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new MenuItemViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, MenuItem item) {
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice());
        holder.object = item;
    }

    public interface ListItemMenuListener {
        void onListItemMenuClicked(View v, int adapterPosition);
    }

    class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView description;
        private final TextView price;
        private Object object = new Object();

        public MenuItemViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));

            Context context = parent.getContext();

            title = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            price = itemView.findViewById(R.id.item_price);

            title.setTypeface(Utils.typefaceSemiBold(context));
            description.setTypeface(Utils.typefaceLight(context));
            price.setTypeface(Utils.typefaceRegular(context));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                v.setTag(object);
                listener.onListItemMenuClicked(v, getAdapterPosition());
            }
        }
    }
}
