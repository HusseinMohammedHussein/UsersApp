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
import com.example.getjson.Retrofit.Adapters.TODOsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.TODOsByUserIdInterface;
import com.example.getjson.Retrofit.Models.TODOsModel;

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

public class TODOsByUserIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_todos_byUserId)
    RecyclerView mRvTodosByUserid;
    @BindView(R.id.swipe_todos_byUserId)
    SwipeRefreshLayout mSwipeTodosByUserId;
    private TODOsListAdapter todosAdapter;
    private List<TODOsModel> todosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_todos_userid);
        ButterKnife.bind(this);
        buildRecycleVew();
        getTODOsByUserId();
        Objects.requireNonNull(getSupportActionBar()).setTitle("TODOs By UserId = " + "(" + getIntent().getStringExtra("getuserId") + ")");
    }

    private void buildRecycleVew() {
        mRvTodosByUserid.setHasFixedSize(true);
        mRvTodosByUserid.setLayoutManager(new LinearLayoutManager(this));
        mSwipeTodosByUserId.setOnRefreshListener(this::getTODOsByUserId);
    }

    private void getTODOsByUserId() {
        mSwipeTodosByUserId.setRefreshing(true);
        String getId = getIntent().getStringExtra("getuserId");
        Call<List<TODOsModel>> call = APIControl.getRetrofit().create(TODOsByUserIdInterface.class).getTODOsByUserId("users/" + getId + "/todos");
        call.enqueue(new Callback<List<TODOsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<TODOsModel>> call, @NotNull Response<List<TODOsModel>> response) {
                mSwipeTodosByUserId.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    todosList = new ArrayList<>(response.body());
                    todosAdapter = new TODOsListAdapter(TODOsByUserIdActivity.this, todosList);
                    mRvTodosByUserid.setAdapter(todosAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<TODOsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(TODOsByUserIdActivity.this)) {
                    mSwipeTodosByUserId.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeTodosByUserId.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }

    void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
