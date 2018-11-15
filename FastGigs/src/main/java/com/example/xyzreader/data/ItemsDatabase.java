package com.example.xyzreader.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.xyzreader.data.ItemsProvider.Tables;

public class ItemsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 2;

    public ItemsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.ITEMS + " ("
                + ItemsContract.ItemsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemsContract.ItemsColumns.SERVER_ID + " TEXT,"
                + ItemsContract.ItemsColumns.TITLE + " TEXT NOT NULL,"
                + ItemsContract.ItemsColumns.AUTHOR + " TEXT NOT NULL,"
                + ItemsContract.ItemsColumns.BODY + " TEXT NOT NULL,"
                //+ ItemsContract.ItemsColumns.THUMB_URL + " TEXT NOT NULL,"
                //+ ItemsContract.ItemsColumns.PHOTO_URL + " TEXT NOT NULL,"
                + ItemsContract.ItemsColumns.ASPECT_RATIO + " REAL NOT NULL DEFAULT 1.5,"
                + ItemsContract.ItemsColumns.PUBLISHED_DATE + " TEXT NOT NULL"
                + ")" );

        db.execSQL("INSERT or replace INTO items (server_id, title, author,body, aspect_ratio, published_date) VALUES('Web Development','Code Conversion to IE11','malam004','Convert existing code to IE11 compatible. Web link and task details provided below', '100','10/02/2018')");
        db.execSQL("INSERT or replace INTO items (server_id, title, author,body, aspect_ratio, published_date) VALUES('Video Transcription','Convert Spanish To English','malam004','Translate spanish conversation to english. Make 2 minutes individual audio for each conversation', '100','10/02/2018')");
        db.execSQL("INSERT or replace INTO items (server_id, title, author,body, aspect_ratio, published_date) VALUES('Food Delivery','50 Pizzas Delivery','malam004','Delivery 50 pizzas to the below location', '50','10/02/2018')");
        db.execSQL("INSERT or replace INTO items (server_id, title, author,body, aspect_ratio, published_date) VALUES('Food Delivery','100 Pizzas Delivery','malam004','Delivery 100 pizzas to the below location', '50','10/02/2018')");
        db.execSQL("INSERT or replace INTO items (server_id, title, author,body, aspect_ratio, published_date) VALUES('Data Analysis','Analysis SF Traffic Data','malam004','Prepare SF traffic flow report for pick hours. Time frame 08/01/2018 to 10/30/2018', '250','07/02/2018')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.ITEMS);
        onCreate(db);
    }
}
