package com.yumsarap.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yumsarap.ItemInformationActivity;
import com.yumsarap.R;
import com.yumsarap.adapter.MenuListAdapter;
import com.yumsarap.delegates.MenuItemDelegateAdapter;
import com.yumsarap.model.AddsItem;
import com.yumsarap.model.HeaderTitleAndDescriptionItem;
import com.yumsarap.model.MenuItem;
import com.yumsarap.utils.RecyclerViewConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuListFragment extends BaseFragment implements MenuItemDelegateAdapter.ListItemMenuListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuListFragment() {
    }

    public static MenuListFragment newInstance(String param1, String param2) {
        MenuListFragment fragment = new MenuListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        MenuListAdapter adapter = new MenuListAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        HeaderTitleAndDescriptionItem item = new HeaderTitleAndDescriptionItem();
        item.setTitle("Welcome to Yum Sarap!");
        item.setDescription("We are excited to serve you! Try our yummy Merienda with affordable price. We only deliver Imus and Bacoor area.");
        item.setViewType(RecyclerViewConstants.SELECTED_MAIN_HEADER_ITEM);
        adapter.displayMainHeader(item);

        HeaderTitleAndDescriptionItem merienda = new HeaderTitleAndDescriptionItem();
        merienda.setTitle("Merienda Time!");
        adapter.displayHeaderTitleDescription(merienda);

        adapter.displayItemMenu(itemList());

        HeaderTitleAndDescriptionItem title = new HeaderTitleAndDescriptionItem();

        title.setTitle("Sponsor Adds");
        adapter.displayHeaderTitleDescription(title);
        adapter.displayAdds(addsItemList());
        return view;
    }

    private List<MenuItem> itemList() {
        List<MenuItem> menuList = new ArrayList<>();

        MenuItem menu = new MenuItem();
        menu.setTitle("Spaghetti");
        menu.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu.setPrice("P 80");
        menuList.add(menu);

        MenuItem menu2 = new MenuItem();
        menu2.setTitle("Lechon Kawali");
        menu2.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu2.setPrice("P 100");
        menuList.add(menu2);

        MenuItem menu3 = new MenuItem();
        menu3.setTitle("Ginataan");
        menu3.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu3.setPrice("P 30");
        menuList.add(menu3);

        MenuItem menu4 = new MenuItem();
        menu4.setTitle("Toron Langka");
        menu4.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu4.setPrice("P 20");
        menuList.add(menu4);
        return menuList;
    }

    private List<AddsItem> addsItemList() {
        List<AddsItem> menuList = new ArrayList<>();

        AddsItem menu = new AddsItem();
        menu.setTitle("Spaghetti");
        menu.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu.setPrice("P 80");
        menuList.add(menu);

        AddsItem menu2 = new AddsItem();
        menu2.setTitle("Lechon Kawali");
        menu2.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu2.setPrice("P 100");
        menuList.add(menu2);

        AddsItem menu3 = new AddsItem();
        menu3.setTitle("Ginataan");
        menu3.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu3.setPrice("P 30");
        menuList.add(menu3);

        AddsItem menu4 = new AddsItem();
        menu4.setTitle("Toron Langka");
        menu4.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu4.setPrice("P 20");
        menuList.add(menu4);
        return menuList;
    }

    @Override
    public void onListItemMenuClicked(View v, int adapterPosition) {
        MenuItem menu = (MenuItem) v.getTag();
        String menuItemString = gson.toJson(menu);
        String menuListItemString = gson.toJson(itemList());

        Intent intent = new Intent(getActivity(), ItemInformationActivity.class);
        intent.putExtra(ItemInformationActivity.KEY_MENU_SELECT_ITEM, menuItemString);
        intent.putExtra(ItemInformationActivity.KEY_MENU_LIST_ITEM, menuListItemString);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}