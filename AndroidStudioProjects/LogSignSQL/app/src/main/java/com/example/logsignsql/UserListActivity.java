package com.example.logsignsql;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {

    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_layout);

        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Exemple de liste d'utilisateurs (à remplacer par votre propre liste)
        List<User> userList = generateUserList();

        userAdapter = new UserAdapter(userList);
        userRecyclerView.setAdapter(userAdapter);
    }

    // Méthode de génération de la liste d'utilisateurs (à remplacer par votre propre logique)
    private List<User> generateUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1@example.com", "password1"));

        userList.add(new User( "user2@example.com", "password2"));
        // Ajoutez d'autres utilisateurs selon vos besoins
        return userList;
    }

    @Override
    public void onEditClick(int position) {
        // Implémentez la logique de modification ici
        Toast.makeText(this, "Modifier l'utilisateur à la position : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        // Implémentez la logique de suppression ici
        Toast.makeText(this, "Supprimer l'utilisateur à la position : " + position, Toast.LENGTH_SHORT).show();
    }
}

