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
import com.example.getjson.Retrofit.Adapters.AlbumsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.AlbumsByUserIdInterface;
import com.example.getjson.Retrofit.Models.AlbumsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class AlbumsByUserIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_albums_byUserId)
    RecyclerView mRvAlbumsUserid;
    @BindView(R.id.swipe_albums_byUserId)
    SwipeRefreshLayout mSwipeAlbumsByUserId;
    private List<AlbumsModel> albumsList;
    private AlbumsListAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_albums_by_userid);
        ButterKnife.bind(this);
        initializeViews();
        getAlbumsByUserId();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Albums For UserId = " + "(" + getIntent().getStringExtra("getId") + ")");
    }

    private void initializeViews() {
        mRvAlbumsUserid.setLayoutManager(new LinearLayoutManager(this));
        mRvAlbumsUserid.setHasFixedSize(true);
        mSwipeAlbumsByUserId.setOnRefreshListener(this::getAlbumsByUserId);
    }

    private void getAlbumsByUserId() {
        mSwipeAlbumsByUserId.setRefreshing(true);
        int getId = Integer.parseInt(getIntent().getStringExtra("getId"));
        Map<String, String> getParameters = new HashMap<>();
        getParameters.put("userId", String.valueOf(getId));
        getParameters.put("_sort", "id");
        getParameters.put("_order", "desc");
        Call<List<AlbumsModel>> call = APIControl.getRetrofit().create(AlbumsByUserIdInterface.class).getAlbumsByUserId(getParameters);
        call.enqueue(new Callback<List<AlbumsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<AlbumsModel>> call, @NotNull Response<List<AlbumsModel>> response) {
                mSwipeAlbumsByUserId.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    albumsList = new ArrayList<>(response.body());
                    albumsAdapter = new AlbumsListAdapter(AlbumsByUserIdActivity.this, albumsList);
                    mRvAlbumsUserid.setAdapter(albumsAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<AlbumsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(AlbumsByUserIdActivity.this)) {
                    mSwipeAlbumsByUserId.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeAlbumsByUserId.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
