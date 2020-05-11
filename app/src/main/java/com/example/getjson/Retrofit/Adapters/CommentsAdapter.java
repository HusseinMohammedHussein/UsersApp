package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.CommentsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    private List<CommentsModel> commentsModelList;
    private Context mContext;

    public CommentsAdapter(Context mContext, List<CommentsModel> commentsModelList) {
        this.commentsModelList = commentsModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_comments_list, parent, false);
        return new CommentsViewHolder(view);
    }

    @SuppressLint("setTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        CommentsModel commentsModel = commentsModelList.get(position);
        holder.mTvPostIdComments.setText("posts ID: " + commentsModel.getmPostId());
        holder.mTvPostIdComments.setTextColor(Color.parseColor("#FFFFFF"));
        holder.mTvIDComments.setText("ID: " + commentsModel.getmId());
        holder.mTvIDComments.setTextColor(Color.parseColor("#FFFFFF"));
        holder.mTvNAMEComments.setText("Name: " + commentsModel.getmName());
        holder.mTvNAMEComments.setTextColor(Color.parseColor("#FFFFFF"));
        holder.mTvEMAILComments.setText("Email: " + commentsModel.getmEmail());
        holder.mTvEMAILComments.setTextColor(Color.parseColor("#FFFFFF"));
        holder.mTvBODYComments.setText("Body: " + commentsModel.getmBody());
        holder.mTvBODYComments.setTextColor(Color.parseColor("#FFFFFF"));
        /* TODO: i'll to Search about (TextView how to select some words for color?) */
//        holder.tvNAMEComments.setText("Name: " + Color.parseColor("#008ab3") + commentsModel.getmName());


    }

    @Override
    public int getItemCount() {
        return commentsModelList.size();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_comments_postId)
        TextView mTvPostIdComments;
        @BindView(R.id.tv_comments_id)
        TextView mTvIDComments;
        @BindView(R.id.tv_comments_name)
        TextView mTvNAMEComments;
        @BindView(R.id.tv_comments_email)
        TextView mTvEMAILComments;
        @BindView(R.id.tv_comments_body)
        TextView mTvBODYComments;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
