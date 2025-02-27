package com.gmwapp.slv_g5.fragment;

import static com.gmwapp.slv_g5.activities.MainActivity.fm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.helper.Session;
import com.google.android.material.button.MaterialButton;

public class MyOrderFragment extends Fragment {


    public MyOrderFragment() {
        // Required empty public constructor
    }

    Session session;
    Activity activity;
    ImageButton ibBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_order, container, false);
        activity = getActivity();
        session = new Session(activity);

        ibBack = root.findViewById(R.id.ibBack);

        // Back button click listener
        ibBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return root;
    }

}
