package com.example.myroomdbapplication

import com.example.myroomdbapplication.db.Book
import org.junit.Assert.assertEquals
import org.junit.Test

class BookTest {

    @Test
    fun `test book properties`() {
        val book = Book(
            id = 1,
            name = "The Great Gatsby",
            author = "F. Scott Fitzgerald"
        )

        assertEquals(1, book.id)
        assertEquals("The Great Gatsby", book.name)
        assertEquals("F. Scott Fitzgerald", book.author)
    }

    @Test
    fun `test book equality`() {
        val book1 = Book(
            id = 1,
            name = "The Great Gatsby",
            author = "F. Scott Fitzgerald"
        )

        val book2 = Book(
            id = 1,
            name = "The Great Gatsby",
            author = "F. Scott Fitzgerald"
        )

        assertEquals(book1, book2)
    }
}