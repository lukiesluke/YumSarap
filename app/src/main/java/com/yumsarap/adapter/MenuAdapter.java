package com.yumsarap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.model.Menu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<Menu> menuList;
    private final OnMenuClickListener listener;
    private Context context;

    public MenuAdapter(List<Menu> menuList, OnMenuClickListener listener) {
        this.menuList = menuList;
        this.listener = listener;
    }

    public void setMenu(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.title.setText(menu.getTitle());
        holder.description.setText(menu.getDescription());
        holder.price.setText(menu.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnMenuClickListener {
        void itemClickedMenu(View view, int position);
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
                Menu menu = menuList.get(getAdapterPosition());
                v.setTag(menu);
                listener.itemClickedMenu(v, getAdapterPosition());
            }
        }
    }
}
