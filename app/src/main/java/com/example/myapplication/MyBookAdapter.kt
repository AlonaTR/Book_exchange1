package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.BookItemBinding
import com.example.myapplication.databinding.MyBookAdapterBinding
import kotlinx.coroutines.flow.Flow
import java.io.File

class MyBookAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<MyBookAdapter.BookHolder>() {
    private val bookList = ArrayList<UserData>()

    fun update(books: List<UserData>) {
        bookList.clear()
        bookList.addAll(books)
        notifyDataSetChanged()
    }

    class BookHolder(private val binding: MyBookAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData , listener: OnItemClickListener) {
            with(binding) {
                val file = File(user.imageId)
                Glide.with(itemView)
                    .load(file)
                    .into(imAdapt)

//                imAdapt.setImageResource(R.drawable.alice)
                tvTitleAdapt.text = user.title
                // передать в описание первые 20 символов и закончить ...
                if (user.about_book.length > 100) {
                    tvDescAdapt.text = user.about_book.substring(0, 100) + "..."
                } else {
                    tvDescAdapt.text = user.about_book
                }
                itemView.setOnClickListener {
                    listener.onItemClick(user)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyBookAdapterBinding.inflate(inflater, parent, false)
        return BookHolder(binding)
    }


    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(bookList[position], listener)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun addBook(user: UserData) {
        bookList.add(user)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(user: UserData)
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
