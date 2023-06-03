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
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import android.app.Activity.RESULT_OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope


class FragmentMain : Fragment(), BookAdapter.OnItemClickListener {
    lateinit var binding: FragmentMainBinding
    private val adapter = BookAdapter(this)
    lateinit var searchView: SearchView
    private lateinit var bookList: MutableList<Data>
    companion object {
        const val REQUEST_CONTENT = 1
//        const val RESULT_OK = -1
    }


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
            imageId= R.drawable.tom,
            title = "The Adventures of Tom Sawyer",
            author = "Mark Twain",
            publisher = "Harper & Brothers",
            genre = "Adventure",
            year_of_publishing = "1876",
            about_book = "Tom is known for his lively and rebellious spirit, constantly seeking adventure and excitement. He frequently finds himself in trouble but manages to navigate his way out of it using his cleverness and quick thinking. Together with his loyal friend Huckleberry Finn, Tom embarks on a series of escapades that include playing pirates, hunting for treasure, and even witnessing a murder. Throughout the novel, Tom encounters various characters, including his strict Aunt Polly, his love interest Becky Thatcher, and the notorious Injun Joe. As the story progresses, Tom learns valuable lessons about friendship, responsibility, and the consequences of his actions.",
            user_name = "wellboy",
            like = false),
        "The Great Gatsby" to Data(
            id = null,
            imageId= R.drawable.gatsby,
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            publisher = "Scribner",
            genre = "Novel",
            year_of_publishing = "1925",
            about_book = "The novel tells the story of Jay Gatsby, a millionaire adventurer who lives in his mansion on Long Island and dreams of reviving his passion for a woman named Daisy Buchanan. The novel is a symbol of the \"Jazz Age\" and is known for its themes of love, ambition, and corruption.",
            user_name = "ann",
            like = true),
        "The Hobbit" to Data(
            id = null,
            imageId= R.drawable.hobbit,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            publisher = "George Allen & Unwin",
            genre = "Fantasy",
            year_of_publishing = "1937",
            about_book = "The Hobbit is a fantasy novel by J.R.R. Tolkien that tells the story of Bilbo Baggins, a hobbit who is swept into an epic quest to reclaim the lost Dwarf Kingdom of Erebor from the fearsome dragon Smaug. Along the way, Bilbo and his companions encounter a host of strange creatures and perilous obstacles as they journey through Middle-earth. The novel is known for its vivid world-building, memorable characters, and timeless themes of heroism, friendship, and self-discovery.",
            user_name = "pawel",
            like = false),
        "The Catcher in the rye" to   Data(
            id = null,
            imageId= R.drawable.catcher,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            publisher = "Little, Brown and Company",
            genre = "Realistic fiction",
            year_of_publishing = "1951",
            about_book = "The Catcher in the Rye is a novel by J.D. Salinger that tells the story of Holden Caulfield, a teenage boy who has been expelled from his prep school and is wandering aimlessly through New York City. Over the course of several days, Holden encounters a variety of people and situations that force him to confront his own feelings of alienation and disillusionment with the adult world. The novel is known for its frank portrayal of teenage angst and rebellion, as well as its themes of innocence, identity, and authenticity.",
            user_name = "artur",
            like = false)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        init()
//        searchView = binding.searchView
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
////
//            Thread {
//                db.getDao().deleteAll()
//            }.start()

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
            //if rcView has items, then clear it and then add items from database

//            db.getDao().getAll().asLiveData().observe(viewLifecycleOwner) { list ->
//                adapter.update(list)
//            }

            db.getDao().getAll().asLiveData().observe(viewLifecycleOwner) {list ->
                val BooksList = list.map {
                    Data(
                        id = it.id,
                        imageId = it.imageId,
                        title = it.title,
                        author = it.author,
                        publisher = it.publisher,
                        genre = it.genre,
                        year_of_publishing = it.year_of_publishing,
                        about_book = it.about_book,
                        user_name = it.user_name,
                        like = it.like
                    )
                }
                adapter.update(BooksList)

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
        // Open ContentActivity with details of book after click on item
        val intent = Intent(requireContext() , ContentActivity::class.java)
        intent.putExtra("item", book)
        intent.putExtra("fragment", "book")
        startActivityForResult(intent, REQUEST_CONTENT)
        Log.d("MyLog", "onItemClick: ${book.title} ${book.like}")
    }

    override fun onLikeClick(book: Data, isChecked: Boolean) {
        // Update like for book in database
        val db = Books.getDb(this)
        val updatedBook = book.copy(like = isChecked)
        GlobalScope.launch(Dispatchers.IO) {
            db.getDao().updateLike(updatedBook.title, isChecked)

        }
    }





    fun updateBookList() {
        // Получите список книг из базы данных
        val db = Books.getDb(this)
        val bookListFlow = db.getDao().getAll()

        // Преобразуйте Flow<List<Data>> в List<List<Data>>
        lifecycleScope.launch {
            val bookList = bookListFlow.toList()

            // Проверьте, что список книг не пустой, прежде чем передавать его в адаптер
            if (bookList.isNotEmpty()) {
                adapter.update(bookList[0])
            }
        }
    }






}