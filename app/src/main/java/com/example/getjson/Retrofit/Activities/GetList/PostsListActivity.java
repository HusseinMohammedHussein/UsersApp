package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.PostsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.PostsInterface;
import com.example.getjson.Retrofit.Models.PostsModel;

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

public class PostsListActivity extends AppCompatActivity {
    @BindView(R.id.recycleView_Posts)
    RecyclerView mRecyclerView;
    private PostsAdapter mPostAdapter;
    private ArrayList<PostsModel> mPostsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_posts_list);
        ButterKnife.bind(this);
        initializeViews();
        getPosts();


    }


    private void getPosts() {
        Call<List<PostsModel>> call = APIControl.getRetrofit().create(PostsInterface.class).getPosts();
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                mPostsList = new ArrayList<>(response.body());
                mPostAdapter = new PostsAdapter(PostsListActivity.this, mPostsList);
                mRecyclerView.setAdapter(mPostAdapter);
                Intent intent = getIntent();
                String getActivityName = intent.getExtras().getString("PostsActivity");
                Toast.makeText(PostsListActivity.this, "Activity: " + getActivityName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(PostsListActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initializeViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
