package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.UsersAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.UsersInterface;
import com.example.getjson.Retrofit.Models.UsersModel;

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

public class UsersListActivity extends AppCompatActivity {


    @BindView(R.id.rv_user_list)
    RecyclerView mRvUserList;
    @BindView(R.id.swipe_users_list)
    SwipeRefreshLayout mSwipeUsersList;
    private UsersAdapter mUsersAdapter;
    private ArrayList<UsersModel> mUsersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_users_list);
        ButterKnife.bind(this);
        initializeViews();
        getUsers();
        String getActivityName = getIntent().getStringExtra("usersActivity");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);
    }

    private void initializeViews() {
        mRvUserList.setLayoutManager(new LinearLayoutManager(this));
        mRvUserList.setHasFixedSize(true);
        mSwipeUsersList.setOnRefreshListener(this::getUsers);
    }

    private void getUsers() {
        mSwipeUsersList.setRefreshing(true);
        Call<List<UsersModel>> listCall = APIControl.getRetrofit().create(UsersInterface.class).getUsers();
        listCall.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<UsersModel>> call, @NotNull Response<List<UsersModel>> response) {
                mSwipeUsersList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    mUsersList = new ArrayList<>(response.body());
                    mUsersAdapter = new UsersAdapter(UsersListActivity.this, mUsersList);
                    mRvUserList.setAdapter(mUsersAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<UsersModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(UsersListActivity.this)) {
                    mSwipeUsersList.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeUsersList.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
