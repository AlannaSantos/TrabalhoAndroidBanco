package com.example.books.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME = "booksdb";
    public static final int DB_VERSION = 1;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + BookDados.TABLE_NAME;

    private static final String SQL_CREATE = String.format (
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", BookDados.TABLE_NAME,
            BookDados.Columns._ID, BookDados.Columns.TITLE, BookDados.Columns.AUTHOR, BookDados.Columns.PAGES);

    private static DBHelper instance;

    //singleton
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DROP);
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
