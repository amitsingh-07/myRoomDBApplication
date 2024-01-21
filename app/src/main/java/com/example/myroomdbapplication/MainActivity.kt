package com.example.myroomdbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDatabase
import com.example.myroomdbapplication.repository.BookRepository

class MainActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        val adapter = MyAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val bookDao = BookDatabase.getInstance(application).bookDao()
        val repository = BookRepository(bookDao)
        bookViewModel =
            ViewModelProvider(this, BookViewModelFactory(repository))[BookViewModel::class.java]
        bookViewModel.allBooks.observe(this, Observer { books ->
            books?.let { adapter.setBooks(it) }
        })
        val bookNameEditText = findViewById<EditText>(R.id.edtBookName)
        val bookAuthorEditText = findViewById<EditText>(R.id.edtAuthorName)
        val addBookButton = findViewById<Button>(R.id.btnSave)

        addBookButton.setOnClickListener {
            val bookName = bookNameEditText.text.toString().trim()
            val bookAuthor = bookAuthorEditText.text.toString().trim()
            if (bookName.isNotEmpty() && bookAuthor.isNotEmpty()) {
                bookViewModel.insertBook(Book(0, bookName, bookAuthor))
            }
        }
    }
}

        /*bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val bookNameEditText = findViewById<EditText>(R.id.edtBookName)
        val bookAuthorEditText = findViewById<EditText>(R.id.edtAuthorName)
        val addBookButton = findViewById<Button>(R.id.btnSave)

        addBookButton.setOnClickListener {
            val bookName = bookNameEditText.text.toString().trim()
            val bookAuthor = bookAuthorEditText.text.toString().trim()
            if (bookName.isNotEmpty() && bookAuthor.isNotEmpty()) {
                bookViewModel.insertBook(Book(0,bookName,bookAuthor))
            }
        }
    }*/

