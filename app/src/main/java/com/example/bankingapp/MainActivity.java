package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView  viw_users ;
    CardView viw_trans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viw_users = findViewById(R.id.view_users);
        viw_trans = findViewById(R.id.view_transactions);

        viw_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this,ViewUsers.class  );
                startActivity(i);
            }
        });

        viw_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this,ViewTransactions.class  );
                startActivity(i);
            }
        });

    }
}