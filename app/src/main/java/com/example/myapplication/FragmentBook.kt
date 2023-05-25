package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.FragmentBookBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.CheckBox



class FragmentBook : Fragment() {
    private lateinit var binding: ViewBinding
    private var isCheckboxChecked: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        val item = activity?.intent?.getSerializableExtra("item") as Data

        binding.imMainBook.setImageResource(item.imageId)
        binding.tvTitleBook.text = item.title
        binding.tvAboutBook.text = "About book: " + item.about_book
        binding.tvAuthorBook.text = "Author: "+ item.author
        binding.tvPublisherBook.text = "Publisher: "+ item.publisher
        binding.tvGenreBook.text = "Genre: "+ item.genre
        binding.tvYearPublishedBook.text ="Published in "+ item.year_of_publishing

//        binding.checkboxLike.visibility = View.VISIBLE

        if (item.like) {
            binding.checkboxLike.isChecked = true
        } else {
            binding.checkboxLike.isChecked = false
        }

        binding.checkboxLike.isChecked = isCheckboxChecked

        binding.checkboxLike.setOnCheckedChangeListener { _, isChecked ->
            isCheckboxChecked = isChecked
            val dao = Books.getDb(this).getDao()
            GlobalScope.launch(Dispatchers.IO) {
                if (isChecked) {
                    item.like = true
                    dao.updateLike(item.title, true)
                } else {
                    item.like = false
                    dao.updateLike(item.title, false)
                }
            }
        }

        val sharedPrefs = activity?.getPreferences(Context.MODE_PRIVATE)
        isCheckboxChecked = sharedPrefs?.getBoolean("checkbox_state", false) ?: false
        binding.checkboxLike.isChecked = isCheckboxChecked




        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("checkbox_state", isCheckboxChecked)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            isCheckboxChecked = savedInstanceState.getBoolean("checkbox_state", false)
        }
    }


    // create updateData function for updating data in the fragment when the checkbox is clicked
//    fun updateData() {
//
//    }


}

