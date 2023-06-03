package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import com.example.myapplication.databinding.UserInfoBinding

class User_Info : AppCompatActivity() {
    lateinit var binding: UserInfoBinding
    private val PICK_IMAGE_REQUEST_CODE = 1
    private var selectedImagePath: String = ""
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        val name = "Alona"
        val surname = "Trubchaninova"
        val email = "example@gmail.com"
        val phone = "+48123456789"
        super.onCreate(savedInstanceState)
        binding = UserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        selectedImagePath = sharedPreferences.getString("image_path", "") ?: ""
        if (selectedImagePath.isNotEmpty()) {
            binding.imUser.setImageURI(Uri.parse(selectedImagePath))
        } else {
            binding.imUser.setImageResource(R.drawable.alice)
        }

        binding.tvName.text = "Name: " + name
        binding.tvLastName.text = "Surname: " + surname
        binding.tvEmail.text = "Email: " + email
        binding.tvPhone.text = "Phone: " + phone
        binding.tvNameEdit.setText(name)
        binding.tvLastNameEdit.setText(surname)
        binding.tvEmailEdit.setText(email)
        binding.tvPhoneEdit.setText(phone)

        binding.btEdit.setOnClickListener {
            binding.tvName.visibility = android.view.View.INVISIBLE
            binding.tvLastName.visibility = android.view.View.INVISIBLE
            binding.tvEmail.visibility = android.view.View.INVISIBLE
            binding.tvPhone.visibility = android.view.View.INVISIBLE
            binding.btFavourites.visibility = android.view.View.INVISIBLE
            binding.btMyBooks.visibility = android.view.View.INVISIBLE
            binding.btEdit.visibility = android.view.View.INVISIBLE

            binding?.scroll?.visibility = android.view.View.VISIBLE
            binding.tvNameEdit.visibility = android.view.View.VISIBLE
            binding.tvLastNameEdit.visibility = android.view.View.VISIBLE
            binding.tvEmailEdit.visibility = android.view.View.VISIBLE
            binding.tvPhoneEdit.visibility = android.view.View.VISIBLE
            binding.btSave.visibility = android.view.View.VISIBLE
            binding.editphoto.visibility = android.view.View.VISIBLE

        }

        binding.btSave.setOnClickListener {
            binding.tvName.visibility = android.view.View.VISIBLE
            binding.tvLastName.visibility = android.view.View.VISIBLE
            binding.tvEmail.visibility = android.view.View.VISIBLE
            binding.tvPhone.visibility = android.view.View.VISIBLE
            binding.btFavourites.visibility = android.view.View.VISIBLE
            binding.btMyBooks.visibility = android.view.View.VISIBLE
            binding.btEdit.visibility = android.view.View.VISIBLE

            binding?.scroll?.visibility = android.view.View.INVISIBLE
            binding.tvNameEdit.visibility = android.view.View.INVISIBLE
            binding.tvLastNameEdit.visibility = android.view.View.INVISIBLE
            binding.tvEmailEdit.visibility = android.view.View.INVISIBLE
            binding.tvPhoneEdit.visibility = android.view.View.INVISIBLE
            binding.btSave.visibility = android.view.View.INVISIBLE
            binding.editphoto.visibility = android.view.View.INVISIBLE

            binding.tvName.text = "Name: " + binding.tvNameEdit.text.toString()
            binding.tvLastName.text = "Surname: " + binding.tvLastNameEdit.text.toString()
            binding.tvEmail.text = "Email: " + binding.tvEmailEdit.text.toString()
            binding.tvPhone.text = "Phone: " + binding.tvPhoneEdit.text.toString()


            if (selectedImagePath.isNotEmpty()){
                binding.imUser.setImageURI(Uri.parse(selectedImagePath))
            }
            else{
                binding.imUser.setImageResource(R.drawable.alice)
            }
        }
        binding.editphoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }


        binding.btFavourites.setOnClickListener {
            val intent = intent
            intent.setClass(this, Favourites::class.java)
            startActivity(intent)
        }

        binding.btMyBooks.setOnClickListener {
            val intent = intent
            intent.setClass(this, My_books::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            binding.imUser.setImageURI(selectedImageUri)

            selectedImageUri?.let { uri ->
                selectedImagePath = getImagePath(uri) ?: ""
            }
        }
    }

    private fun getImagePath(imageUri: Uri): String? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(imageUri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val imagePath = cursor?.getString(columnIndex!!)
        cursor?.close()
        return imagePath
    }

    override fun onDestroy() {
        super.onDestroy()

        // Сохранение пути к изображению в SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("image_path", selectedImagePath)
        editor.apply()
    }
}