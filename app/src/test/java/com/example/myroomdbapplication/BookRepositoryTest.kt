package com.example.myroomdbapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDao
import com.example.myroomdbapplication.repository.BookRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BookRepositoryTest {

    private lateinit var repository: BookRepository

    @Mock
    private lateinit var bookDao: BookDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        repository = BookRepository(bookDao)
    }

    @Test
    fun insert() = runBlocking {
        val book = Book(1, "The Great Gatsby", "F. Scott Fitzgerald")

        // Mock the behavior of the getAllBooks() method to return a non-null value
        Mockito.`when`(bookDao.getAllBooks().value).thenReturn(listOf())

        repository.insert(book)

        // Retrieve the list of books from the database
        val retrievedBooks = bookDao.getAllBooks().value

        // Find the book with ID 1 in the list
        val retrievedBook = retrievedBooks?.find { it.id == 1 }

        // Verify that the retrieved book is equal to the inserted book
        assertEquals(book, retrievedBook)
    }
}