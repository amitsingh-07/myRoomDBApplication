package com.example.myroomdbapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDao
import com.example.myroomdbapplication.db.BookDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class BookDatabaseTest {

    private lateinit var database: BookDatabase
    private lateinit var bookDao: BookDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        `when`(context.applicationContext).thenReturn(ApplicationProvider.getApplicationContext())

        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = database.bookDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    suspend fun insertAndGetAllBooks() {
        val book1 = Book(1, "The Great Gatsby", "F. Scott Fitzgerald")
        val book2 = Book(2, "To Kill a Mockingbird", "Harper Lee")
        val book3 = Book(3, "1984", "George Orwell")
        bookDao.insert(book1)
        bookDao.insert(book2)
        bookDao.insert(book3)

        val retrievedBooks = bookDao.getAllBooks()

        assertEquals(listOf(book1, book2, book3), retrievedBooks)
    }
}