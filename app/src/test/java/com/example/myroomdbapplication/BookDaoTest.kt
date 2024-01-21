package com.example.myroomdbapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDao
import com.example.myroomdbapplication.db.BookDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BookDaoTest {

    private lateinit var bookDao: BookDao
    private lateinit var database: BookDatabase

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        val bookDaoMock = Mockito.mock(BookDao::class.java)
        database = Mockito.mock(BookDatabase::class.java)
        Mockito.`when`(database.bookDao()).thenReturn(bookDaoMock)

        bookDao = bookDaoMock
    }
    @Test
    fun insert() = runBlocking {
        val book = Book(1, "The Great Gatsby", "F. Scott Fitzgerald")

        bookDao.insert(book)

        Mockito.verify(database, Mockito.times(1)).bookDao()
        Mockito.verify(bookDao, Mockito.times(1)).insert(book)
    }
    @Test
    fun getAllBooks() = testDispatcher.runBlockingTest {
        val book1 = Book(1, "The Great Gatsby", "F. Scott Fitzgerald")
        val book2 = Book(2, "To Kill a Mockingbird", "Harper Lee")

        val booksLiveData = MutableLiveData<List<Book>>()
        Mockito.`when`(bookDao.getAllBooks()).thenReturn(booksLiveData)

        val observer = Mockito.mock(Observer::class.java) as Observer<List<Book>>
        bookDao.getAllBooks().observeForever(observer)

        Mockito.verify(database).bookDao()
        Mockito.verify(bookDao).getAllBooks()

        booksLiveData.postValue(listOf(book1, book2))

        Mockito.verify(observer).onChanged(listOf(book1, book2))

        bookDao.getAllBooks().removeObserver(observer)
    }
}