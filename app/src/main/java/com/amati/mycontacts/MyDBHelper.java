package com.amati.mycontacts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacts";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addDatabase(String name, String number) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);

        sql.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<ContactModel> fetchData() {
        ArrayList<ContactModel> contacts = new ArrayList<>();
        SQLiteDatabase sql = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sql.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<ContactModel> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactModel contact = new ContactModel();
            contact.id = cursor.getInt(0);
            contact.name = cursor.getString(1);
            contact.number = cursor.getString(2);
            result.add(contact);
        }
        return result;
    }
    //Update table

    public void updateData(ContactModel contactModel) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", contactModel.name);
        contentValues.put("number", contactModel.number);

        sql.update(TABLE_NAME, contentValues, "id = " + contactModel.id, null);

    }

    //delete data
    public void deleteData(int id) {
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }
}