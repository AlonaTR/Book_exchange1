package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.UserInfoBinding

class User_Info : AppCompatActivity() {
    lateinit var binding: UserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}