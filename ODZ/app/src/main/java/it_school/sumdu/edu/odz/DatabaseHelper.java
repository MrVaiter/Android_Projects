package it_school.sumdu.edu.odz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context ) {
        super(context,"twl",null, 3);
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
                                        "release_date TEXT, " +
                                        "type TEXT, " +
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
        Cursor cursor = DB.rawQuery("Select MAX(ID)+1 from accounts ",null);
        cursor.moveToFirst();

        if(cursor.getString(0) == null)
            return 1;

        return Integer.parseInt(cursor.getString(0));
    }

    public Cursor getAccount(String login){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from accounts WHERE login = '" + login + "'",null);
    }

//    Content
    public Boolean insertContentData(Integer id, String title, Integer full_amount, String release_date, String type, Integer userID){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("title", title);
        contentValues.put("full_amount", full_amount);
        contentValues.put("release_date", release_date);
        contentValues.put("type", type);
        contentValues.put("userID", userID);

        long result= DB.insert("content",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getContentData(String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from content WHERE userID = '" + user + "'",null);
        return cursor;
    }

    public Cursor getContent(String title, String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from content WHERE title = '" + title + "' AND userID = '" + user + "'",null);
    }

    public Integer getNewContentId(String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select MAX(ID)+1 from content WHERE userID = '" + user + "'",null);
        cursor.moveToFirst();

        if(cursor.getString(0) == null)
            return 1;

        return Integer.parseInt(cursor.getString(0));
    }

    public Boolean deleteContent(String title, String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        int result = DB.delete("content", "title='" + title + "' AND userID='" + user + "'", null);
        return result != 0;
    }
    public void updateContent(String id, String title, String date, String user){
        SQLiteDatabase DB = this.getWritableDatabase();

        String SQLQuery = "UPDATE content SET title = '" + title;
        if(!date.equals("01-01-2023")){
            SQLQuery += "', release_date = '" + date;
        }
        SQLQuery += "' WHERE userID='" + user + "' AND ID='" + id + "'";

        DB.execSQL(SQLQuery);
    }

    public void updateContent(String id, String title, String amount, String date, String user){
        SQLiteDatabase DB = this.getWritableDatabase();

        String SQLQuery = "UPDATE content SET title = '" + title + "', full_amount ='" + amount;
        if(!date.equals("01-01-2023")){
            SQLQuery += "', release_date = '" + date;
        }
        SQLQuery += "' WHERE userID='" + user + "' AND ID='" + id + "'";

        DB.execSQL(SQLQuery);
    }
}