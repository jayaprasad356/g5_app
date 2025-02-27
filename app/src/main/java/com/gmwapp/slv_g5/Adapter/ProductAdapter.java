package com.gmwapp.slv_g5.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.fragment.ProductDetailsFragment;
import com.gmwapp.slv_g5.model.HomeProduct;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<HomeProduct> items;
    private FragmentManager fragmentManager;  // Add FragmentManager

    // Constructor now accepts FragmentManager
    public ProductAdapter(Context context, List<HomeProduct> items, FragmentManager fragmentManager) {
        this.context = context;
        this.items = items;
        this.fragmentManager = fragmentManager;  // Initialize FragmentManager
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_home_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        HomeProduct product = items.get(position);

        // Set product details
        viewHolder.title.setText(product.getName());
        viewHolder.price.setText("₹ " + product.getPrice());

        // Load image using Glide
        Glide.with(context)
                .load(product.getImage())
                .placeholder(R.drawable.image_icon)
                .into(viewHolder.image);

        // Set up the click listener for the item
        viewHolder.llItem.setOnClickListener(v -> {
            // Create instance of ProductDetailsFragment
            ProductDetailsFragment fragment = new ProductDetailsFragment();

            // Get product details
            String productName = product.getName();
            String productPrice = product.getPrice();
            String productImage = product.getImage();
            String productDescription = product.getDescription();

            // Create a Bundle to pass data
            Bundle bundle = new Bundle();
            bundle.putString("product_name", productName);
            bundle.putString("product_price", productPrice);
            bundle.putString("product_image", productImage);
            bundle.putString("product_description", productDescription);
            fragment.setArguments(bundle); // Attach the bundle to the fragment

            // Perform fragment transaction
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.Container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });


        return view;
    }

    private static class ViewHolder {
        TextView title, price;
        ImageView image;
        LinearLayout llItem;

        ViewHolder(View view) {
            title = view.findViewById(R.id.tvTitle);
            price = view.findViewById(R.id.tvPrice);
            image = view.findViewById(R.id.ivItemImage);
            llItem = view.findViewById(R.id.llItem);
        }
    }
}


