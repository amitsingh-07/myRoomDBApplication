package com.example.myroomdbapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): LiveData<List<Book>>
}