<<<<<<< HEAD
package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
=======
import android.content.Context
import android.os.Bundle
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.myapplication.Books
import com.example.myapplication.Data
import com.example.myapplication.MainActivity
=======
import com.example.myapplication.Books
import com.example.myapplication.Data
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
import com.example.myapplication.databinding.FragmentBookBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentBook : Fragment() {
    private lateinit var binding: FragmentBookBinding
<<<<<<< HEAD


=======
    private var isCheckboxChecked: Boolean = false
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(inflater, container, false)

        val item = requireActivity().intent.getSerializableExtra("item") as Data

        binding.imMainBook.setImageResource(item.imageId)
        binding.tvTitleBook.text = item.title
        binding.tvAboutBook.text = "About book: " + item.about_book
        binding.tvAuthorBook.text = "Author: " + item.author
        binding.tvPublisherBook.text = "Publisher: " + item.publisher
        binding.tvGenreBook.text = "Genre: " + item.genre
        binding.tvYearPublishedBook.text = "Published in " + item.year_of_publishing
<<<<<<< HEAD
//        binding.checkboxLike.isChecked = item.like
//
//        binding.checkboxLike.setOnClickListener {
//            item.like = binding.checkboxLike.isChecked
//            GlobalScope.launch(Dispatchers.IO) {
//                Books.getDb(this@FragmentBook).getDao().updateLike(item.title, binding.checkboxLike.isChecked)
//
//                Log.d("Mylog", "updateLike ${item.title} ${binding.checkboxLike.isChecked}")
//
//            }
//        }



=======

        binding.checkboxLike.isChecked = isCheckboxChecked

        binding.checkboxLike.setOnCheckedChangeListener { _, isChecked ->
            isCheckboxChecked = isChecked
            val dao = Books.getDb(this).getDao()
            GlobalScope.launch(Dispatchers.IO) {
                dao.updateLike(item.title, isChecked)
            }
        }
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

        return binding.root
    }

<<<<<<< HEAD


=======
    override fun onResume() {
        super.onResume()
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        isCheckboxChecked = sharedPrefs.getBoolean("checkbox_state", false)
        binding.checkboxLike.isChecked = isCheckboxChecked
    }

    override fun onPause() {
        super.onPause()
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("checkbox_state", isCheckboxChecked).apply()
    }
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
}
