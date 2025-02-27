package com.gmwapp.slv_g5.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.gmwapp.slv_g5.Adapter.ProductAdapter;
import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.helper.Session;
import com.gmwapp.slv_g5.model.HomeProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {


    public ProductFragment() {
        // Required empty public constructor
    }

    Session session;
    Activity activity;
    ImageButton ibBack;
    private List<HomeProduct> homeProduct = new ArrayList<>();  // Declare product list
    private ProductAdapter productAdapter;
    private GridView gridView;  // Declare the GridView
    private LinearLayout llFrame;  // Declare the GridView
    private RelativeLayout rvGiftVoucher;  // Declare the GridView
    Integer productId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);
        activity = getActivity();
        session = new Session(activity);

        if (getArguments() != null) {
            productId = getArguments().getInt("product_id", -1); // Default to -1 if not found
            Log.d("ProductFragment", "Received Product ID: " + productId);
        }

        ibBack = root.findViewById(R.id.ibBack);
        gridView = root.findViewById(R.id.gridView);
        llFrame = root.findViewById(R.id.llFrame);
        rvGiftVoucher = root.findViewById(R.id.rvGiftVoucher);

        // Back button click listener
        ibBack.setOnClickListener(v -> requireActivity().onBackPressed());

        homeProduct.clear();

        if (productId == 1) {// Add demo products
            llFrame.setVisibility(View.GONE);
            rvGiftVoucher.setVisibility(View.VISIBLE);
        } else if (productId == 2) {// Add demo products
            llFrame.setVisibility(View.VISIBLE);
            rvGiftVoucher.setVisibility(View.GONE);
            homeProduct.add(new HomeProduct(1, "Noise Buds VS104", "Piece", "1", "1,099", "https://m.media-amazon.com/images/I/51+e7yrgPpL._SL1500_.jpg", "2025-01-01", "2025-01-01", "Noise Buds VS104 Truly Wireless Earbuds with 45H of Playtime, Quad Mic with ENC, Instacharge(10 min=200 min), 13mm Driver,Low Latency, BT v5.2 (Mint Green)", "4.5"));
            homeProduct.add(new HomeProduct(2, "boAt Bassheads", "Piece", "1", "298", "https://m.media-amazon.com/images/I/513ugd16C6L._SL1500_.jpg", "2025-01-01", "2025-01-01", "boAt Bassheads 100 in Ear Wired Earphones with Mic(Black)", "4.2"));
            homeProduct.add(new HomeProduct(3, "Solar Power Bank", "Piece", "1", "1999", "https://m.media-amazon.com/images/I/41YXeop3YlL.jpg", "2025-01-01", "2025-01-01", "Solar Power Bank, 10000mAh, 15W Fast Charging, Solar Panel, 4-in-1 Cables, 4 Output Ports, 3 Input, Digital Display, LED Torch, for iPhone, Smartphones (Black,White Lithium Polymer)", "4.3"));
        } else if (productId == 3) {// Add demo products
            llFrame.setVisibility(View.VISIBLE);
            rvGiftVoucher.setVisibility(View.GONE);
            homeProduct.add(new HomeProduct(1, "iQOO Z9s 5G", "Piece", "1", "21,999", "https://m.media-amazon.com/images/I/61nO5YRaAxL._SL1200_.jpg", "2025-01-01", "2025-01-01", "iQOO Z9s 5G (Onyx Green, 8GB RAM, 256GB Storage) | 120 Hz 3D Curved AMOLED Display | 5500 mAh Ultra-Thin Battery | Dimesity 7300 5G Processor | Sony IMX882 OIS Camera with Aura Light", "4.5"));
            homeProduct.add(new HomeProduct(2, "Redmi 13C 5G", "Piece", "1", "10,799", "https://m.media-amazon.com/images/I/81KFSdWCCEL._SL1500_.jpg", "2025-01-01", "2025-01-01", "Redmi 13C 5G (Startrail Green,6GB RAM, 128GB Storage) | MediaTek Dimensity 6100+ 5G | 90Hz Display", "4.2"));
            homeProduct.add(new HomeProduct(3, "Redmi Note 14 5G", "Piece", "1", "21,999", "https://m.media-amazon.com/images/I/71lq435TlUL._SL1500_.jpg", "2025-01-01", "2025-01-01", "Redmi Note 14 5G (Phantom Purple, 8GB RAM 256GB Storage) | Global Debut MTK Dimensity 7025 Ultra | 2100 nits Segment Brightest 120Hz AMOLED | 50MP Sony LYT 600 OIS+EIS Triple Camera", "4.3"));
        } else if (productId == 4) {// Add demo products
            llFrame.setVisibility(View.VISIBLE);
            rvGiftVoucher.setVisibility(View.GONE);
            homeProduct.add(new HomeProduct(1, "Samsung 236 L", "Piece", "1", "25,490", "https://m.media-amazon.com/images/I/61TEhUS2SbL._SL1500_.jpg", "2025-01-01", "2025-01-01", "Samsung 236 L, 3 Star, Digital Inverter, Frost Free Double Door Refrigerator (RT28C3053S8/HL, Silver, Elegant Inox)", "4.5"));
            homeProduct.add(new HomeProduct(2, "Whirlpool 235 L", "Piece", "1", "26,490", "https://m.media-amazon.com/images/I/61gZPm6FkWL._SL1477_.jpg", "2025-01-01", "2025-01-01", "Whirlpool 235 L Frost Free Triple-Door Refrigerator (FP 253D PROTTON ROY RADIANT STEEL(Z) Double Door Refrigerator space, 2024 Model)", "4.2"));
            homeProduct.add(new HomeProduct(3, "LG 1.5 Ton 5 Star", "Piece", "1", "46,990", "https://m.media-amazon.com/images/I/81wujRO8qLL._SL1500_.jpg", "2025-01-01", "2025-01-01", "LG 1.5 Ton 5 Star DUAL Inverter Split AC (Copper, AI Convertible 6-in-1, VIRAAT Mode, Faster Cooling & Energy Saving, 4 Way Swing, HD Filter with Anti-Virus Protection, US-Q19YNZE, White)", "4.3"));
            homeProduct.add(new HomeProduct(4, "LG 9 Kg", "Piece", "1", "38,990", "https://m.media-amazon.com/images/I/718Wy4iXY1L._SL1500_.jpg", "2025-01-01", "2025-01-01", "LG 9 Kg, 5 Star, AI Direct Drive Technology, Steam, 6 Motion DD & Wi-Fi Fully-Automatic Front Load Washing Machine (FHP1209Z5M, Intelligent & Convenient Fabric Care, Allergy Care, Middle Black)", "4.3"));
            homeProduct.add(new HomeProduct(5, "Samsung 9 kg", "Piece", "1", "23,990", "https://m.media-amazon.com/images/I/71bCtoaM3hL._SL1500_.jpg", "2025-01-01", "2025-01-01", "Samsung 9 kg, 5 star, Eco Bubble Technology, Wi-Fi, Soft Closing Door, Fully-Automatic Top Load Washing Machine (WA90BG4542BDTL, Versailles Gray)", "4.3"));
        }


        productAdapter = new ProductAdapter(getContext(), homeProduct, requireActivity().getSupportFragmentManager());
        gridView.setAdapter(productAdapter);

        return root;
    }

}