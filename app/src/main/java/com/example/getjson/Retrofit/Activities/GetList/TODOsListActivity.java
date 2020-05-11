package com.example.getjson.Retrofit.Activities.GetList;

import android.os.Bundle;
import android.widget.Toast;

import com.example.getjson.R;
import com.example.getjson.Retrofit.APIControl.APIControl;
import com.example.getjson.Retrofit.Adapters.TODOsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetList.TODOsListInterface;
import com.example.getjson.Retrofit.Models.TODOsModel;

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

public class TODOsListActivity extends AppCompatActivity {

    @BindView(R.id.rv_todos_list)
    RecyclerView rvTodosList;
    private TODOsListAdapter todOsListAdapter;
    private List<TODOsModel> todOsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_todos_list);
        ButterKnife.bind(this);
        buildRecycleView();
        getTodosList();

    }

    private void getTodosList() {

        String activityName = getIntent().getStringExtra("ActivityName");
        Call<List<TODOsModel>> call = APIControl.getRetrofit().create(TODOsListInterface.class).getTODOsList();
        call.enqueue(new Callback<List<TODOsModel>>() {
            @Override
            public void onResponse(Call<List<TODOsModel>> call, Response<List<TODOsModel>> response) {
                todOsModels = new ArrayList<>(response.body());
                todOsListAdapter = new TODOsListAdapter(TODOsListActivity.this, todOsModels);
                rvTodosList.setAdapter(todOsListAdapter);
                Toast.makeText(TODOsListActivity.this, "Activity Name :" + activityName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<TODOsModel>> call, Throwable t) {
                Toast.makeText(TODOsListActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecycleView() {
        rvTodosList.setHasFixedSize(true);
        rvTodosList.setLayoutManager(new LinearLayoutManager(this));
    }

}
