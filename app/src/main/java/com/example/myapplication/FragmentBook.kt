package com.example.myapplication

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


class FragmentBook : Fragment() {
    private lateinit var binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        val item = activity?.intent?.getSerializableExtra("item") as Data

        binding.imMain.setImageResource(item.imageId)
        binding.tvTitle.text = item.title
        binding.tvAbout.text = "About: " + item.about_book
        binding.tvAuthor.text = "Author: "+ item.author
        binding.tvPublisher.text = "Publisher: "+ item.publisher
        binding.tvGenre.text = "Genre: "+ item.genre
        binding.tvYearPublished.text ="Published in "+ item.year_of_publishing

        binding.checkboxLike.setOnCheckedChangeListener { _, isChecked ->
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



        return binding.root
    }




}

