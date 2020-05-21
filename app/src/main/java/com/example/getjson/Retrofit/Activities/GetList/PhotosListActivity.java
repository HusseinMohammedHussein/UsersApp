package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.PhotosListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.PhotosListInterface;
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

public class PhotosListActivity extends AppCompatActivity {

    @BindView(R.id.rv_photos_list)
    RecyclerView mRvPhotosList;
    @BindView(R.id.swipe_photos_list)
    SwipeRefreshLayout mSwipePhotosList;
    private PhotosListAdapter photosListAdapter;
    private List<PhotosModel> photosModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_photos_list);
        ButterKnife.bind(this);
        initializeViews();
        getPhotosList();
        String getActivityName = getIntent().getStringExtra("ActivityName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);

    }

    private void initializeViews() {
        mRvPhotosList.setLayoutManager(new LinearLayoutManager(this));
        mRvPhotosList.setHasFixedSize(true);
        mSwipePhotosList.setOnRefreshListener(this::getPhotosList);
    }

    private void getPhotosList() {
        mSwipePhotosList.setRefreshing(true);

        Call<List<PhotosModel>> call = APIControl.getRetrofit().create(PhotosListInterface.class).getPhotosList();
        call.enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<PhotosModel>> call, @NotNull Response<List<PhotosModel>> response) {
                mSwipePhotosList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    photosModels = new ArrayList<>(response.body());
                    photosListAdapter = new PhotosListAdapter(PhotosListActivity.this, photosModels);
                    mRvPhotosList.setAdapter(photosListAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<PhotosModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(PhotosListActivity.this)) {
                    mSwipePhotosList.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipePhotosList.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
