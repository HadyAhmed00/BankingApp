package com.example.bankingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DataBase(@Nullable Context context) {
        super(context, "User.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT  ,NAME TEXT,BALANCE DECIMAL ,PHONE_NUMBER TEXT ,EMAIL VARCHAR,USER_PIC INTEGER)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,SRC_ID INTEGER ,DEST_ID INTEGER ,AMOUNT DECIMAL,STATUS INTEGER)");
        db.execSQL("insert into user_table values(1,'Hady',9472.00,'01234567489','hady@gmail.com',0)");
        db.execSQL("insert into user_table values(2,'Mohammed',464654.00,'0154656589','mohamed22@gmail.com',1)");
        db.execSQL("insert into user_table values(3,'Abo ElAnwar',2334.00,'0112234232','El-noor@gmail.com',2)");
        db.execSQL("insert into user_table values(4,'Hany',32242.00,'01342342323','Hany44@gmail.com',3)");
        db.execSQL("insert into user_table values(5,'Wassem',923219.00,'01216923219','wiza33@gmail.com',4)");
        db.execSQL("insert into user_table values(6,'Hitham',313336.00,'01333695545','Hitham22@gmail.com',5)");
        db.execSQL("insert into user_table values(7,'Yehia',335456.00,'0123556846','Yehia42@gmail.com',6)");
        db.execSQL("insert into user_table values(8,'Yossif',2233211.00,'0122233211','Yoss33if@gmail.com',7)");
        db.execSQL("insert into user_table values(9,'Shady',942372.00,'012424267489','shady@gmail.com',8)");
        db.execSQL("insert into user_table values(10,'Hishame',23223222.00,'012322322289','Hishame@gmail.com',9)");
        db.execSQL("insert into user_table values(11,'Ahmed',23232.00,'01232333449','ahmed@gmail.com',10)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public ArrayList<User> readUsersData(){
        /*SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;*/
        SQLiteDatabase db = this.getReadableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<User> users = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                User user = new User(Integer.parseInt(cursorCourses.getString(0)),
                        cursorCourses.getString(1),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),ViewUsers.ava[Integer.parseInt(cursorCourses.getString(5))%5],
                        Float.parseFloat(cursorCourses.getString(2)) );

                users.add(user);

            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return users;
    }


    public void updateBalance(int Id, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where USER_ID = " +Id);
    }

    public User getUserById(int Id){
        /*SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where USER_ID = " + Id, null);
        return cursor;*/

        SQLiteDatabase db = this.getReadableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("select * from user_table where USER_ID = " + Id, null);
        if(cursorCourses.moveToFirst()) {
            User user = new User(Integer.parseInt(cursorCourses.getString(0)),
                    cursorCourses.getString(1),
                    cursorCourses.getString(3),
                    cursorCourses.getString(4), ViewUsers.ava[Integer.parseInt(cursorCourses.getString(5)) % 5],
                    Float.parseFloat(cursorCourses.getString(2)));
            return user;
        }

        return  null;

    }

    /*
    public Cursor getSelectdUser(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where USER_ID = " +Id, null);
        return cursor;
    }*/



    public ArrayList<Transaction> readTransaction(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("select * from transfers_table", null);
        ArrayList<Transaction> trans=new ArrayList<>();

        User src ,dst ;
        String date;
        float amount;
        boolean state;
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                /*User user = new User(Integer.parseInt(cursorCourses.getString(0)),
                        cursorCourses.getString(1),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),ViewUsers.ava[Integer.parseInt(cursorCourses.getString(5))%5],
                        Float.parseFloat(cursorCourses.getString(2)) );*/
                date = cursorCourses.getString(1);
                src = getUserById(Integer.parseInt(cursorCourses.getString(2)));
                dst = getUserById(Integer.parseInt(cursorCourses.getString(3)));
                amount =  Float.parseFloat(cursorCourses.getString(4));
                state = Integer.parseInt(cursorCourses.getString(5)) == 1;
                Transaction t = new Transaction(src,dst,amount,state);
                t.setData(date);
                trans.add(t);

            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();

        return trans;
    }

    public boolean insertTransferData(int src_id, int dst_id ,String date, float amount, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("SRC_ID", src_id);
        contentValues.put("DEST_ID", dst_id);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}


//public class DataBase {
//}
