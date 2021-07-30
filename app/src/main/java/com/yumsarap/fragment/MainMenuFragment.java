package com.yumsarap.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yumsarap.ItemInformationActivity;
import com.yumsarap.R;
import com.yumsarap.adapter.MenuAdapter;
import com.yumsarap.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends BaseFragment implements MenuAdapter.OnMenuClickListener {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<Menu> menuList;

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

        Menu menu = new Menu();
        menu.setTitle("Spaghetti");
        menu.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu.setPrice("P 80");
        menuList.add(menu);

        Menu menu2 = new Menu();
        menu2.setTitle("Lechon Kawali");
        menu2.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu2.setPrice("P 100");
        menuList.add(menu2);

        Menu menu3 = new Menu();
        menu3.setTitle("Ginataan");
        menu3.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu3.setPrice("P 30");
        menuList.add(menu3);

        Menu menu4 = new Menu();
        menu4.setTitle("Toron Langka");
        menu4.setDescription("Juicy seasoned beef and pork meatballs in an easy homemade tomato sauce.");
        menu4.setPrice("P 20");
        menuList.add(menu4);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireContext(), R.drawable.line_separator)));

        adapter = new MenuAdapter(menuList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(adapter);
        adapter.setMenu(menuList);
        return view;
    }

    @Override
    public void itemClickedMenu(View view, int position) {
        Menu menu = (Menu) view.getTag();
        String menuString = gson.toJson(menu);

        Intent intent = new Intent(getActivity(), ItemInformationActivity.class);
        intent.putExtra(ItemInformationActivity.KEY_MENU, menuString);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}