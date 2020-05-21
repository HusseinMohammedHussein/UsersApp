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
import com.example.getjson.Retrofit.Adapters.PostsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.PostsByUserIdInterface;
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

public class PostsByUserIdActivity extends AppCompatActivity {


    @BindView(R.id.rv_posts_byUsersId)
    RecyclerView mRvPostsByUsersId;
    @BindView(R.id.swipe_posts_byUserId)
    SwipeRefreshLayout mSwipePostsByUserId;
    private PostsAdapter userIdPostsAdapter;
    private List<PostsModel> postsByUserIdModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_posts_userid);
        ButterKnife.bind(this);
        initializeViews();
        getPostsByUserId();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Posts By UserId = "+ "("+getIntent().getStringExtra("userid") +")");
    }

    private void initializeViews() {
        mRvPostsByUsersId.setHasFixedSize(true);
        mRvPostsByUsersId.setLayoutManager(new LinearLayoutManager(this));
        mSwipePostsByUserId.setOnRefreshListener(this::getPostsByUserId);
    }

    private void getPostsByUserId() {
        mSwipePostsByUserId.setRefreshing(true);
        int getUserId = Integer.parseInt(getIntent().getStringExtra("userid"));

        Call<List<PostsModel>> call = APIControl.getRetrofit().create(PostsByUserIdInterface.class).getPostsByUserId(getUserId);
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<PostsModel>> call, @NotNull Response<List<PostsModel>> response) {
                mSwipePostsByUserId.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    postsByUserIdModelList = new ArrayList<>(response.body());
                    userIdPostsAdapter = new PostsAdapter(PostsByUserIdActivity.this, postsByUserIdModelList);
                    mRvPostsByUsersId.setAdapter(userIdPostsAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<PostsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(PostsByUserIdActivity.this)) {
                    mSwipePostsByUserId.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipePostsByUserId.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
