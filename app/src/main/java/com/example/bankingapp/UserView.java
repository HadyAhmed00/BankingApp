package com.example.bankingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserView extends AppCompatActivity {


    TextView userName,
             userId,
             userEmail,
             userPhone,
             userBalace;
    ImageView userPhoto;
    EditText src, dst,amoutn;
    Button popUp,cancel,doTheThing;

    String tmpName ;
    String tmpEmail;
    String tmpPhone;
    Float tmpBalece;
    String tmpId ;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        Intent intent =getIntent();

        tmpName = intent.getStringExtra(ViewUsers.userName);
         tmpEmail = intent.getStringExtra(ViewUsers.userEmail);
         tmpPhone = intent.getStringExtra(ViewUsers.userPhone);
        tmpBalece =intent.getFloatExtra(ViewUsers.userBalance,0);
         tmpId = String.valueOf(intent.getIntExtra(ViewUsers.userId,0));
        int tmppic =intent.getIntExtra(ViewUsers.userPhoto,0);


        userName = findViewById(R.id.name_profile_txt);
        userId = findViewById(R.id.id);
        userEmail = findViewById( R.id.user_email);
        userPhone = findViewById(R.id.user_phone);
        userBalace = findViewById(R.id.user_balace);
        userPhoto = findViewById(R.id.user_pic);
        popUp = findViewById(R.id.pop_up);

//        Intent intent = new Intent();
        userId.setText(tmpId);
        userName.setText(tmpName);
        userEmail.setText(tmpEmail);
        userPhone.setText(tmpPhone);
        userBalace.setText(String.valueOf(tmpBalece));
        userPhoto.setBackgroundResource(tmppic);


        popUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewContactDiaglog();
            }
        });


    }

    public void createNewContactDiaglog() {
        dialogBuilder = new AlertDialog.Builder( this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.trans_popup, null);
        dst = contactPopupView.findViewById(R.id.dst);
        amoutn = contactPopupView.findViewById(R.id.amount);
        doTheThing = contactPopupView.findViewById(R.id.do_the_thing);
        cancel = contactPopupView.findViewById(R.id.cancle);
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        doTheThing.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                transfare(Integer.parseInt(tmpId),
                          Integer.parseInt(String.valueOf(dst.getText())),
                          Integer.parseInt(String.valueOf(amoutn.getText())));

            }
        });
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
            Toast.makeText(this, "The Transaction done successful", Toast.LENGTH_SHORT).show();
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