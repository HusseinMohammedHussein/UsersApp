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
import com.example.getjson.Retrofit.Adapters.TODOsListAdapter;
import com.example.getjson.Retrofit.Interfaces.GetById.TODOsByUserIdInterface;
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

public class TODOsByUserIdActivity extends AppCompatActivity {

    @BindView(R.id.rv_todos_by_userid)
    RecyclerView mRvTodosByUserid;
    private TODOsListAdapter todosAdapter;
    private List<TODOsModel> todosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_todos_userid);
        ButterKnife.bind(this);
        buildRecycleVew();
        getTODOsByUserId();
    }

    private void buildRecycleVew() {
        mRvTodosByUserid.setHasFixedSize(true);
        mRvTodosByUserid.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getTODOsByUserId() {
        String getId = getIntent().getStringExtra("getuserId");
        Call<List<TODOsModel>> call = APIControl.getRetrofit().create(TODOsByUserIdInterface.class).getTODOsByUserId("users/" + getId + "/todos");
        call.enqueue(new Callback<List<TODOsModel>>() {
            @Override
            public void onResponse(Call<List<TODOsModel>> call, Response<List<TODOsModel>> response) {
                todosList = new ArrayList<>(response.body());
                todosAdapter = new TODOsListAdapter(TODOsByUserIdActivity.this, todosList);
                mRvTodosByUserid.setAdapter(todosAdapter);
                Toast.makeText(TODOsByUserIdActivity.this, "TODOs By UserId: " + getId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<TODOsModel>> call, Throwable t) {
                Toast.makeText(TODOsByUserIdActivity.this, "Message Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
