package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.asLiveData
import com.example.myapplication.databinding.FragmentMainBinding


class FragmentMain : Fragment(), BookAdapter.OnItemClickListener {
    lateinit var binding: FragmentMainBinding
    private val adapter = BookAdapter(this)
    lateinit var searchView: SearchView


    val books = mapOf(
        "Alice's Adventures in Wonderland" to Data(
            id = null,
            imageId= R.drawable.alice,
            title = "Alice's Adventures in Wonderland",
            author = "Lewis Carroll",
            publisher = "Macmillan",
            genre = "Fantasy",
            year_of_publishing = "1865",
            about_book = "A young girl named Alice is the main character in Alice’s Adventures in Wonderland. She falls asleep and dreams that she follows a White Rabbit down a rabbit hole. She has many wondrous, often bizarre adventures. Alice often changes size unexpectedly, growing as tall as a house and shrinking to three inches. Some of the strange creatures she meets are the March Hare, the Cheshire Cat, the Duchess, the Mad Hatter, the Mock Turtle, and the Red Queen.",
            user_name ="ann_2000",
            like = true),
        "The Adventures of Tom Sawyer" to Data(
            id = null,
            imageId= R.drawable.alice,
            title = "The Adventures of Tom Sawyer",
            author = "Mark Twain",
            publisher = "Harper & Brothers",
            genre = "Adventure",
            year_of_publishing = "1876",
            about_book = "Tom is known for his lively and rebellious spirit, constantly seeking adventure and excitement. He frequently finds himself in trouble but manages to navigate his way out of it using his cleverness and quick thinking. Together with his loyal friend Huckleberry Finn, Tom embarks on a series of escapades that include playing pirates, hunting for treasure, and even witnessing a murder. Throughout the novel, Tom encounters various characters, including his strict Aunt Polly, his love interest Becky Thatcher, and the notorious Injun Joe. As the story progresses, Tom learns valuable lessons about friendship, responsibility, and the consequences of his actions.",
            user_name = "wellboy",
            like = false),
//        "The Great Gatsby" to Data(
//            id = null,
//            imageId = R.drawable.gatsby,
//            title = "The Great Gatsby",
//            author = " "
//        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        init()
        searchView = binding.searchView


        return binding.root

    }

    private fun init() {

        binding.apply {
            rcView.layoutManager = GridLayoutManager(binding.root.context, 1)
            rcView.adapter = adapter
            // Изменение цвета текста
            val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            searchEditText.setTextColor(resources.getColor(R.color.white))
            searchEditText.setHintTextColor(resources.getColor(R.color.white))




            val db = Books.getDb(this@FragmentMain)

            //        add to database from map recipes
//        val db = Books.getDb(this@FragmentMain)
//            Thread {
//                db.getDao().deleteAll()
//            }.start()
//
//            books.forEach {
//                val book = Data(imageId= it.value.imageId,
//                    title = it.value.title,
//                    author = it.value.author,
//                    publisher = it.value.publisher,
//                    genre = it.value.genre,
//                    year_of_publishing = it.value.year_of_publishing,
//                    about_book = it.value.about_book,
//                    user_name = it.value.user_name,
//                    like = it.value.like)
//                Thread {
//                    db.getDao().insert(book)
//                }.start()
//            }

            db.getDao().getAll().asLiveData().observe(viewLifecycleOwner) {list ->

                list.forEach(){
                    val book = Data(imageId= it.imageId,
                                    title = it.title,
                                    author = it.author,
                                    publisher = it.publisher,
                                    genre = it.genre,
                                    year_of_publishing = it.year_of_publishing,
                                    about_book = it.about_book,
                                    user_name = it.user_name,
                                    like = it.like)
                    adapter.addRecipe(book)
                }
            }


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter(newText)
                    if (newText == null || newText.isEmpty())
                        adapter.update(books.values.toList())
                    return true
                }
            })

        }


    }

    override fun onItemClick(book: Data) {
        val intent = Intent(requireContext() , ContentActivity::class.java)
        intent.putExtra("item", book)
        startActivity(intent)
        Log.d("MyLog", "onItemClick: $book")
    }

}