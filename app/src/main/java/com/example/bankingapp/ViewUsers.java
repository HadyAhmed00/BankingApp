package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.AutofillService;

import java.util.ArrayList;

public class ViewUsers extends AppCompatActivity implements UsersListAdapter.OnItemClickListener {

    public static final String userName = "name";
    public static final String userId = "id";
    public static final String userEmail = "email";
    public static final String userPhone = "phone";
    public static final String userBalance = "balance";
    public static final String userPhoto = "photo";

    public static int [] ava = {R.drawable.ava_1
            , R.drawable.ava_2
            , R.drawable.ava_3
            , R.drawable.ava_4
            , R.drawable.ava_5
            , R.drawable.ava_6

            , R.drawable.ava_7

            , R.drawable.ava_8

            , R.drawable.ava_9

            , R.drawable.ava_10};

    RecyclerView userRc;
    ArrayList<User> users =new ArrayList<>();
    UsersListAdapter a;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        userRc = findViewById(R.id.users_list);
        fillRc();

    }
    public  void fillRc (){
        users = new DataBase(this).readUsersData();
        a = new UsersListAdapter(users);
        layoutManager = new LinearLayoutManager(userRc.getContext(), LinearLayoutManager.VERTICAL, false);
        userRc.setLayoutManager(layoutManager);
        userRc.setAdapter(a);
        a.setonItemclickListener(ViewUsers.this);
    }

    @Override
    public void onItemclick(int position) {
        Intent user_dital = new Intent(this,UserView.class);
        User chosen = users.get(position);
        user_dital.putExtra(userId,   chosen.id);
        startActivity(user_dital);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillRc();
    }
}