package com.gmwapp.slv_g5.fragment;

import static com.gmwapp.slv_g5.helper.Constant.SUCCESS;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmwapp.slv_g5.Adapter.GiftVoucherAdapter;
import com.gmwapp.slv_g5.Adapter.TransactionAdapter;
import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.helper.ApiConfig;
import com.gmwapp.slv_g5.helper.Constant;
import com.gmwapp.slv_g5.helper.Session;
import com.gmwapp.slv_g5.model.Transanction;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiftVoucherFragment extends Fragment {

    RecyclerView rvTransaction;
    GiftVoucherAdapter giftVoucherAdapter;
    Activity activity;
    Session session;




    public GiftVoucherFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gift_voucher, container, false);

        activity = getActivity();
        session = new Session(activity);

        rvTransaction = root.findViewById(R.id.rvTransaction);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTransaction.setLayoutManager(linearLayoutManager);

        transactionList();

        return root;
    }

    private void transactionList()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.USER_ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        ArrayList<Transanction> transanctions = new ArrayList<>();
                        Gson g = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Transanction group = g.fromJson(jsonObject1.toString(), Transanction.class);
                                transanctions.add(group);
                            } else {
                                break;
                            }
                        }
                        giftVoucherAdapter = new GiftVoucherAdapter(activity,transanctions);
                        rvTransaction.setAdapter(giftVoucherAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.TRNSACTION_LIST_URL, params, true);
        Log.d("TRNSACTION_LIST_URL", "TRNSACTION_LIST_URL: " + Constant.TRNSACTION_LIST_URL);
        Log.d("TRNSACTION_LIST_URL", "TRNSACTION_LIST_URL params: " + params);
    }
}
