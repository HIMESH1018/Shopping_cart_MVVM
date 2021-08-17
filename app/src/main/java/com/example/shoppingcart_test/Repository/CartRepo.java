package com.example.shoppingcart_test.Repository;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.shoppingcart_test.Models.CartItem;
import com.example.shoppingcart_test.Models.Product;

import java.util.ArrayList;
import java.util.List;



public  class CartRepo {

    private static  final String TAG= "cartrepops";
    private static MutableLiveData<List<CartItem>> allMutableCartItems = new MediatorLiveData<>();


    private static CartRepo cartRepo = null;
    private Context context;


    // private constructor restricted to this class itself
    private CartRepo()
    {
    }

    // static method to create instance of Singleton class
    public static CartRepo getInstance()
    {
        if (cartRepo == null)
            cartRepo = new CartRepo();

        return cartRepo;
    }




    public static   LiveData<List<CartItem>> getAllMutableCartItems(){

        if(allMutableCartItems.getValue()==null){
            Log.d(TAG, "getAllMutableCartItems: null");
            allMutableCartItems.setValue(new ArrayList<CartItem>());
        }

        Log.d(TAG, "getAllMutableCartItems: ");
        List<CartItem> cartItemList = new ArrayList<>(allMutableCartItems.getValue());

        Log.d(TAG, "insert: size"+cartItemList.size());
        //allMutableCartItems.setValue(new ArrayList<CartItem>());
        return allMutableCartItems;
    }

    public void insert(Product pharmacyItem, int qty){

        if(allMutableCartItems.getValue()==null){
            Log.d(TAG, "insert: recreated the list");
            allMutableCartItems.setValue(new ArrayList<CartItem>());
        }


        List<CartItem> cartItemList = new ArrayList<>(allMutableCartItems.getValue());

        for(CartItem cartItem:cartItemList){

            if(pharmacyItem.getId()==cartItem.getProductId() && pharmacyItem.getName().equals(cartItem.getProductName())){

                if(qty==0){
                    int index = cartItemList.indexOf(cartItem);
                    cartItemList.get(index).setQty(cartItem.getQty()+1);
                    allMutableCartItems.setValue(cartItemList);
                }else{
                    int index = cartItemList.indexOf(cartItem);
                    cartItemList.get(index).setQty(cartItem.getQty()+qty);
                    allMutableCartItems.setValue(cartItemList);
                }
                return;
            }
        }

        Log.d(TAG, "insert: size"+cartItemList.size());

        if(qty==0){
            CartItem cartItem = new CartItem(pharmacyItem.getName(),1,pharmacyItem.getId(), pharmacyItem.getPrice(),pharmacyItem.getImg(),pharmacyItem.getCategory());
            cartItem.setTotalPrice(pharmacyItem.getPrice()*cartItem.getQty());
            cartItemList.add(cartItem);
            allMutableCartItems.setValue(cartItemList);
        }else{
            CartItem cartItem = new CartItem(pharmacyItem.getName(),qty,pharmacyItem.getId(), pharmacyItem.getPrice(),pharmacyItem.getImg(),pharmacyItem.getCategory());
            cartItem.setTotalPrice(pharmacyItem.getPrice()*cartItem.getQty());
            cartItemList.add(cartItem);
            allMutableCartItems.setValue(cartItemList);
        }



    }

    public void Itemqtyup(int position){

        List<CartItem> cartItemList = new ArrayList<>(allMutableCartItems.getValue());

        CartItem updateditem = cartItemList.get(position);

        updateditem.setQty(updateditem.getQty()+1);
        updateditem.setTotalPrice(updateditem.getUnitPrice()*updateditem.getQty());

        cartItemList.set(position,updateditem);

        allMutableCartItems.setValue(cartItemList);

    }

    public void Itemqtydown(int position){

        List<CartItem> cartItemList = new ArrayList<>(allMutableCartItems.getValue());

        CartItem updateditem = cartItemList.get(position);

        int currentQty = updateditem.getQty() - 1;

        if(currentQty==0){

            cartItemList.remove(position);
            allMutableCartItems.setValue(cartItemList);

            return;
        }


        updateditem.setQty(updateditem.getQty()-1);

        updateditem.setTotalPrice(updateditem.getUnitPrice()*updateditem.getQty());

        cartItemList.set(position,updateditem);

        allMutableCartItems.setValue(cartItemList);

    }

    public void removeCart(){
        List<CartItem> cartItemList = new ArrayList<>(allMutableCartItems.getValue());

        cartItemList.clear();

        allMutableCartItems.setValue(cartItemList);

    }
}
