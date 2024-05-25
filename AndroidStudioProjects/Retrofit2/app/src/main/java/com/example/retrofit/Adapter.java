package com.example.retrofit;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<userData> userDataList;
    public Adapter(List<userData> userDataList){
        this.userDataList=userDataList;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.users,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String user=userDataList.get(position).getUsername();
        String userEmail=userDataList.get(position).getEmail();
        String userId=userDataList.get(position).getId();
        holder.setData(user,userEmail,userId);
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,username,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            username=itemView.findViewById(R.id.username);
            email=itemView.findViewById(R.id.email);
        }

        public void setData(String user, String userEmail, String userId) {
            id.setText(userId);
            username.setText(user);
            email.setText(userEmail);
        }
    }
}
