package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.AlbumsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.AlbumsListInterface;
import com.example.getjson.Retrofit.Models.AlbumsModel;

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

public class AlbumsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_albumsList)
    RecyclerView rvAlbumsList;

    private AlbumsListAdapter albumsListAdapter;
    private List<AlbumsModel> albumsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_albums_list);
        ButterKnife.bind(this);
        buildRecycleView();
        getAlbumsList();
    }

    private void getAlbumsList() {
        String intent = getIntent().getStringExtra("ActivityName");
        Call<List<AlbumsModel>> call = APIControl.getRetrofit().create(AlbumsListInterface.class).getAlbumsList();
        call.enqueue(new Callback<List<AlbumsModel>>() {
            @Override
            public void onResponse(Call<List<AlbumsModel>> call, Response<List<AlbumsModel>> response) {
                albumsList = new ArrayList<>(response.body());
                albumsListAdapter = new AlbumsListAdapter(AlbumsListActivity.this, albumsList);
                rvAlbumsList.setAdapter(albumsListAdapter);
                Toast.makeText(AlbumsListActivity.this, "Activity: " + intent, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<AlbumsModel>> call, Throwable t) {
                Toast.makeText(AlbumsListActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecycleView() {
        rvAlbumsList.setHasFixedSize(true);
        rvAlbumsList.setLayoutManager(new LinearLayoutManager(this));
    }

}
