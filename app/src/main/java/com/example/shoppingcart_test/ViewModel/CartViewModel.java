package com.example.shoppingcart_test.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.shoppingcart_test.Models.CartItem;
import com.example.shoppingcart_test.Models.Product;
import com.example.shoppingcart_test.Repository.CartRepo;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {

    private static  final String TAG= "mycarttest";



    CartRepo cartRepo = CartRepo.getInstance();

    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getAllMutableCartItems();
    }

    public void addProductCart(Product pharmacyItem, int qty){
        Log.d(TAG, "addProductCart: "+pharmacyItem.toString());
        cartRepo.insert(pharmacyItem,qty);
    }

    public void itemqtyUp(int position){

        cartRepo.Itemqtyup(position);

    }

    public void itemqtyDown(int position){

        cartRepo.Itemqtydown(position);

    }

    public void removeCart(){

        cartRepo.removeCart();
    }
}
