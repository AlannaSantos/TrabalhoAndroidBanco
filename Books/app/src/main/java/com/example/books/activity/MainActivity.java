package com.example.books.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.books.R;
import com.example.books.adapter.BookAdapter;
import com.example.books.data.Book;
import com.example.books.data.BookDAO;
import com.example.books.dialog.DeleteDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, View.OnClickListener, DeleteDialog.OnDeleteListener {

    private ListView lista;
    private BookAdapter adapter;
    private BookDAO bookDAO;
    private static final int REQ_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.btnAdicionar);
        floatingActionButton.setOnClickListener(this);

        lista = findViewById(R.id.lista);

        adapter = new BookAdapter(this);
        lista.setAdapter(adapter);

        bookDAO = BookDAO.getInstance(this);
        updateList();

        lista.setOnItemClickListener(this); //editar

        lista.setOnItemLongClickListener(this); //excluir
    }

    private void updateList() {
        List<Book> books = bookDAO.list();
        adapter.setBooks(books);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // tela de informações do desenvolvedor
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.act_informacao){
            startActivity(new Intent(this, InformacaoDesenvolvedor.class));
        }
        return super.onOptionsItemSelected(item);
    }
    //inserir
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_EDIT || requestCode == RESULT_OK){
            updateList();
        }
    }
    //editar
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, EditarBookActivity.class);
        intent.putExtra("book", adapter.getItem(i));
        startActivityForResult(intent, REQ_EDIT);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EditarBookActivity.class);
        startActivityForResult(intent, REQ_EDIT);
    }
    //excluir
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Book book = adapter.getItem(i);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setBook(book);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
        return true;
    }

    @Override
    public void onDelete(Book book) {
        bookDAO.delete(book);
        adapter.remove(book);
    }
}