package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MyBooksBinding

class My_books : AppCompatActivity() {
    private lateinit var binding: MyBooksBinding
    private lateinit var adapter: MyBookAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.rvMyBooks)
        adapter = MyBookAdapter(object : MyBookAdapter.OnItemClickListener {
            override fun onItemClick(book: UserData) {
                val intent = Intent(this@My_books, ContentActivity::class.java)
                intent.putExtra("item", book)
                intent.putExtra("fragment", "my_book")

                startActivity(intent)

            }
        })

        recyclerView.layoutManager = GridLayoutManager(binding.root.context, 1)
        recyclerView.adapter = adapter

        loadMyBooks()
    }




    fun loadMyBooks() {
        val db = User.getDb(this)


        //        add to database from map recipes

//        Thread {
//            db.getUserDao().deleteAll()
//        }.start()
//
//
//        val book = UserData(imageId= "/storage/emulated/0/Pictures/Telegram/IMG_20220403_110303_370.jpg",
//        title = "Proba",
//        author = "Author",
//        publisher = "Publisher",
//        genre = "Genre",
//        year_of_publishing = "Year",
//        about_book = "About")
//        Thread {
//            db.getUserDao().insert(book)
//        }.start()



        db.getUserDao().getAll().asLiveData().observe(this) { list ->
            val myBooks = list.map { bookEntity ->

                UserData(
                    imageId = bookEntity.imageId,
                    title = bookEntity.title,
                    author = bookEntity.author,
                    publisher = bookEntity.publisher,
                    genre = bookEntity.genre,
                    year_of_publishing = bookEntity.year_of_publishing,
                    about_book = bookEntity.about_book

                )
            }
            adapter.update(myBooks)
//            Log.d("MyLog", "onItemClick: $myBooks")
        }
    }


}