package edu.bluejack22_2.agocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.bluejack22_2.agocar.models.User;

public class HomeActivity extends AppCompatActivity {
    public static User user = null;
    private TextView tvGreetings;


    void authenticateUser(){
        Gson gson = new Gson();
        SharedPreferences mPrefs = getSharedPreferences("userPref", Context.MODE_PRIVATE);
        String json = mPrefs.getString("user", "");
        user = gson.fromJson(json, User.class);

        if(user == null){
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    void getComponents(){
        this.tvGreetings = findViewById(R.id.tvGreetings);
    }

    void setComponents(){
        tvGreetings.setText("Hello, "+this.user.getUsername()+ " !");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        authenticateUser();
        getComponents();

        setComponents();
    }
}