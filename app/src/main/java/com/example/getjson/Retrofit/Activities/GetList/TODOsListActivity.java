package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.APIControl.CheckConnection;
import com.example.getjson.Retrofit.Adapters.TODOsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.TODOsListInterface;
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

public class TODOsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_todos_list)
    RecyclerView mRvTodosList;
    @BindView(R.id.swipe_todos_list)
    SwipeRefreshLayout mSwipeTodosList;
    private TODOsListAdapter todOsListAdapter;
    private List<TODOsModel> todOsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_todos_list);
        ButterKnife.bind(this);
        initializeVeiws();
        getTodosList();
        String getActivityName = getIntent().getStringExtra("ActivityName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName);
    }

    private void initializeVeiws() {
        mRvTodosList.setLayoutManager(new LinearLayoutManager(this));
        mRvTodosList.setHasFixedSize(true);
        mSwipeTodosList.setOnRefreshListener(this::getTodosList);
    }

    private void getTodosList() {
        mSwipeTodosList.setRefreshing(true);

        Call<List<TODOsModel>> call = APIControl.getRetrofit().create(TODOsListInterface.class).getTODOsList();
        call.enqueue(new Callback<List<TODOsModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<TODOsModel>> call, @NotNull Response<List<TODOsModel>> response) {
                mSwipeTodosList.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    todOsModels = new ArrayList<>(response.body());
                    todOsListAdapter = new TODOsListAdapter(TODOsListActivity.this, todOsModels);
                    mRvTodosList.setAdapter(todOsListAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<TODOsModel>> call, @NotNull Throwable t) {
                if (!CheckConnection.isConnection(TODOsListActivity.this)) {
                    mSwipeTodosList.setRefreshing(false);
                    message("Internet Isn't Connection!");
                } else {
                    mSwipeTodosList.setRefreshing(false);
                    message("Error Message: " + t.getLocalizedMessage());
                }
            }
        });
    }
    void message(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
