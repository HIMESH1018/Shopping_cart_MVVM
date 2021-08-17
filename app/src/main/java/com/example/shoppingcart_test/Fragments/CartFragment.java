package com.example.shoppingcart_test.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart_test.Adapters.CartViewAdapter;
import com.example.shoppingcart_test.Models.CartItem;
import com.example.shoppingcart_test.R;
import com.example.shoppingcart_test.ViewModel.CartViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartViewAdapter.CartClickInterface {

    private RecyclerView recyCartitems;
    private CartViewAdapter cardAdapter;
    private Button btnCheckout;
    private CartViewModel cartViewModel;
    private TextView txtSubTotal;
    private ImageButton imgBtnRemove;

    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_cart, container, false);

        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        setupUi(v);
        setupRecyclerView();

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                //can check empty or not and hide recyclerview and show image
                String formatedTextCheckoutBtn ="Checkout ("+cartItems.size()+")";
                cardAdapter.setCartItems(cartItems);

                double totalPrice = 0;

                for(CartItem cartItem:cartItems){

                    totalPrice += cartItem.getTotalPrice();

                }
                String formatedTotalprice = "Sub Total : Rs "+totalPrice;
                txtSubTotal.setText(formatedTotalprice);
                btnCheckout.setText(formatedTextCheckoutBtn);
            }
        });


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sizecheck();
            }
        });

        imgBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartViewModel.removeCart();
            }
        });


        return v;
    }

    private void setupUi(View view){

        recyCartitems= view.findViewById(R.id.recyCartitems);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        txtSubTotal = view.findViewById(R.id.txtSubTotal);
        imgBtnRemove = view.findViewById(R.id.imgBtnRemove);


    }

    private void sizecheck(){
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                if(cartItems.size() == 0){
                    Toast.makeText(getContext(), "Please Add items and Continue", Toast.LENGTH_SHORT).show();
                }
                else {

                    cartItems.clear();
                    recyCartitems.setVisibility(View.GONE);
                    txtSubTotal.setText("Rs.0.0");
                    btnCheckout.setText("CHECKOUT (0)");
                    imgBtnRemove.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Your Order Completed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setupRecyclerView(){

        recyCartitems.setHasFixedSize(true);

        cardAdapter = new CartViewAdapter(this,getContext());

        recyCartitems.setLayoutManager(new LinearLayoutManager(getContext()));

        recyCartitems.setAdapter(cardAdapter);


    }

    @Override
    public void onDelete(int position) {

    }

    @Override
    public void onQtyUp(int position) {

        cartViewModel.itemqtyUp(position);
    }

    @Override
    public void onQtyDown(int position) {
        cartViewModel.itemqtyDown(position);
    }
}