package com.example.shoppingcart_test.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart_test.Adapters.ProductItemAdapter;
import com.example.shoppingcart_test.Models.Product;
import com.example.shoppingcart_test.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView shop_recycler;
    ArrayList<Product> bestitems;
    private ProductItemAdapter productAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_home, container, false);

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        shop_recycler = v.findViewById(R.id.shop_recycler);

        shop_recycler.setHasFixedSize(true);
        shop_recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        bestitems = new ArrayList<>();
        mockData();
        return v;
    }


    private ArrayList<Product> mockData(){
        ArrayList<Product> bestSellers = new ArrayList<>();

        bestSellers.add(new Product(1,"IMac",20,699.00,"https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fimac21.jpeg?alt=media&token=e1cf1537-ab30-44a3-91f1-4d6284e79540","PC"));
        bestSellers.add(new Product(2,"IPad",10,1699.00,"https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fipadair.jpeg?alt=media&token=da387155-bd8f-4343-954b-e46da7d252ae","Ipad"));
        bestSellers.add(new Product(3,"Iphone 11",4,2099.00,"https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fipadpro.jpeg?alt=media&token=5d433343-f3b3-43eb-8bf2-5298eb5bf11c","Ipad"));
        bestSellers.add(new Product(4,"Iphone SE",5,1299.00,"https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphone11.jpeg?alt=media&token=c6874af2-c81e-48eb-96e9-2f1f3fad617f","Iphone"));
        bestSellers.add(new Product(5,"MacBook",12,899.00,"https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphonese.jpeg?alt=media&token=8a3a144d-0cd8-4f6d-94cb-0d81634ea5d0","Iphone"));

        productAdapter = new ProductItemAdapter(bestSellers,getActivity());
        shop_recycler.setAdapter(productAdapter);


        productAdapter.setOnItemClickListner(new ProductItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Product product = bestSellers.get(position);
                Bundle b = new Bundle();
                b.putSerializable("pharmacyitem", product);

                ProductDiscriptionFragment productDiscriptionFragment = new ProductDiscriptionFragment();

                productDiscriptionFragment.setArguments(b);

                getActivity().getSupportFragmentManager()
                        .beginTransaction().
                        replace(R.id.fragmentContainer,productDiscriptionFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return bestSellers;
    }
}