package com.example.myapplication


<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.view.View

import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
//    val bookTitle = intent.getStringExtra("title")

=======
import FragmentBook
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bClose.setOnClickListener {
<<<<<<< HEAD
            sendEmail()
=======
            finish()
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
        }
        val tag = "FragmentBook"
        val fragmentType = intent.getStringExtra("fragment")

        if (fragmentType == "my_book") {
            val fragment = FragmentMyBook()
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_book, fragment, tag)
                .commit()
<<<<<<< HEAD
            binding.bClose.visibility = View.INVISIBLE
=======
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
        } else {
            val fragment = FragmentBook()
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_book, fragment, tag)
                .commit()
<<<<<<< HEAD


        }
    }

    private fun sendEmail() {
        val phoneNumber = "1234567890"
        val message = "Hi, is the book available now?"
        val uri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
            putExtra("sms_body", message)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }



=======
        }

    }


>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d
}
