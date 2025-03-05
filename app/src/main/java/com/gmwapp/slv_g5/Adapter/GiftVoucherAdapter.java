package com.gmwapp.slv_g5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmwapp.slv_g5.R;
import com.gmwapp.slv_g5.model.GiftVoucherModel;
import com.gmwapp.slv_g5.model.Transanction;

import java.util.List;

public class GiftVoucherAdapter extends RecyclerView.Adapter<GiftVoucherAdapter.TransactionViewHolder> {

    private final Context context;
    private final List<GiftVoucherModel> giftVoucherModel;

    public GiftVoucherAdapter(Context context, List<GiftVoucherModel> giftVoucherModel) {
        this.context = context;
        this.giftVoucherModel = giftVoucherModel;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.redeem_list_layout, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        GiftVoucherModel giftVoucher = giftVoucherModel.get(position);

        // Bind the data to the views
        holder.tvStatus.setText(giftVoucher.getName());
        holder.tvType.setText(giftVoucher.getType());
        holder.tvAmount.setText("₹" + giftVoucher.getAmount());
        holder.tvDateTime.setText(giftVoucher.getDatetime());
        holder.ivImage.setImageResource(R.drawable.menu_user);
    }

    @Override
    public int getItemCount() {
        return giftVoucherModel.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        final TextView tvStatus;
        final TextView tvType;
        final TextView tvAmount;
        final TextView tvDateTime;
        final ImageView ivImage;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvType = itemView.findViewById(R.id.tvType);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
