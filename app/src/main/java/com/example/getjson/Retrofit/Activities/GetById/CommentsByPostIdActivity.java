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
import com.example.getjson.Retrofit.Adapters.CommentsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.CommentsByPostIdInterface;
import com.example.getjson.Retrofit.Models.CommentsModel;

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

public class CommentsByPostIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_comments_byPostId)
    RecyclerView mRvCommentsPostId;
    @BindView(R.id.swipe_comments_byPostId)
    SwipeRefreshLayout mSwipeCommentsByPostId;
    private List<CommentsModel> comList;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_comments_by_postid);
        ButterKnife.bind(this);
        buildRecycleView();
        getCommentsByPostid();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Comments By PostId = " + "("+getIntent().getStringExtra("GetId") +")");
    }

    private void buildRecycleView() {
        mRvCommentsPostId.setHasFixedSize(true);
        mRvCommentsPostId.setLayoutManager(new LinearLayoutManager(this));
        mSwipeCommentsByPostId.setOnRefreshListener(this::getCommentsByPostid);
    }

    private void getCommentsByPostid() {
        mSwipeCommentsByPostId.setRefreshing(true);
        int getId = Integer.parseInt(getIntent().getStringExtra("GetId"));
        Call<List<CommentsModel>> call = APIControl.getRetrofit().create(CommentsByPostIdInterface.class).getComments(getId);
        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CommentsModel>> call, @NotNull Response<List<CommentsModel>> response) {
                mSwipeCommentsByPostId.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    comList = new ArrayList<>(response.body());
                    commentsAdapter = new CommentsAdapter(CommentsByPostIdActivity.this, comList);
                    mRvCommentsPostId.setAdapter(commentsAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<CommentsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(CommentsByPostIdActivity.this)) {
                    mSwipeCommentsByPostId.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeCommentsByPostId.setRefreshing(false);
                    message("Message Error: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
