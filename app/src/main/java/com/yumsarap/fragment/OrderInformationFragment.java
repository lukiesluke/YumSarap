package com.yumsarap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.model.Menu;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInformationFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Menu menu;
    private TextView title;
    private TextView description;
    private TextView price;
    private Toolbar toolbar;
    private final onBackButtonPress listener;

    public OrderInformationFragment(onBackButtonPress listener) {
        this.listener = listener;
    }

    public static OrderInformationFragment newInstance(String param1, onBackButtonPress listener) {
        OrderInformationFragment fragment = new OrderInformationFragment(listener);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            menu = gson.fromJson(mParam1, Menu.class);
        } else {
            menu = new Menu();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_information_b, container, false);
        setHasOptionsMenu(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(menu.getTitle());
                    collapsingToolbarLayout.setCollapsedTitleTypeface(Utils.typefaceLight(requireContext()));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));//careful there should a space between double quote otherwise it wont work
                    collapsingToolbarLayout.setExpandedTitleTypeface(Utils.typefaceRegular(requireContext()));
                    isShow = false;
                }
            }
        });
        toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> {
                if (listener != null) {
                    listener.onBackButtonPressFragment();
                }
            });
        }
        title = view.findViewById(R.id.item_name);
        description = view.findViewById(R.id.item_description);
        price = view.findViewById(R.id.item_price);

        title.setText(menu.getTitle());
        description.setText(menu.getDescription());
        price.setText(menu.getPrice());

        title.setTypeface(Utils.typefaceSemiBold(requireContext()));
        description.setTypeface(Utils.typefaceLight(requireContext()));
        price.setTypeface(Utils.typefaceRegular(requireContext()));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull android.view.Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_option, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public interface onBackButtonPress {
        void onBackButtonPressFragment();
    }
}