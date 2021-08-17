package com.example.shoppingcart_test.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingcart_test.Models.Product;
import com.example.shoppingcart_test.R;
import com.example.shoppingcart_test.ViewModel.CartViewModel;


public class ProductDiscriptionFragment extends Fragment implements View.OnClickListener {

    private TextView txtItemQty,txtdescription,txtProductName,txtPreviousPrice,txtdiscount,txtprice;
    private Button btnBuyNow,btnaddCart,btn_reg,btn_login,btn_cancel;
    private ImageButton imgBtnQtyPlus,imgBtnQtyMinus;
    private ImageView imgPreview;
    private int qty;
    private int userOrderdQty=0;
    private CartViewModel cartViewModel;

    public ProductDiscriptionFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_product_discription, container, false);

        View v = inflater.inflate(R.layout.fragment_product_discription, container, false);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        setupUI(v);
        init();
        setupActions();
        btnaddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                Product item = (Product) bundle.getSerializable("pharmacyitem");
                cartViewModel.addProductCart(item, userOrderdQty);
                Toast.makeText(getContext(), "Product Added to the Cart", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void setupUI(View v){

        txtItemQty = v.findViewById(R.id.txtItemQty);
        btnBuyNow = v.findViewById(R.id.btnBuyNow);
        imgBtnQtyMinus = v.findViewById(R.id.imgBtnQtyMinus);
        imgBtnQtyPlus = v.findViewById(R.id.imgBtnQtyPlus);
        btnaddCart= v.findViewById(R.id.btnaddCart);
        txtProductName = v.findViewById(R.id.txtProductName);
        txtPreviousPrice = v.findViewById(R.id.txtPreviousPrice);
        txtdiscount = v.findViewById(R.id.txtdiscount);
        txtprice = v.findViewById(R.id.txtprice);

        imgPreview = v.findViewById(R.id.imgPreview);


    }
    private void init(){

        Bundle bundle = getArguments();
        Product item = (Product) bundle.getSerializable("pharmacyitem");
        String cat = item.getCategory();
        txtProductName.setText(item.getName());
        txtprice.setText(String.format("Rs. %.2f", item.getPrice()));
        Glide.with(getContext())
                .load(item.getImg())
                .override(200,200)
                .into(imgPreview);


        Log.d("TAG", "init: "+item.toString());


    }
    private void setupActions(){
        btnBuyNow.setOnClickListener(this);
        imgBtnQtyPlus.setOnClickListener(this);
        imgBtnQtyMinus.setOnClickListener(this);
        btnaddCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnQtyPlus:

                ++userOrderdQty;
                txtItemQty.setText(String.valueOf(userOrderdQty));
                break;

            case R.id.imgBtnQtyMinus:

                if (userOrderdQty > 0) {
                    --userOrderdQty;
                    txtItemQty.setText(String.valueOf(userOrderdQty));
                }
                break;

            default:
                return;
        }
    }

}