package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.CommentsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.CommentsInterface;
import com.example.getjson.Retrofit.Models.CommentsModel;

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

public class CommentsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_Comments)
    RecyclerView mRvComments;
    private CommentsAdapter mCommentsAdapter;
    private ArrayList<CommentsModel> mCMList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_comments_list);
        ButterKnife.bind(this);
        buildRecycView();
        getComments();

    }

    private void buildRecycView() {
        mRvComments.setHasFixedSize(true);
        mRvComments.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getComments() {
        Call<List<CommentsModel>> listCall = APIControl.getRetrofit().create(CommentsInterface.class).getComments(APIControl.BASE_URL + "comments");
        listCall.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                mCMList = new ArrayList<>(response.body());
                mCommentsAdapter = new CommentsAdapter(CommentsListActivity.this, mCMList);
                mRvComments.setAdapter(mCommentsAdapter);
                Intent intent = getIntent();
                String getActivityName = intent.getExtras().getString("CommentsActivity");
                Toast.makeText(CommentsListActivity.this, "Activity: " + getActivityName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Toast.makeText(CommentsListActivity.this, "Error Message : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
