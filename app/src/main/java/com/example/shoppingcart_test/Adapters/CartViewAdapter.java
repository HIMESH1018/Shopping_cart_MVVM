package com.example.shoppingcart_test.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart_test.Models.CartItem;
import com.example.shoppingcart_test.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.MyCartViewHolder> {

    private CartClickInterface cartClickInterface;
    private List<CartItem> cartItems;
    private Context context;
    private int qty;
    private static MutableLiveData<List<CartItem>> allMutableCartItems = new MediatorLiveData<>();

    public CartViewAdapter(CartClickInterface cartClickInterface,Context context) {
        this.cartClickInterface = cartClickInterface;
        this.context =context;
    }

    public void setCartItems(List<CartItem> cartItems){

        this.cartItems =cartItems;
        notifyDataSetChanged();
    }

    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cart_item,parent,false);

        CartViewAdapter.MyCartViewHolder myCartViewHolder = new CartViewAdapter.MyCartViewHolder(view);

        return myCartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        qty = cartItem.getQty();
        holder.txtItemName.setText(cartItem.getProductName());
        holder.txtItemPrice.setText("LKR "+String.valueOf(cartItem.getTotalPrice()));
        holder.txtItemQty.setText(String.valueOf(cartItem.getQty()));

        Glide.with(context)
                .load(cartItem.getImageurl())
                .centerCrop()
                .into(holder.imgPreview);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {

        public TextView txtItemPrice,txtItemName,txtItemQty;
        private ImageButton imgBtnQtyDown,imgBtnQtyUp;
        private ImageView imgPreview;



        public MyCartViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemQty = itemView.findViewById(R.id.txtItemQty);
            imgBtnQtyDown =itemView.findViewById(R.id.imgBtnQtyDown);
            imgBtnQtyUp = itemView.findViewById(R.id.imgBtnQtyUp);
            imgPreview = itemView.findViewById(R.id.imgPreview);

            imgBtnQtyUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cartClickInterface.onQtyUp(getAdapterPosition());
                }
            });

            imgBtnQtyDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(qty == 1){

                        cartClickInterface.onQtyDown(getAdapterPosition());
                        Toast.makeText(itemView.getContext(), "Ask you need to remove product", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        cartClickInterface.onQtyDown(getAdapterPosition());
                    }
                    Log.i("cartItemcount2",""+qty);

                }
            });
        }
        }
    public interface CartClickInterface {
        void onDelete(int position);
        void onQtyUp(int position);
        void onQtyDown(int position);

    }
}

