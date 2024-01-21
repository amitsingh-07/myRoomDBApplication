package com.example.myroomdbapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "published_book")
    var name: String,
    @ColumnInfo(name = "published_author")
    var author: String
)