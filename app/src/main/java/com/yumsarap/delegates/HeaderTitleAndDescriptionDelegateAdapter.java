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

public class HeaderTitleAndDescriptionDelegateAdapter implements DelegateAdapter<HeaderTitleAndDescriptionDelegateAdapter.HeaderTitleAndDescriptionViewHolder, HeaderTitleAndDescriptionItem> {

    private final HeaderClickListener listener;
    @LayoutRes
    private final int layoutId;

    public interface HeaderClickListener {
        void onHeaderClickListener(int position, CharSequence title);
    }

    public HeaderTitleAndDescriptionDelegateAdapter(HeaderClickListener listener, int layoutId) {
        this.listener = listener;
        this.layoutId = layoutId;
    }

    @Override
    public HeaderTitleAndDescriptionViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new HeaderTitleAndDescriptionDelegateAdapter.HeaderTitleAndDescriptionViewHolder(viewGroup, layoutId);
    }

    @Override
    public void onBindViewHolder(HeaderTitleAndDescriptionViewHolder holder, HeaderTitleAndDescriptionItem item) {
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

    class HeaderTitleAndDescriptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView description;
        private final TextView title;

        HeaderTitleAndDescriptionViewHolder(ViewGroup parent, int layoutId) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));

            title = itemView.findViewById(R.id.textview_title);
            description = itemView.findViewById(R.id.textview_description);
            description.setTypeface(Utils.typefaceRegular(parent.getContext()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (listener != null) {
                listener.onHeaderClickListener(position, title.getText());
            }
        }
    }
}
