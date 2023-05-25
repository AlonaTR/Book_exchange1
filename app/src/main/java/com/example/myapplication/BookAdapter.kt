package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BookItemBinding
import kotlinx.coroutines.flow.Flow
import java.util.ArrayList

class BookAdapter(val listener: OnItemClickListener): RecyclerView.Adapter<BookAdapter.BookHolder>() {
    val bookList = ArrayList<Data>()

    fun update(books: List<Data>) {
        bookList.clear()
        bookList.addAll(books)
        notifyDataSetChanged()
    }




    class BookHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = BookItemBinding.bind(item)
        fun bind(book: Data, listener: OnItemClickListener) = with(binding) {
            im.setImageResource(book.imageId)
            tvTitleItem.text = book.title
//            передать в описание первые 20 символов и закончить ...
            if (book.about_book.length > 100) {
                tvDescItem.text = book.about_book.substring(0, 100) + "..."
            } else {
                tvDescItem.text = book.about_book
            }

//            tvDesc.text = book.about_book
            itemView.setOnClickListener {
                listener.onItemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(bookList[position], listener)
    }

    fun addBook(book: Data){
        bookList.add(book)
        notifyDataSetChanged()
    }



    interface OnItemClickListener {
        fun onItemClick(book: Data)
    }

    fun filter(query: String?) {
        query?.let { searchText ->
            val filteredList = bookList.filter { book ->
                book.title.contains(searchText, ignoreCase = true)
            }
            update(filteredList)
        }
    }



}