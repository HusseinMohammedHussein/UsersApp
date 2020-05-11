package com.example.getjson.Retrofit.Activities.GetById;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.AlbumsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.AlbumsByUserIdInterface;
import com.example.getjson.Retrofit.Models.AlbumsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsByUserIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_albums_userid)
    RecyclerView mRvAlbumsUserid;
    private List<AlbumsModel> albumsList;
    private AlbumsListAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_albums_by_userid);
        ButterKnife.bind(this);
        buildRecycleView();
        getAlbumsByUserId();
    }

    private void getAlbumsByUserId() {

        int getId = Integer.parseInt(getIntent().getStringExtra("getId"));
        Map<String, String> getParameters = new HashMap<>();
        getParameters.put("userId", String.valueOf(getId));
        getParameters.put("_sort", "id");
        getParameters.put("_order", "desc");
        Call<List<AlbumsModel>> call = APIControl.getRetrofit().create(AlbumsByUserIdInterface.class).getAlbumsByUserId(getParameters);
        call.enqueue(new Callback<List<AlbumsModel>>() {
            @Override
            public void onResponse(Call<List<AlbumsModel>> call, Response<List<AlbumsModel>> response) {
                albumsList = new ArrayList<>(response.body());
                albumsAdapter = new AlbumsListAdapter(AlbumsByUserIdActivity.this, albumsList);
                mRvAlbumsUserid.setAdapter(albumsAdapter);
                Toast.makeText(AlbumsByUserIdActivity.this, "Albums For UserId: " + getId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AlbumsModel>> call, Throwable t) {
                Toast.makeText(AlbumsByUserIdActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecycleView() {
        mRvAlbumsUserid.setHasFixedSize(true);
        mRvAlbumsUserid.setLayoutManager(new LinearLayoutManager(this));
    }
}
