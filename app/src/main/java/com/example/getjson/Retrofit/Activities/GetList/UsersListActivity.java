package com.example.getjson.Retrofit.Activities.GetList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.UsersAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.UsersInterface;
import com.example.getjson.Retrofit.Models.UsersModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersListActivity extends AppCompatActivity {


    private RecyclerView mRecycle;
    private UsersAdapter mUsersAdapter;
    private ArrayList<UsersModel> mUsersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_users_list);
        buildRecycleView();
        getUsers();

    }

    private void buildRecycleView() {
        mRecycle = findViewById(R.id.rvUserData);
        mRecycle.setHasFixedSize(true);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getUsers() {
        Call<List<UsersModel>> listCall = APIControl.getRetrofit().create(UsersInterface.class).getUsers();
        listCall.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                mUsersList = new ArrayList<>(response.body());
                mUsersAdapter = new UsersAdapter(UsersListActivity.this, mUsersList);
                mRecycle.setAdapter(mUsersAdapter);
                Intent intent = getIntent();
                String showText = intent.getExtras().getString("usersActivity");
                Toast.makeText(UsersListActivity.this, "Activity: " + showText, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {
                Toast.makeText(UsersListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
