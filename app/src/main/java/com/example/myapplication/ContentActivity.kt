package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bClose.setOnClickListener {
            finish()
        }
        val tag = "FragmentBook"
        val fragmentType = intent.getStringExtra("fragment")

        if (fragmentType == "my_book") {
            val fragment = FragmentMyBook()
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_book, fragment, tag)
                .commit()
        } else {
            val fragment = FragmentBook()
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_book, fragment, tag)
                .commit()
        }

    }
// when activity close, update data in fragment
//    override fun onDestroy() {
//        super.onDestroy()
//        val fragment = supportFragmentManager.findFragmentById(R.id.fl_book) as FragmentBook
//        fragment.updateData()
//    }




}
