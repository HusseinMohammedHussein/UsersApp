package com.example.getjson.Retrofit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getjson.R;
import com.example.getjson.Retrofit.Models.UsersModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private ArrayList<UsersModel> mUsersList;
    private Context mContext;

    public UsersAdapter(Context context, ArrayList<UsersModel> usersList) {
        this.mContext = context;
        this.mUsersList = usersList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_users_list, parent, false);
        return new UsersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UsersModel usersPosition = mUsersList.get(position);
        holder.mTvIDUsers.setText("ID: " + usersPosition.getmId());
        holder.mTvNameUsers.setText("Name: " + usersPosition.getmName());
        holder.mTvUsernameUsers.setText("Username: " + usersPosition.getmUsername());
        holder.mTvEmailUsers.setText("Email: " + usersPosition.getmEmail());
        holder.mTvPhoneUsers.setText("Phone" + usersPosition.getmPhone());
        holder.mTvAddressUsers.setText(
                "-Address: " + "\n" +
                        " Street:   " + usersPosition.getmAddress().getmStreet()     + "\n" +
                        " Suite:    " + usersPosition.getmAddress().getmSuite()      + "\n" +
                        " City:     " + usersPosition.getmAddress().getmCity()        + "\n" +
                "-Geo: " + "\n" +
                        " Lat:  " + usersPosition.getmAddress().getmGeo().getmLat() + "\n" +
                        " Lng:  " + usersPosition.getmAddress().getmGeo().getmLng() + "\n"
        );
        holder.mTvCompanyUsers.setText(
                "-Company: " + "\n"+
                        "Name: "         + usersPosition.getmCompany().getmName()           + "\n"+
                        "Catch Phrase: " + usersPosition.getmCompany().getmCatchPhrase()    +"\n"+
                        "Bs: "           + usersPosition.getmCompany().getmBs()             + "\n"
        );
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ID_users)
        TextView mTvIDUsers;
        @BindView(R.id.tv_Username_users)
        TextView mTvUsernameUsers;
        @BindView(R.id.tv_Name_users)
        TextView mTvNameUsers;
        @BindView(R.id.tv_Email_users)
        TextView mTvEmailUsers;
        @BindView(R.id.tv_Phone_users)
        TextView mTvPhoneUsers;
        @BindView(R.id.tv_Address_users)
        TextView mTvAddressUsers;
        @BindView(R.id.tv_company_users)
        TextView mTvCompanyUsers;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
