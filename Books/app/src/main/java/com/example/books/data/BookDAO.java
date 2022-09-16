package com.example.books.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static BookDAO instance;
    public SQLiteDatabase db;

    public BookDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    //singleton
    public static BookDAO getInstance(Context context){
        if (instance == null){
            instance  = new BookDAO(context);
        }
        return instance;
    }

    public void insert(Book book){
        ContentValues values = new ContentValues();
        values.put(BookDados.Columns.TITLE, book.getTitle());
        values.put(BookDados.Columns.AUTHOR, book.getAuthor());
        values.put(BookDados.Columns.PAGES, book.getPages());
        long id = db.insert(BookDados.TABLE_NAME, null, values);
        book.setId((int) id);
    }

    public void update(Book book){
        ContentValues values = new ContentValues();
        values.put(BookDados.Columns.TITLE, book.getTitle());
        values.put(BookDados.Columns.AUTHOR, book.getAuthor());
        values.put(BookDados.Columns.PAGES, book.getPages());
        db.update(BookDados.TABLE_NAME, values, BookDados.Columns._ID + " = ?", new String[]{String.valueOf(book.getId())});
    }

    public void delete(Book book){
        db.delete(BookDados.TABLE_NAME, BookDados.Columns._ID + " = ?", new String[]{String.valueOf(book.getId())});
    }

    public List<Book> list(){
        String[] columns = {
                BookDados.Columns._ID,
                BookDados.Columns.TITLE,
                BookDados.Columns.AUTHOR,
                BookDados.Columns.PAGES,
        };
        List<Book> books = new ArrayList<>();

        try (Cursor c = db.query(BookDados.TABLE_NAME, columns, null, null, null, null, BookDados.Columns._ID)){
            if (c.moveToFirst()){
                do {
                    Book b = BookDAO.fromCursor(c);
                    books.add(b);
                }while (c.moveToNext());
            }
            return books;
        }

    }

    private static Book fromCursor(Cursor c){
        @SuppressLint("Range") int id = c.getInt(c.getColumnIndex(BookDados.Columns._ID));
        @SuppressLint("Range") String title = c.getString(c.getColumnIndex(BookDados.Columns.TITLE));
        @SuppressLint("Range") String author = c.getString(c.getColumnIndex(BookDados.Columns.AUTHOR));
        @SuppressLint("Range") String pages = c.getString(c.getColumnIndex(BookDados.Columns.PAGES));
        return new Book(id, title, author, pages);
    }
}
