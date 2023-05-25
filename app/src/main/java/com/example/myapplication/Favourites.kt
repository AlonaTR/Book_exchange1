package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FavouritesBinding

class Favourites : AppCompatActivity() {
    private lateinit var binding: FavouritesBinding
    private lateinit var adapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavouritesBinding.inflate(layoutInflater)


        setContentView(binding.root)

        recyclerView = findViewById(R.id.rvFav)

        adapter = BookAdapter(object : BookAdapter.OnItemClickListener {
            override fun onItemClick(book: Data) {
                // Обработка нажатия на элемент списка
            }
        })



        loadLikedBooks()



    }

    private fun loadLikedBooks() {
        recyclerView.layoutManager = GridLayoutManager(binding.root.context, 1)
        recyclerView.adapter = adapter

        val db = Books.getDb(this)
        db.getDao().getAll().asLiveData().observe(this) { list ->
            val likedBooks = list.filter { it.like == true }.map { bookEntity ->

                Data(
                    imageId = bookEntity.imageId,
                    title = bookEntity.title,
                    author = bookEntity.author,
                    publisher = bookEntity.publisher,
                    genre = bookEntity.genre,
                    year_of_publishing = bookEntity.year_of_publishing,
                    about_book = bookEntity.about_book,
                    user_name = bookEntity.user_name,
                    like = bookEntity.like
                )
            }
            adapter.update(likedBooks)
        }
    }


}
