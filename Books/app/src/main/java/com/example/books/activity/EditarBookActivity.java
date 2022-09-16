package com.example.books.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.books.R;
import com.example.books.data.Book;
import com.example.books.data.BookDAO;

public class EditarBookActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitle;
    private EditText edtAuthor;
    private EditText edtPages;
    private Button btnProcessar;
    private Button btnCancelar;

    private BookDAO bookDAO;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtPages = findViewById(R.id.edtPages);
        btnProcessar = findViewById(R.id.btnProcessar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnProcessar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        bookDAO = bookDAO.getInstance(this);
        book = (Book) getIntent().getSerializableExtra("book");

        if (book != null) {
            edtTitle.setText(book.getTitle());
            edtAuthor.setText(book.getAuthor());
            edtPages.setText(book.getPages());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnProcessar) {
            String title = edtTitle.getText().toString();
            String author = edtAuthor.getText().toString();
            String pages = edtPages.getText().toString();
            String msg;

            if (book == null) {
                Book book = new Book(title, author, pages);
                bookDAO.insert(book);
                msg = "Cadastrado Livro com id " + book.getId();
            }else {
                book.setTitle(title);
                book.setAuthor(author);
                book.setPages(pages);
                bookDAO.update(book);
                msg = "Cadastro do livro atualizado com id " + book.getId();
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else if (view.getId() == R.id.btnCancelar) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}