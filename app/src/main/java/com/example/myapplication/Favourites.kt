package com.example.myapplication

<<<<<<< HEAD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FavouritesBinding
<<<<<<< HEAD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Favourites : AppCompatActivity(), BookAdapter.OnItemClickListener {
=======

class Favourites : AppCompatActivity() {
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
    private lateinit var binding: FavouritesBinding
    private lateinit var adapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavouritesBinding.inflate(layoutInflater)
<<<<<<< HEAD
=======


>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
        setContentView(binding.root)

        recyclerView = findViewById(R.id.rvFav)

<<<<<<< HEAD
        adapter = BookAdapter(this)

        loadLikedBooks()
    }

    private fun loadLikedBooks() {
        recyclerView.layoutManager = GridLayoutManager(this, 1)
=======
        adapter = BookAdapter(object : BookAdapter.OnItemClickListener {
            override fun onItemClick(book: Data) {
                // Обработка нажатия на элемент списка
            }
        })



        loadLikedBooks()



    }

    private fun loadLikedBooks() {
        recyclerView.layoutManager = GridLayoutManager(binding.root.context, 1)
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
        recyclerView.adapter = adapter

        val db = Books.getDb(this)
        db.getDao().getAll().asLiveData().observe(this) { list ->
            val likedBooks = list.filter { it.like == true }.map { bookEntity ->
<<<<<<< HEAD
=======

>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
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

<<<<<<< HEAD
    override fun onItemClick(book: Data) {
        val intent = Intent(this , ContentActivity::class.java)
        intent.putExtra("item", book)
        intent.putExtra("fragment", "book")
        startActivityForResult(intent, FragmentMain.REQUEST_CONTENT)
        Log.d("MyLog", "onItemClick: ${book.title} ${book.like}")
    }

    override fun onLikeClick(book: Data, isChecked: Boolean) {
        // Обновите значение поля "like" для данной книги в базе данных
        val db = Books.getDb(this)
        val updatedBook = book.copy(like = isChecked)
        GlobalScope.launch(Dispatchers.IO) {
            db.getDao().updateLike(updatedBook.title, isChecked)
            runOnUiThread {
                loadLikedBooks()
            }

        }
    }

=======
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

}
