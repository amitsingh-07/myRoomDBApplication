package com.example.myroomdbapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDao
import com.example.myroomdbapplication.db.BookDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RoomDBTest {

    private lateinit var bookDao: BookDao
    private lateinit var database: BookDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = database.bookDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testInsertAndGetAllBooks() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        val bookDao = database.bookDao()
        val book = Book(1, "Test Book", "Test Author")
        bookDao.insert(book)
        val allBooks = bookDao.getAllBooks()
        val latch = CountDownLatch(1)
        runOnUiThread {
            allBooks.observeForever {
                assertEquals(listOf(book), it)
                latch.countDown()
            }
        }
        latch.await(2, TimeUnit.SECONDS)
        database.close()
    }
}