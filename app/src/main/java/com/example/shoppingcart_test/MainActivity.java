package com.example.shoppingcart_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.shoppingcart_test.Fragments.CartFragment;
import com.example.shoppingcart_test.Fragments.HomeFragment;
import com.example.shoppingcart_test.Models.CartItem;
import com.example.shoppingcart_test.ViewModel.CartViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomMainNAV;
    public Fragment selectedfragment = null;
    FragmentManager fragmentManager;
    private CartViewModel cartViewModel;
    // FloatingActionButton cart_img_icon;
    TextView cart_img_icon;
    private Animation top;
    String TAG_HOME = "HOME";
    String TAG_CART = "CART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupListners();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        cartnumber();
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

    private void cartnumber(){
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                if (cartItems.size() == 0) {

                    cart_img_icon.setVisibility(View.GONE);

                } else {
                    int count = cartItems.size();
                    if (count > 9) {

                        cart_img_icon.setVisibility(View.VISIBLE);
                        cart_img_icon.setText("9+");
                        cart_img_icon.setAnimation(top);

                    } else {
                        cart_img_icon.setVisibility(View.VISIBLE);
                        cart_img_icon.setText("" + count);
                        cart_img_icon.setAnimation(top);
                    }
                }
            }
        });
    }
}