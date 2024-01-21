package com.example.myroomdbapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomdbapplication.databinding.ListItemBinding
import com.example.myroomdbapplication.db.Book

class MyAdapter : RecyclerView.Adapter<MyAdapter.BookViewHolder>() {
    private var books = emptyList<Book>()

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        private val authorTextView: TextView = itemView.findViewById(R.id.tvAuthor)
        fun bind(book: Book) {
            nameTextView.text = book.name
            authorTextView.text = book.author
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BookViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = books[position]
        holder.bind(currentBook)
    }
     override fun getItemCount(): Int {
        return books.size
    }

    fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }
}
        /*fun bind(book: Book, clickListener: (Book) -> Unit) {
            binding.tvName.text = book.name
            binding.tvAuthor.text = book.author
            binding.listItemLayout.setOnClickListener {
                clickListener(book)
            }*/
