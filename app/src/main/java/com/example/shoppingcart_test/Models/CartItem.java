package com.example.shoppingcart_test.Models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CartItem {

    private String productName;

    private int qty;

    private int productId;

    private double totalPrice;

    private double unitPrice;

    private String imageurl;

    private String category;

    public CartItem(String productName, int qty, int productId,double unitPrice, String imageurl,String category) {
        this.productName = productName;
        this.qty = qty;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.imageurl = imageurl;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public float gettotal(){
        float tot = 0;

        tot = (float) (unitPrice*qty);

        return tot;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productName='" + productName + '\'' +
                ", qty=" + qty +
                ", productId=" + productId +
                ", totalPrice=" + totalPrice +
                ", unitPrice=" + unitPrice +
                ", imageurl='" + imageurl + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return qty == cartItem.getQty() &&
                productId == cartItem.productId &&
                Double.compare(cartItem.totalPrice, totalPrice) == 0 &&
                Double.compare(cartItem.unitPrice, unitPrice) == 0 &&
                productName.equals(cartItem.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, qty, productId, totalPrice, unitPrice);
    }

    public static DiffUtil.ItemCallback<CartItem> itemItemCallback = new DiffUtil.ItemCallback<CartItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull CartItem oldItem, @NonNull @NotNull CartItem newItem) {
            return oldItem.getProductId()==newItem.getProductId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull CartItem oldItem, @NonNull @NotNull CartItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
