/*
*   @Copyright Hussein Mohamed
*   Uploaded at 11/5/2020
*   Email: memailbusiness@gmail.com
*/
package com.example.getjson.Retrofit.Activities.GetById;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.PostsAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.PostsByUserIdInterface;
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

public class PostsByUserIdActivity extends AppCompatActivity {


    @BindView(R.id.rv_UsersId)
    RecyclerView rvUsersId;
    private PostsAdapter userIdPostsAdapter;
    private List<PostsModel> postsByUserIdModelList;
    EditText mEtGetUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_posts_userid);
        ButterKnife.bind(this);
        initialize();
        getPostsByUserId();
    }

    private void initialize() {
        mEtGetUserId = findViewById(R.id.et_getposts_byuserid);
        rvUsersId.setHasFixedSize(true);
        rvUsersId.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getPostsByUserId() {
        int getUserId = Integer.parseInt(getIntent().getStringExtra("userid"));
        Call<List<PostsModel>> call = APIControl.getRetrofit().create(PostsByUserIdInterface.class).getPostsByUserId(getUserId);
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                postsByUserIdModelList = new ArrayList<>(response.body());
                userIdPostsAdapter = new PostsAdapter(PostsByUserIdActivity.this, postsByUserIdModelList);
                rvUsersId.setAdapter(userIdPostsAdapter);
                Toast.makeText(PostsByUserIdActivity.this, "Posts By UserID: " + getUserId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(PostsByUserIdActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
