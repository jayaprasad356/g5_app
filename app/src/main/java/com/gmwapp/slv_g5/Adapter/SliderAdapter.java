package com.gmwapp.slv_g5.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmwapp.slv_g5.model.SliderItem;
import com.gmwapp.slv_g5.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private final List<SliderItem> items;
    private final Context context;

    public SliderAdapter(Context context, List<SliderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderItem item = items.get(position);
        Log.d("SliderAdapter", "Binding item at position " + position + ", isVideo: " + item.getImage());

        holder.videoView.stopPlayback();
        holder.videoView.setVideoURI(null);

        holder.imageView.setVisibility(View.VISIBLE);
        holder.videoView.setVisibility(View.GONE);

        Glide.with(holder.imageView.getContext())
                .load(item.getImage())
                .into(holder.imageView);

//        if (item.isVideo()) {
//            holder.imageView.setVisibility(View.GONE);
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            holder.videoView.setVideoURI(Uri.parse(item.getUrl()));
//            holder.videoView.setOnPreparedListener(mediaPlayer -> {
//                mediaPlayer.setLooping(true);
//                holder.videoView.start();
//            });
//        } else {
//            holder.imageView.setVisibility(View.VISIBLE);
//            holder.videoView.setVisibility(View.GONE);
//
//            Glide.with(holder.imageView.getContext())
//                    .load(item.getImage())
//                    .into(holder.imageView);
//
//            holder.itemView.setOnClickListener(v -> {
//                Uri webpage = Uri.parse(item.getUrl());
//                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                if (intent.resolveActivity(context.getPackageManager()) != null) {
//                    context.startActivity(intent);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}
