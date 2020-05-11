package com.example.getjson.Retrofit.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.ShimmerFacebookAdapter;
import com.example.getjson.Retrofit.Interfaces.ShimmerFacebookInterface;
import com.example.getjson.Retrofit.Models.PostsModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShimmerFacebookRV extends AppCompatActivity {


    @BindView(R.id.rv_shimmer_facebook)
    RecyclerView mRVShimmer;
    @BindView(R.id.shimmer_rv)
    ShimmerFrameLayout mShimmer;
    private ShimmerFacebookAdapter shimmerFacebookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_shimmer_facebook);
        ButterKnife.bind(this);
        buildRecycleView();
        getUsers();

    }

    private void buildRecycleView() {
        mRVShimmer.setHasFixedSize(true);
        mRVShimmer.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mShimmer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmer.stopShimmerAnimation();

    }

    private void getUsers() {

        Call<List<PostsModel>> call = APIControl.getRetrofitWithShimmer().create(ShimmerFacebookInterface.class).getPosts();
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                List<PostsModel> postList = new ArrayList<>(response.body());
                shimmerFacebookAdapter = new ShimmerFacebookAdapter(ShimmerFacebookRV.this, postList);
                mShimmer.stopShimmerAnimation();
                mShimmer.setVisibility(View.GONE);
                mRVShimmer.setVisibility(View.VISIBLE);
                mRVShimmer.setAdapter(shimmerFacebookAdapter);
                Toast.makeText(ShimmerFacebookRV.this, "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(ShimmerFacebookRV.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
