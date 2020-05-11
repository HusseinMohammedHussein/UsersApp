package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.PhotosModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.PhotosListViewHolder> {
    private Context mCon;
    private List<PhotosModel> photosModelList;

    public PhotosListAdapter(Context mCon, List<PhotosModel> photosModelList) {
        this.mCon = mCon;
        this.photosModelList = photosModelList;
    }

    @NonNull
    @Override
    public PhotosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.card_photos_list, parent, false);
        return new PhotosListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PhotosListViewHolder holder, int position) {
        PhotosModel phList = photosModelList.get(position);
        holder.tvPhotosAlbumid.setText("Album Id: " + phList.getmAlbumId());
        holder.tvPhotosId.setText("Id: " + phList.getmId());
        holder.tvPhotosTitle.setText("Title: " + phList.getmTitle());
        Picasso.get()
                .load(phList.getmUrl())
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(holder.imgPhotosUrl);
        Picasso.get()
                .load(phList.getmThumbnailUrl())
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(holder.imgPhotosThumbnailUrl);
    }

    @Override
    public int getItemCount() {
        return photosModelList.size();
    }


    public static class PhotosListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photos_url)
        ImageView imgPhotosUrl;
        @BindView(R.id.img_photos_thumbnailUrl)
        ImageView imgPhotosThumbnailUrl;
        @BindView(R.id.tv_photos_albumid)
        TextView tvPhotosAlbumid;
        @BindView(R.id.tv_photos_id)
        TextView tvPhotosId;
        @BindView(R.id.tv_photos_title)
        TextView tvPhotosTitle;

        public PhotosListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
