package com.example.bankingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ToUser extends AppCompatActivity implements ToUserAdapter.OnItemClickListener {

    RecyclerView toUser;
    ToUserAdapter adapter ;
    ArrayList<User> users ;

    int src_Id , dst_Id;
    float amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_user);
        Intent intent = getIntent();

        src_Id = Integer.parseInt(intent.getStringExtra("SrcId"));
        amount =  intent.getFloatExtra("Amount",0);

        toUser = findViewById(R.id.to_user_rc);
        users = new ArrayList<>( );
        users = new DataBase(this).readUsersData();
        adapter = new ToUserAdapter(users);
        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(toUser.getContext(),RecyclerView.VERTICAL,false);
        toUser.setAdapter(adapter);
        toUser.setLayoutManager(layoutManager);
        adapter.setonItemclickListener(ToUser.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemclick(int position) {
        User user = users.get(position);
        dst_Id = user.id;
        transfare(src_Id,dst_Id,amount);
        Intent intent = new Intent(getApplicationContext(),ViewUsers.class);

        startActivity(intent);
        ToUser.this.finish();

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void transfare (int src_id, int dst_id, float amount )
    {
        DataBase dataBase = new DataBase(this);

        User srcUser = dataBase.getUserById(src_id);
        User dstUser = dataBase.getUserById(dst_id);

        if(amount<=srcUser.getBalace()){

            dataBase.insertTransferData(src_id,dst_id,getDate(),amount,1);
            dataBase.updateBalance(dst_id,dstUser.getBalace()+amount );
            dataBase.updateBalance(src_id,srcUser.getBalace()-amount);
            Toast.makeText(this, "The Transaction done successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            dataBase.insertTransferData(src_id,dst_id,getDate(),amount,0);
            Toast.makeText(this, "The Transaction done Fail", Toast.LENGTH_SHORT).show();

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now).toString();

    }
}