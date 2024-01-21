package com.example.myroomdbapplication.repository

import androidx.lifecycle.LiveData
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDao

class BookRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    suspend fun insert(book: Book){
        return bookDao.insert(book)
    }
   /*fun  getAllBooks():List<Book>{
        return bookdao.getAllBooks()
    }*/
}
