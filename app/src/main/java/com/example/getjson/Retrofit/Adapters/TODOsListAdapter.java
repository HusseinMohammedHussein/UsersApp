package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.TODOsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TODOsListAdapter extends RecyclerView.Adapter<TODOsListAdapter.TODOsListViewHolder> {
    private Context mCon;
    private List<TODOsModel> todosModels;

    public TODOsListAdapter(Context mCon, List<TODOsModel> todosModels) {
        this.mCon = mCon;
        this.todosModels = todosModels;
    }

    @NonNull
    @Override
    public TODOsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.card_todos_list, parent, false);
        return new TODOsListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TODOsListViewHolder holder, int position) {
        TODOsModel getTodo = todosModels.get(position);
        holder.tvTodoUserId.setText("User Id:" + getTodo.getmUserId());
        holder.tvTodoId.setText("Id:" + getTodo.getmId());
        holder.tvTodoTitle.setText("Title:" + getTodo.getmTitle());
        holder.tvTodoCompleted.setText("Completed:" + getTodo.getmCompleted());

    }

    @Override
    public int getItemCount() {
        return todosModels.size();
    }

    public static class TODOsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_todo_userId)
        TextView tvTodoUserId;
        @BindView(R.id.tv_todo_id)
        TextView tvTodoId;
        @BindView(R.id.tv_todo_title)
        TextView tvTodoTitle;
        @BindView(R.id.tv_todo_completed)
        TextView tvTodoCompleted;

        public TODOsListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
