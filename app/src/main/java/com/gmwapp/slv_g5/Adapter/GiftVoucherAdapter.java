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
import com.gmwapp.slv_g5.model.Transanction;

import java.util.List;

public class GiftVoucherAdapter extends RecyclerView.Adapter<GiftVoucherAdapter.TransactionViewHolder> {

    private final Context context;
    private final List<Transanction> transactionList;

    public GiftVoucherAdapter(Context context, List<Transanction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.redeem_list_layout, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transanction transaction = transactionList.get(position);

        // Bind the data to the views
        holder.tvStatus.setText(getStatusTitle(transaction.getType(), transaction.getCodes()));
        holder.tvType.setText(transaction.getDatetime());
        holder.tvAmount.setText("₹" + transaction.getAmount());
        holder.tvDateTime.setText(transaction.getDatetime());
        holder.ivImage.setImageResource(R.drawable.menu_user);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    private String getStatusTitle(String type, String code) {
        switch (type) {
            case "earning_wallet":
                return "Earning Wallet to Balance";
            case "bonus_wallet":
                return "Bonus wallet to Balance";
            case "refer_bonus":
                return "Refer Bonus";
            case "recharge":
                return "Recharge";
            case "plan_activated":
                return "Plan activated";
            case "sync_wallet":
            case "Generated":
                return "Sync Amount to Earning Wallet";
            case "cancelled":
                return "Cancelled withdrawal amount credited";
            case "admin_credit_balance":
                return "Amount credited by admin";
            case "level_income":
                return "Level Income to Bonus Wallet";
            case "outsource_earnings":
                return "Outsource Earnings to Earning Wallet";
            default:
                return "Transaction";
        }
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
