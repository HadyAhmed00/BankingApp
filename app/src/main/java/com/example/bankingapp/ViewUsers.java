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
            , R.drawable.ava_5};

    RecyclerView userRc;
    ArrayList<User> users =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);


        /*User u ;



        for (int i = 0 ; i<10; i++){
           u = new User("Hady Ahmed","0123456789", "hady@gmail.com",ava[i%4],1030+10*i);
           users.add(u);


        }*/
        users = new DataBase(this).readUsersData();

        userRc = findViewById(R.id.users_list);
        UsersListAdapter a = new UsersListAdapter(users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(userRc.getContext(), LinearLayoutManager.VERTICAL, false);
        userRc.setLayoutManager(layoutManager);
        userRc.setAdapter(a);
        a.setonItemclickListener(ViewUsers.this);
    }

    @Override
    public void onItemclick(int position) {
        Intent user_dital = new Intent(this,UserView.class);

        User chosen = users.get(position);
        user_dital.putExtra(userName,   chosen.getName());
        user_dital.putExtra(userId,   chosen.id);
        user_dital.putExtra(userEmail,  chosen.getEmile());
        user_dital.putExtra(userPhone,  chosen.getPhone_number());
        user_dital.putExtra(userPhoto,  chosen.getPhoto());
        user_dital.putExtra(userBalance,chosen.getBalace());
        user_dital.putExtra(userBalance,chosen.getBalace());

        startActivity(user_dital);
    }


}