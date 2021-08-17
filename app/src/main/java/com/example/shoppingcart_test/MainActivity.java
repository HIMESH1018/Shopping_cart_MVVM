package com.example.shoppingcart_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.shoppingcart_test.Fragments.CartFragment;
import com.example.shoppingcart_test.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomMainNAV;
//    private CartViewModel cartViewModel;
    public Fragment selectedfragment = null;
    FragmentManager fragmentManager;
    // FloatingActionButton cart_img_icon;
    TextView cart_img_icon;
    String TAG_HOME = "HOME";
    String TAG_CART = "CART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupListners();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
    }

    private void setupUI(){

        fragmentContainer = findViewById(R.id.fragmentContainer);
        bottomMainNAV = findViewById(R.id.bottomMainNAV);
        cart_img_icon = findViewById(R.id.cart_img_icon);

    }

    private void setupListners(){
        bottomMainNAV.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void openFragment(Fragment fragment,final String tag){
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,fragment,tag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                openFragment(new HomeFragment(),TAG_HOME);
                return true;

            case R.id.menu_cart:
                openFragment(new CartFragment(),TAG_CART);
                return true;


        }
        return false;
    }
}