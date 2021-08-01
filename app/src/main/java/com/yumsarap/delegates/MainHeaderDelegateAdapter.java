package com.yumsarap.delegates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.base.Strings;
import com.lwg.commons.adapter.DelegateAdapter;
import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.model.HeaderTitleAndDescriptionItem;

public class MainHeaderDelegateAdapter implements DelegateAdapter<MainHeaderDelegateAdapter.MainHeaderViewHolder, HeaderTitleAndDescriptionItem> {

    private final MainHeaderItemListener listener;
    @LayoutRes
    private final int layoutId;

    public MainHeaderDelegateAdapter(MainHeaderItemListener listener, int layoutId) {
        this.listener = listener;
        this.layoutId = layoutId;
    }

    public interface MainHeaderItemListener {
        void onMainHeaderItemClicked(int position, CharSequence title);
    }

    @Override
    public MainHeaderViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new MainHeaderViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(MainHeaderViewHolder holder, HeaderTitleAndDescriptionItem item) {
        boolean isTitlePresent = !Strings.isNullOrEmpty(item.getTitle()) || item.getTitleRes() != View.NO_ID;
        boolean isDescriptionPresent = !Strings.isNullOrEmpty(item.getDescription()) || item.getDescriptionRes() != View.NO_ID;

        if (item.getTitleRes() != View.NO_ID) {
            holder.title.setText(item.getTitleRes());
        } else {
            holder.title.setText(item.getTitle());
        }

        if (item.getDescriptionRes() != View.NO_ID) {
            holder.description.setText(item.getDescriptionRes());
        } else {
            holder.description.setText(item.getDescription());
        }

        holder.title.setVisibility(isTitlePresent ? View.VISIBLE : View.GONE);
        holder.description.setVisibility(isDescriptionPresent ? View.VISIBLE : View.GONE);
    }

    class MainHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView description;
        private final TextView title;

        public MainHeaderViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));

            title = itemView.findViewById(R.id.textview_title);
            description = itemView.findViewById(R.id.textview_description);
            description.setTypeface(Utils.typefaceRegular(viewGroup.getContext()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (listener != null) {
                listener.onMainHeaderItemClicked(position, title.getText());
            }
        }
    }
}
