package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentMyBookBinding
import java.io.File


class FragmentMyBook : Fragment() {
    private lateinit var binding: ViewBinding
    private var isCheckboxChecked: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyBookBinding.inflate(inflater, container, false)

        val item = activity?.intent?.getSerializableExtra("item") as UserData

        val file = File(item.imageId)
        Glide.with(this)
            .load(file)
            .into(binding.imMainMyBook)
        binding.tvTitleMyBook.text = item.title
        binding.tvAboutMyBook.text = "About book: " + item.about_book
        binding.tvAuthorMyBook.text = "Author: "+ item.author
        binding.tvPublisherMyBook.text = "Publisher: "+ item.publisher
        binding.tvGenreMyBook.text = "Genre: "+ item.genre
        binding.tvYearPublishedMyBook.text ="Published in "+ item.year_of_publishing



        return binding.root
    }



}

