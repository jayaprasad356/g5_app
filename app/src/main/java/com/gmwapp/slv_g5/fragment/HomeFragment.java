package com.gmwapp.slv_g5.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.gmwapp.slv_g5.Adapter.CategoryAdapter;
import com.gmwapp.slv_g5.Adapter.ProductAdapter;
import com.gmwapp.slv_g5.Adapter.SliderAdapter;
import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.helper.Session;
import com.gmwapp.slv_g5.model.CategoryModel;
import com.gmwapp.slv_g5.model.HomeProduct;
import com.gmwapp.slv_g5.model.SliderItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private Session session;
    private Activity activity;

    private MaterialButton btnNotification;
    private MaterialButton btnMyOrders;
    private LinearLayout llWaiting;
    private LinearLayout frame;
    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    private List<SliderItem> sliderItems = new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable slideRunnable;

    private GridView gridView, gvCategories;  // Declare the GridView

    private List<CategoryModel> categoryList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private List<HomeProduct> homeProduct = new ArrayList<>();  // Declare product list

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        session = new Session(activity);

        llWaiting = root.findViewById(R.id.llWaiting);
        frame = root.findViewById(R.id.frame);
        btnNotification = root.findViewById(R.id.btnNotification);
        btnMyOrders = root.findViewById(R.id.btnMyOrders);
//        viewPager = root.findViewById(R.id.sliderRecyclerView);
        gridView = root.findViewById(R.id.gridView);

        llWaiting.setVisibility(View.VISIBLE);
        frame.setVisibility(View.GONE);


        fetchSessionDataAndInitialize();
//        imageList(); // Fetch and setup the slider
        demoItemList();


        return root;
    }

    private void demoItemList() {
        homeProduct.clear();

        // Add demo products
        homeProduct.add(new HomeProduct(1, "Best Deal", "Piece", "1", "999", "https://gqncpetg2pdu-u2278.pressidiumcdn.com/wp-content/uploads/2016/07/AFC-Importing-Electronics-1-7-7-16.jpg", "2025-01-01", "2025-01-01", "High-performance laptop", "4.5"));
        homeProduct.add(new HomeProduct(2, "Accessories", "Piece", "1", "499", "https://www.mate.net.in/public/uploads/all/UsReqZvujmEjMUb27qlTtRcCG8Pf18SyULO4HW7U.jpg", "2025-01-01", "2025-01-01", "Latest smartphone", "4.2"));
        homeProduct.add(new HomeProduct(3, "Mobile Phone", "Piece", "1", "199", "https://5.imimg.com/data5/SELLER/Default/2021/9/HR/PW/SE/32162519/vivo-mobile-phone-1000x1000.png", "2025-01-01", "2025-01-01", "Noise-cancelling headphones", "4.3"));
        homeProduct.add(new HomeProduct(4, "Home Appliances", "Piece", "1", "299", "https://media.istockphoto.com/id/1211554164/photo/3d-render-of-home-appliances-collection-set.jpg?s=612x612&w=0&k=20&c=blm3IyPyZo5ElWLOjI-hFMG-NrKQ0G76JpWGyNttF8s=", "2025-01-01", "2025-01-01", "Fitness tracker", "4.6"));


        categoryAdapter = new CategoryAdapter(getContext(), homeProduct, requireActivity().getSupportFragmentManager());
        gridView.setAdapter(categoryAdapter);

        btnNotification.setOnClickListener(v -> navigateToNotificationFragment());
        btnMyOrders.setOnClickListener(v -> navigateToMyOrderFragment());
    }

    private void fetchSessionDataAndInitialize() {
        new Handler().postDelayed(() -> {
            llWaiting.setVisibility(View.GONE);
            frame.setVisibility(View.VISIBLE);
        }, 1000);
    }

//    private void imageList() {
//        // Add demo images to the sliderItems list
//        sliderItems.add(new SliderItem(1, "Bank Background", "", "https://cssslider.com/sliders/demo-17/data1/images/picjumbo.com_hnck0391.jpg", "", ""));
//        sliderItems.add(new SliderItem(2, "Grow Trading", "", "https://cssslider.com/sliders/demo-17/data1/images/picjumbo.com_hnck0391.jpg", "", ""));
//        sliderItems.add(new SliderItem(3, "Bank Background", "", "https://cssslider.com/sliders/demo-17/data1/images/picjumbo.com_hnck0391.jpg", "", ""));
//
//        // Setup slider adapter
//        sliderAdapter = new SliderAdapter(activity, sliderItems);
//        viewPager.setAdapter(sliderAdapter);
//    }

//    private void imageList() {
//        Map<String, String> params = new HashMap<>();
//
//        ApiConfig.RequestToVolley((result, response) -> {
//            if (result) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
//                        JSONArray dataArray = jsonObject.getJSONArray(Constant.DATA);
//                        sliderItems.clear();
//
//                        for (int i = 0; i < dataArray.length(); i++) {
//                            JSONObject itemObject = dataArray.getJSONObject(i);
//                            SliderItem sliderItem = new SliderItem(
//                                    itemObject.getInt("id"),
//                                    itemObject.getString("name"),
//                                    itemObject.getString("link"),
//                                    itemObject.getString("image"),
//                                    itemObject.getString("updated_at"),
//                                    itemObject.getString("created_at")
//                            );
//                            sliderItems.add(sliderItem);
//                        }
//
//                        // Setup slider adapter
//                        sliderAdapter = new SliderAdapter(activity, sliderItems);
//                        viewPager.setAdapter(sliderAdapter);
//
//                        // Auto-slide feature
//                        slideRunnable = () -> {
//                            int nextSlide = (viewPager.getCurrentItem() + 1) % sliderItems.size();
//                            viewPager.setCurrentItem(nextSlide, true);
//                            handler.postDelayed(slideRunnable, 3000); // 3 seconds delay
//                        };
//                        handler.postDelayed(slideRunnable, 3000);
//
//                        // Setup dot indicators
//                        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();
//
//                    } else {
//                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(activity, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, activity, Constant.DATA_LIST, params, true);
//    }

    private void navigateToNotificationFragment() {
        NotificationFragment notificationFragment = new NotificationFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.Container, notificationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToMyOrderFragment() {
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.Container, myOrderFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}