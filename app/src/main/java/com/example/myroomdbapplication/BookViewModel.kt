package com.example.myroomdbapplication


import androidx.lifecycle.*
import com.example.myroomdbapplication.db.Book
import com.example.myroomdbapplication.db.BookDatabase
import com.example.myroomdbapplication.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private var repository: BookRepository) : ViewModel(){
    var allBooks: LiveData<List<Book>> = repository.allBooks

   /*init {
        val bookDao = BookDatabase.getInstance().bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.allBooks
    }*/
    fun insertBook(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
    }
//class BookViewModel(private val repository: BookRepository) : ViewModel() {
  /*private val inputBookName = MutableLiveData<String>()
    private val inputAuthorName = MutableLiveData<String>()
    private val btnSave = MutableLiveData<String>()
    private val btnClearAll = MutableLiveData<String>()

    init {
        btnSave.value = "Save"
        btnClearAll.value = "Clear All"
    }

    fun saveOrUpdate() {
        val bookName = inputBookName.value!!
        val author = inputAuthorName.value!!
        insertBook(Book(0, bookName, author))
        inputBookName.value = ""
        inputAuthorName.value = ""
    }

    private fun insertBook(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
    val book: LiveData<List<Book>> = liveData {
        emit(repository.getAllBooks())
    }
}*/