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
import com.example.getjson.Retrofit.Adapters.PhotosListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.PhotosByAlbumIdInterface;
import com.example.getjson.Retrofit.Models.PhotosModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosByAlbumIdActivity extends AppCompatActivity {


    @BindView(R.id.rv_Albums)
    RecyclerView rvAlbums;
    private PhotosListAdapter albumsAdapter;
    private List<PhotosModel> photosByAlbumIdModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_photos_albumid);
        ButterKnife.bind(this);
        buildRecyclerView();
        getAlbums();
    }

    private void buildRecyclerView() {

        rvAlbums.setHasFixedSize(true);
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));

    }
    private void getAlbums() {
        int getAlbumId = Integer.parseInt(getIntent().getStringExtra("albumid"));
        Call<List<PhotosModel>> call = APIControl.getRetrofit().create(PhotosByAlbumIdInterface.class).getAlbums(getAlbumId);
        call.enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(Call<List<PhotosModel>> call, Response<List<PhotosModel>> response) {
                assert response.body() != null;
                photosByAlbumIdModelList = new ArrayList<>(response.body());
                albumsAdapter = new PhotosListAdapter(PhotosByAlbumIdActivity.this, photosByAlbumIdModelList);
                rvAlbums.setAdapter(albumsAdapter);
                String getActivityName = getIntent().getStringExtra("albumid");
                Toast.makeText(PhotosByAlbumIdActivity.this, "Photos By AlbumId: " + getActivityName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<PhotosModel>> call, Throwable t) {
                Toast.makeText(PhotosByAlbumIdActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
