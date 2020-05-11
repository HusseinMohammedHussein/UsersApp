package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.AlbumsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumsListViewHolder> {

    private Context mCon;
    private List<AlbumsModel> mAlbumsList;

    public AlbumsListAdapter(Context mCon, List<AlbumsModel> mAlbumsList) {
        this.mCon = mCon;
        this.mAlbumsList = mAlbumsList;
    }

    @NonNull
    @Override
    public AlbumsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.card_albums_list, parent, false);
        return new AlbumsListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlbumsListViewHolder holder, int position) {
        AlbumsModel albumsModel = mAlbumsList.get(position);
        holder.tvAlbumUserId.setText("User Id:" + albumsModel.getmUserId());
        holder.tvAlbumId.setText("Id:" + albumsModel.getmId());
        holder.tvAlbumTitle.setText("Title:" + albumsModel.getmTitle());
    }

    @Override
    public int getItemCount() {
        return mAlbumsList.size();
    }

    public static class AlbumsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_album_userId)
        TextView tvAlbumUserId;
        @BindView(R.id.tv_album_id)
        TextView tvAlbumId;
        @BindView(R.id.tv_album_title)
        TextView tvAlbumTitle;

        public AlbumsListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
