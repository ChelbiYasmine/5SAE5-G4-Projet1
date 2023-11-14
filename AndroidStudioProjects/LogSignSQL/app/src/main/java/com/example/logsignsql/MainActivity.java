package com.example.logsignsql;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_layout);

        userList = new ArrayList<>(); // Vous devez remplir votre liste avec les données appropriées.

        // Initialize RecyclerView and set the adapter
//        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        userAdapter = new UserAdapter(userList);
//        recyclerView.setAdapter(userAdapter);
    }
}
