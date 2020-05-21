package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.PostsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.PostsInterface;
import com.example.getjson.Retrofit.Models.PostsModel;

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

public class PostsListActivity extends AppCompatActivity {
    @BindView(R.id.recycleView_Posts)
    RecyclerView mRecyclerView;
    @BindView(R.id.swip_posts_list)
    SwipeRefreshLayout mSwipPostsList;
    private PostsAdapter mPostAdapter;
    private ArrayList<PostsModel> mPostsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_posts_list);
        ButterKnife.bind(this);
        initializeViews();
        getPosts();
        String getActivityName = getIntent().getStringExtra("PostsActivity");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);
    }


    private void initializeViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipPostsList.setOnRefreshListener(this::getPosts);
    }

    private void getPosts() {
        mSwipPostsList.setRefreshing(true);
        Call<List<PostsModel>> call = APIControl.getRetrofit().create(PostsInterface.class).getPosts();
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<PostsModel>> call, @NotNull Response<List<PostsModel>> response) {
                mSwipPostsList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    mPostsList = new ArrayList<>(response.body());
                    mPostAdapter = new PostsAdapter(PostsListActivity.this, mPostsList);
                    mRecyclerView.setAdapter(mPostAdapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<List<PostsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(PostsListActivity.this)) {
                    mSwipPostsList.setRefreshing(false);
                    message("Internet isn't Connection!");
                }else {
                    mSwipPostsList.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
