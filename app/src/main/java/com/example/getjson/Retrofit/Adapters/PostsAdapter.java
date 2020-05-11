package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.PostsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private List<PostsModel> mPostsList;
    private Context context;

    public PostsAdapter(Context context, List<PostsModel> postsList) {
        this.mPostsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_posts_list, parent, false);
        return new PostsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        PostsModel posts = mPostsList.get(position);
        holder.mtvUserId.setText("User Id: " + posts.getmUserId());
        holder.mTvId.setText("ID: " + posts.getmId());
        holder.mTvTitle.setText("Title: " + posts.getmTitle());
        holder.mTvBody.setText("Body: " + posts.getmText());
    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }


    public static class PostsViewHolder extends RecyclerView.ViewHolder {

        private TextView mtvUserId, mTvId, mTvTitle, mTvBody;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvUserId = itemView.findViewById(R.id.tv_userId);
            mTvId = itemView.findViewById(R.id.tv_Id);
            mTvTitle = itemView.findViewById(R.id.tv_Title);
            mTvBody = itemView.findViewById(R.id.tv_Body);
        }
    }
}
