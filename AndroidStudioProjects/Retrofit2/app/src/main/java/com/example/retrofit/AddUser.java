package com.example.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    Button btn;
    private static final String TAG = "AddUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("Registering...");
                String userName=username.getText().toString();
                String userEmail=email.getText().toString();
                String userPassword=password.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    username.setError("Username field is required");
                    username.requestFocus();
                }
                else if(TextUtils.isEmpty(userEmail)){
                    email.setError("Email field is required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(userPassword)){
                    password.setError("Password field is required");
                    password.requestFocus();
                }
                else{
                    RegisterUser registerUser=new RegisterUser();
                    registerUser.setEmail(userEmail);
                    registerUser.setUsername(userName);
                    registerUser.setPassword(userPassword);
                    AddUserInterface addUserInterface=RetrofitClient.getRetrofitInstance().create(AddUserInterface.class);
                    Call<AddResponse> call=addUserInterface.addUser(registerUser);
                    call.enqueue(new Callback<AddResponse>() {
                        @Override
                        public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                            if(response.isSuccessful()){
                                username.setText("");
                                email.setText("");
                                password.setText("");
                                Intent intent=new Intent(AddUser.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(AddUser.this, response.body().success, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(AddUser.this, "User with that account alraedy exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddResponse> call, Throwable t) {
                            Log.d(TAG, "API call failed: " + t.getMessage());
                            Toast.makeText(AddUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}