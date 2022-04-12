package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewTransactions extends AppCompatActivity {

    RecyclerView tranRc;
    ArrayList<Transaction> trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);
        trans = new ArrayList<>()       ;
        tranRc = findViewById(R.id.trans_Rc) ;

        /*User u = new User("hady ahmed","01018314139","hady@gmali.cim",5000f);
        User u1 = new User("hady ahmed","01018314139","hady@gmali.cim",5000f);
        Transaction t = new Transaction(u,u1,300,true   );
        for (int i = 0 ;i<10;i++)
        {
            trans.add(t);
        }*/

        trans = new DataBase(this).readTransaction();

        TransactionListAdapter a = new TransactionListAdapter(trans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(tranRc.getContext(), LinearLayoutManager.VERTICAL, false);
        tranRc.setLayoutManager(layoutManager);
        tranRc.setAdapter(a);
    }
}