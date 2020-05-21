package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.AlbumsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.AlbumsListInterface;
import com.example.getjson.Retrofit.Models.AlbumsModel;

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

public class AlbumsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_albumsList)
    RecyclerView rvAlbumsList;
    @BindView(R.id.swipe_albums_list)
    SwipeRefreshLayout mSwipeAlbumsList;

    private AlbumsListAdapter albumsListAdapter;
    private List<AlbumsModel> albumsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_albums_list);
        ButterKnife.bind(this);
        initialization();
        getAlbumsList();
        String getActivityName = getIntent().getStringExtra("ActivityName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);
    }

    private void initialization() {
        rvAlbumsList.setHasFixedSize(true);
        rvAlbumsList.setLayoutManager(new LinearLayoutManager(this));
        mSwipeAlbumsList.setOnRefreshListener(this::getAlbumsList);

    }

    private void getAlbumsList() {
        mSwipeAlbumsList.setRefreshing(true);

        Call<List<AlbumsModel>> call = APIControl.getRetrofit().create(AlbumsListInterface.class).getAlbumsList();
        call.enqueue(new Callback<List<AlbumsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<AlbumsModel>> call, @NotNull Response<List<AlbumsModel>> response) {
                mSwipeAlbumsList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    albumsList = new ArrayList<>(response.body());
                    albumsListAdapter = new AlbumsListAdapter(AlbumsListActivity.this, albumsList);
                    rvAlbumsList.setAdapter(albumsListAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<AlbumsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(AlbumsListActivity.this)) {
                    mSwipeAlbumsList.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeAlbumsList.setRefreshing(false);
                    message("Error Message: "+t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
