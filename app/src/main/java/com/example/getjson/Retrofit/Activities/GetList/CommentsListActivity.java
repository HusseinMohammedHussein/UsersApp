/*
 *   @Copyright Hussein Mohamed
 *   Uploaded at 11/5/2020
 *   Email: memailbusiness@gmail.com
 *   GitHub: https://github.com/HusseinMohammedHussein
 */
package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.CommentsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.CommentsInterface;
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

public class CommentsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_Comments)
    RecyclerView mRvComments;
    @BindView(R.id.swipe_comments_list)
    SwipeRefreshLayout mSwipCommentsList;
    private CommentsAdapter mCommentsAdapter;
    private ArrayList<CommentsModel> mCMList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_comments_list);
        ButterKnife.bind(this);
        initialization();
        getComments();
        String getActivityName = getIntent().getStringExtra("CommentsActivity");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);

    }

    private void initialization() {
        mRvComments.setHasFixedSize(true);
        mRvComments.setLayoutManager(new LinearLayoutManager(this));
        mSwipCommentsList.setOnRefreshListener(this::getComments);
    }

    private void getComments() {
        mSwipCommentsList.setRefreshing(true);
        Call<List<CommentsModel>> listCall = APIControl.getRetrofit().create(CommentsInterface.class).getComments(APIControl.BASE_URL + "comments");
        listCall.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CommentsModel>> call, @NotNull Response<List<CommentsModel>> response) {
                mSwipCommentsList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    mCMList = new ArrayList<>(response.body());
                    mCommentsAdapter = new CommentsAdapter(CommentsListActivity.this, mCMList);
                    mRvComments.setAdapter(mCommentsAdapter);
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<CommentsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(CommentsListActivity.this)) {
                    mSwipCommentsList.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipCommentsList.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }
    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
