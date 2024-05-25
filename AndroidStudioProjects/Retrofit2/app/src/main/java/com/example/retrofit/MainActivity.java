package com.example.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    List<userData> userDataList;

    TextView textView;
    ProgressBar progressBar;

    Button button;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        progressBar=findViewById(R.id.progress);
        button=findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddUser.class);
                startActivity(intent);
                finish();
            }
        });


        userDataList = new ArrayList<>();
        initRecyclerView();

            ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
            Call<User> call = apiInterface.getUserData();

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<userData> fetchedUsers = response.body().getUsers();
                        userDataList.clear();
                        userDataList.addAll(fetchedUsers);
                        progressBar.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "Response unsuccessful or body is null");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d(TAG, "API call failed: " + t.getMessage());
                }
            });


    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(userDataList);
        recyclerView.setAdapter(adapter);
    }


}
