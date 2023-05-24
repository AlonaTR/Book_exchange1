package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.UserInfoBinding

class User_Info : AppCompatActivity() {
    lateinit var binding: UserInfoBinding
    val name = "Alona"
    val surname = "Trubchaninova"
    val email = "example@gmail.com"
    val phone = "+48123456789"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = "Name: " + name
        binding.tvLastName.text = "Surname: " + surname
        binding.tvEmail.text = "Email: " + email
        binding.tvPhone.text = "Phone: " + phone



    }
}