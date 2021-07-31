package com.yumsarap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.reflect.TypeToken;
import com.yumsarap.R;
import com.yumsarap.Utils;
import com.yumsarap.adapter.MenuAdapter;
import com.yumsarap.model.Menu;
import com.yumsarap.mvp.presenter.OrderPresenter;
import com.yumsarap.mvp.view.OrderInformationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInformationFragment extends BaseFragment implements OrderInformationView, MenuAdapter.OnMenuClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Menu menu;
    private List<Menu> menuList;
    private TextView title;
    private TextView description;
    private TextView price;
    private Toolbar toolbar;
    private final onBackButtonPress listener;
    private final OrderPresenter presenter;
    private RecyclerView recyclerView;
    private MenuAdapter adapter;

    public OrderInformationFragment(onBackButtonPress listener) {
        this.listener = listener;
        presenter = new OrderPresenter();
    }

    public static OrderInformationFragment newInstance(String itemOrder, String listMenuItem, onBackButtonPress listener) {
        OrderInformationFragment fragment = new OrderInformationFragment(listener);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, itemOrder);
        args.putString(ARG_PARAM2, listMenuItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            menu = gson.fromJson(mParam1, Menu.class);
            //Convert string arrayList to model object
            menuList = gson.fromJson(mParam2, new TypeToken<List<Menu>>() {
            }.getType());
        } else {
            menu = new Menu();
            menuList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_information_b, container, false);
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
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    collapsingToolbarLayout.setExpandedTitleTypeface(Utils.typefaceRegular(requireContext()));
                    isShow = false;
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new MenuAdapter(menuList, this, R.layout.item_menu_b);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        adapter.setMenu(menuList);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        presenter.init(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull android.view.Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_option, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void init(View view) {

        toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            setHasOptionsMenu(true);
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
    }

    @Override
    public void itemClickedMenu(View view, int position) {
        menu = (Menu) view.getTag();

        title.setText(menu.getTitle());
        description.setText(menu.getDescription());
        price.setText(menu.getPrice());

        title.startAnimation(AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_out));
        description.startAnimation(AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_out));
        price.startAnimation(AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_out));
    }

    public interface onBackButtonPress {
        void onBackButtonPressFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }
}