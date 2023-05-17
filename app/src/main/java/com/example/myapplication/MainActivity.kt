package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragment = FragmentMain() // создаем экземпляр фрагмента
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MainHolder, fragment) // заменяем FrameLayout на наш фрагмент
        transaction.commit()



    }




}