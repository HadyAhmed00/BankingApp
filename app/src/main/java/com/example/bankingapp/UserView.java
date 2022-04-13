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
    EditText amoutn;
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
        tmpId = String.valueOf(intent.getIntExtra(ViewUsers.userId,0));
        User u = new DataBase(this ).getUserById(Integer.parseInt(tmpId));
        tmpName =u.getName();
        tmpEmail = u.getEmile();
        tmpPhone = u.getPhone_number();
        tmpBalece =u.getBalace();
        int tmppic =u.getPhoto();


        userName = findViewById(R.id.name_profile_txt);
        userId = findViewById(R.id.id);
        userEmail = findViewById( R.id.user_email);
        userPhone = findViewById(R.id.user_phone);
        userBalace = findViewById(R.id.user_balace);
        userPhoto = findViewById(R.id.user_pic);
        popUp = findViewById(R.id.pop_up);

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
                String amout_str=String.valueOf(amoutn.getText());
                Float amout_float;
                if(amout_str.isEmpty()) {
                    amoutn.setError("This Field Cannot be Empty");
                }
                else {
                    amout_float = Float.parseFloat(amout_str);


                    if (amout_float > tmpBalece) {

                        amoutn.setError("You do not have enough Balance in Your Account");


                    } else {
                        Intent intent = new Intent(getApplicationContext(), ToUser.class);
                        intent.putExtra("SrcId", tmpId);
                        intent.putExtra("Amount", amout_float);
                        startActivity(intent);
                        dialog.dismiss();
                        UserView.this.finish();
                    }

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}