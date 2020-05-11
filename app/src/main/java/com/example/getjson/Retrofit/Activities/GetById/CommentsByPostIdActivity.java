package com.example.getjson.Retrofit.Activities.GetById;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.CommentsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.CommentsByPostIdInterface;
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

public class CommentsByPostIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_comments_postid)
    RecyclerView mRvCommentsPostid;

    private List<CommentsModel> comList;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_comments_by_postid);
        ButterKnife.bind(this);
        buildRecycleView();
        getCommentsByPostid();
    }

    private void getCommentsByPostid() {
        int getId = Integer.parseInt(getIntent().getStringExtra("GetId"));
        Call<List<CommentsModel>> call = APIControl.getRetrofit().create(CommentsByPostIdInterface.class).getComments(getId);
        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                comList = new ArrayList<>(response.body());
                commentsAdapter = new CommentsAdapter(CommentsByPostIdActivity.this, comList);
                mRvCommentsPostid.setAdapter(commentsAdapter);
                Toast.makeText(CommentsByPostIdActivity.this, "Comments By Post Id: " + getId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Toast.makeText(CommentsByPostIdActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecycleView() {
        mRvCommentsPostid.setHasFixedSize(true);
        mRvCommentsPostid.setLayoutManager(new LinearLayoutManager(this));
    }
}
