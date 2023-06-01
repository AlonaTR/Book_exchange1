package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
<<<<<<< HEAD
//        val fragmentMain = supportFragmentManager.findFragmentById(R.id.MainHolder) as? FragmentMain
//        fragmentMain?.updateBookList()
//
//        val fragmentMain = FragmentMain.newInstance()
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.MainHolder, fragmentMain)
//            .commit()
=======

>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

        val fragment = FragmentMain() // создаем экземпляр фрагмента
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MainHolder, fragment) // заменяем FrameLayout на наш фрагмент
        transaction.commit()

        binding.button.setOnClickListener {
            // open activity AddBook
            val intent = Intent(this, AddBook::class.java)
            startActivity(intent)


        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_user -> {
                val intent = Intent(this, User_Info::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
<<<<<<< HEAD
    override fun onResume() {
        super.onResume()
        val fragmentMain = supportFragmentManager.findFragmentById(R.id.MainHolder) as? FragmentMain
        fragmentMain?.updateBookList()
    }

    fun openFragmentMain() {
        val fragmentMain = FragmentMain()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MainHolder, fragmentMain)
        transaction.commit()
    }


=======
>>>>>>> 02e74ec8eb16d92de7fdf390d55fa8528f92ad0d

}