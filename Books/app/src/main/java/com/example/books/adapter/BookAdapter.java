package com.example.books.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.books.R;
import com.example.books.data.Book;
import com.example.books.data.BookDAO;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private List<Book> books = new ArrayList<>();
    private BookDAO bookDAO;

    // private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("PT", "BR"));

    public BookAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return books.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate( R.layout.activity_adapter_list_dados, viewGroup, false);

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtAuthor = v.findViewById(R.id.txtAuthor);
        TextView txtPages = v.findViewById(R.id.txtPages);
        TextView txtId = v.findViewById(R.id.idBook);

        Book book = books.get(i);
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(book.getPages());
        txtId.setText(String.valueOf(book.getId()));
        return v;
    }

    public void setBooks(List<Book> bookList) {
        books.clear();
        this.books = bookList;
        notifyDataSetChanged();
    }

    public void remove(Book books1) {
       books.remove(books1);
        notifyDataSetChanged();
    }
}
