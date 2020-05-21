/*
 *   @Copyright Hussein Mohamed
 *   Uploaded at 11/5/2020
 *   Email: memailbusiness@gmail.com
 */
package com.example.getjson.Retrofit.Activities.GetById;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.PhotosListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.PhotosByAlbumIdInterface;
import com.example.getjson.Retrofit.Models.PhotosModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosByAlbumIdActivity extends AppCompatActivity {


    @BindView(R.id.rv_photos_byAlbumId)
    RecyclerView mRvPhotosByAlbumId;
    @BindView(R.id.swipe_photos_byAlbumId)
    SwipeRefreshLayout mSwipePhotosByAlbumId;
    private PhotosListAdapter albumsAdapter;
    private List<PhotosModel> photosByAlbumIdModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_photos_albumid);
        ButterKnife.bind(this);
        initializeViews();
        getPhotosByAlbumId();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Photos By AlbumId = " + "(" + getIntent().getStringExtra("albumid") + ")");
    }

    private void initializeViews() {
        mRvPhotosByAlbumId.setLayoutManager(new LinearLayoutManager(this));
        mRvPhotosByAlbumId.setHasFixedSize(true);
        mSwipePhotosByAlbumId.setOnRefreshListener(this::getPhotosByAlbumId);
    }


    private void getPhotosByAlbumId() {
        mSwipePhotosByAlbumId.setRefreshing(true);
        int getAlbumId = Integer.parseInt(getIntent().getStringExtra("albumid"));
        Call<List<PhotosModel>> call = APIControl.getRetrofit().create(PhotosByAlbumIdInterface.class).getAlbums(getAlbumId);
        call.enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<PhotosModel>> call, @NotNull Response<List<PhotosModel>> response) {
                mSwipePhotosByAlbumId.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    photosByAlbumIdModelList = new ArrayList<>(response.body());
                    albumsAdapter = new PhotosListAdapter(PhotosByAlbumIdActivity.this, photosByAlbumIdModelList);
                    mRvPhotosByAlbumId.setAdapter(albumsAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<PhotosModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(PhotosByAlbumIdActivity.this)) {
                    mSwipePhotosByAlbumId.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipePhotosByAlbumId.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
