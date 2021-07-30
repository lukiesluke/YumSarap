package com.yumsarap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.yumsarap.fragment.OrderInformationFragment;
import com.yumsarap.model.Menu;

public class ItemInformationActivity extends AppCompatActivity implements OrderInformationFragment.onBackButtonPress {

    public static final String KEY_MENU = "KEY_MENU";
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_information);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Menu menu = gson.fromJson(bundle.getString(KEY_MENU), Menu.class);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container_view, OrderInformationFragment.newInstance(bundle.getString(KEY_MENU), this));
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.xml.left_to_right, R.xml.right_to_left);
    }

    @Override
    public void onBackButtonPressFragment() {
        onBackPressed();
    }
}