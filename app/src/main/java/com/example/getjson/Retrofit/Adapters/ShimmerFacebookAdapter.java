package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.PostsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShimmerFacebookAdapter extends RecyclerView.Adapter<ShimmerFacebookAdapter.ShimmerFacebookViewHolder> {

    private List<PostsModel> shimmerList;
    private Context mCon;

    public ShimmerFacebookAdapter(Context mCon, List<PostsModel> shimmerList) {
        this.shimmerList = shimmerList;
        this.mCon = mCon;
    }

    @NonNull
    @Override
    public ShimmerFacebookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.card_shimmer_fram, parent, false);
        return new ShimmerFacebookViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShimmerFacebookViewHolder holder, int position) {
        PostsModel shHolderList = shimmerList.get(position);
        holder.tvShimmerUserId.setText("UserId: " + shHolderList.getmUserId());
        holder.tvShimmerPostId.setText("Id: " + shHolderList.getmId());
        holder.tvShimmerTitle.setText("Title: " + shHolderList.getmTitle());
        holder.tvShimmerBody.setText("Body: " + shHolderList.getmText());

        Picasso.get()
                .load(R.drawable.ic_android_green)
                .placeholder(R.drawable.ic_android_green)
                .into(holder.imgShimmer);
    }

    @Override
    public int getItemCount() {
        return shimmerList.size();
    }

    public static class ShimmerFacebookViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imgShimmer)
        ImageView imgShimmer;
        @BindView(R.id.tvShimmer_userId)
        TextView tvShimmerUserId;
        @BindView(R.id.tvShimmer_PostId)
        TextView tvShimmerPostId;
        @BindView(R.id.tvShimmer_Title)
        TextView tvShimmerTitle;
        @BindView(R.id.tvShimmer_Body)
        TextView tvShimmerBody;

        public ShimmerFacebookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
