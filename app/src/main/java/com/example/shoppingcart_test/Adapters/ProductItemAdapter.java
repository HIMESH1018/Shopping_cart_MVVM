package com.example.shoppingcart_test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart_test.Models.Product;
import com.example.shoppingcart_test.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ItemViewHolder> {

    private ArrayList<Product> pharmacyItems;
    private onItemClickListener mlistner;
    private Context context;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public  interface  onItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListner(onItemClickListener listner){
        mlistner = listner;
    }

    public ProductItemAdapter(ArrayList<Product> pharmacyItems, Context context) {
        this.pharmacyItems = pharmacyItems;
        this.context = context;
    }

    @Override
    public ProductItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);

        ItemViewHolder pharamcyItemViewHolder = new ItemViewHolder(v,mlistner);



        return pharamcyItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductItemAdapter.ItemViewHolder holder, int position) {

        Product currentPharmacyItem = pharmacyItems.get(position);

        holder.txtItemName.setText(currentPharmacyItem.getName());
        holder.txtPrice.setText("Rs "+String.format("%.2f", currentPharmacyItem.getPrice()));

        Glide.with(context)
                .load(currentPharmacyItem.getImg())
                .into(holder.imgvitem);

    }

    @Override
    public int getItemCount() {
        return pharmacyItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPrice,txtItemName;
        public ImageView imgvitem;

        public ItemViewHolder(@NonNull @NotNull View itemView,onItemClickListener listener) {
            super(itemView);

            txtPrice = itemView.findViewById(R.id.txtprice);
            txtItemName = itemView.findViewById(R.id.txtItemName);

            imgvitem = itemView.findViewById(R.id.imgvitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            listener.onItemClick(pos);
                        }
                    }


                }
            });
        }
    }
}
