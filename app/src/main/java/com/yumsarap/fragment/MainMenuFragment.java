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
import com.yumsarap.adapter.MenuAdapter;
import com.yumsarap.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends BaseFragment implements MenuAdapter.OnMenuClickListener {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<MenuItem> menuList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragment newInstance(String param1, String param2) {
        MainMenuFragment fragment = new MainMenuFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        menuList = new ArrayList<>();

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

//        DividerItemDecoration itemDecorator = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireContext(), R.drawable.line_separator)));

        adapter = new MenuAdapter(menuList, this, -1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(adapter);
        adapter.setMenu(menuList);
        return view;
    }

    @Override
    public void itemClickedMenu(View view, int position) {
        MenuItem menu = (MenuItem) view.getTag();
        String menuItemString = gson.toJson(menu);
        String menuListItemString = gson.toJson(menuList);

        Intent intent = new Intent(getActivity(), ItemInformationActivity.class);
        intent.putExtra(ItemInformationActivity.KEY_MENU_SELECT_ITEM, menuItemString);
        intent.putExtra(ItemInformationActivity.KEY_MENU_LIST_ITEM, menuListItemString);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}