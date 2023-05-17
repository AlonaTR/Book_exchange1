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
        val fragment = FragmentBook()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_book , fragment)
        transaction.commit()



    }


}
