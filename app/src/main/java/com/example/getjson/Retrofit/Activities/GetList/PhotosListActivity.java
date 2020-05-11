package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.PhotosListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.PhotosListInterface;
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

public class PhotosListActivity extends AppCompatActivity {

    @BindView(R.id.rv_photos_list)
    RecyclerView rvPhotosList;
    private PhotosListAdapter photosListAdapter;
    private List<PhotosModel> photosModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_photos_list);
        ButterKnife.bind(this);

        buildRecycleVeiw();
        getPhotosList();

    }

    private void getPhotosList() {
        String activityName = getIntent().getStringExtra("ActivityName");
        Call<List<PhotosModel>> call = APIControl.getRetrofit().create(PhotosListInterface.class).getPhotosList();
        call.enqueue(new Callback<List<PhotosModel>>() {
            @Override
            public void onResponse(Call<List<PhotosModel>> call, Response<List<PhotosModel>> response) {
                photosModels = new ArrayList<>(response.body());
                photosListAdapter = new PhotosListAdapter(PhotosListActivity.this, photosModels);
                rvPhotosList.setAdapter(photosListAdapter);
                Toast.makeText(PhotosListActivity.this, "Activity: " + activityName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PhotosModel>> call, Throwable t) {
                Toast.makeText(PhotosListActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecycleVeiw() {
        rvPhotosList.setHasFixedSize(true);
        rvPhotosList.setLayoutManager(new LinearLayoutManager(this));
    }


}
