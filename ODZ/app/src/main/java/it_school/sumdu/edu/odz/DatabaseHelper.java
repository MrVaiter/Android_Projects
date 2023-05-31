package it_school.sumdu.edu.odz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context ) {
        super(context,"twl",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table accounts(ID NUMBER primary key," +
                                        "login TEXT, " +
                                        "email TEXT," +
                                        "password PASSWORD)");

        DB.execSQL("create Table content(ID NUMBER primary key, " +
                                        "title TEXT, " +
                                        "full_amount NUMBER, " +
                                        "done NUMBER, " +
                                        "release_date TEXT, " +
                                        "userID NUMBER, " +
                                        "FOREIGN KEY (userID) REFERENCES accounts(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists accounts;");
        DB.execSQL("drop Table if exists content");

        onCreate(DB);
    }

    //    Accounts
    public Boolean insertUserData(Integer id, String login, String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("login", login);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result= DB.insert("accounts",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Integer getNewUserId(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from accounts ",null);
        return cursor.getCount() + 1;
    }

    public Cursor getAccount(String login){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from accounts WHERE login = '" + login + "'",null);
    }

//    Content
    public Boolean insertContentData(Integer id, String title, Integer full_amount, Integer done, String release_date, Integer userID){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("title", title);
        contentValues.put("full_amount", full_amount);
        contentValues.put("done", done);
        contentValues.put("release_date", release_date);
        contentValues.put("userID", userID);

        long result= DB.insert("content",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getContentData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from content ",null);
        return cursor;
    }

    public Integer getNewContentId(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from content ",null);
        return cursor.getCount() + 1;
    }
}