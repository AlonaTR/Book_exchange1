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
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


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
                // Добавьте код для обработки нажатия на значок "Home"
                return true
            }
            // Добавьте обработку других элементов меню (если нужно)
            // ...
            else -> return super.onOptionsItemSelected(item)
        }
    }

}