package com.example.myapplication
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.myapplication.databinding.AddBookBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class AddBook : AppCompatActivity() {
    lateinit var binding: AddBookBinding
    private val PICK_IMAGE_REQUEST_CODE = 1
    private var selectedImagePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.choosephoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }

        binding.addbook.setOnClickListener {
            val image = selectedImagePath
            val title = binding.tvTitle.text.toString()
            val author = binding.tvAuthor.text.toString()
            val publisher = binding.tvPublisher.text.toString()
            val genre = binding.tvGenre.text.toString()
            val year = binding.tvYearPublished.text.toString()
            val description = binding.tvAbout.text.toString()
            val book = UserData(
                imageId = image,
                title = title,
                author = author,
                publisher = publisher,
                genre = genre,
                year_of_publishing = year,
                about_book = description,
            )
            val db = User.getDb(this)
            GlobalScope.launch(Dispatchers.IO) {
                db.getUserDao().insert(book)
            }
            val fragment = supportFragmentManager.findFragmentById(R.id.MainHolder) as? FragmentMain
            fragment?.updateBookList()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            binding.imMain.setImageURI(selectedImageUri)

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

    // when activity closed write in log UserData
    override fun onDestroy() {
        super.onDestroy()
        val db = User.getDb(this)
        GlobalScope.launch(Dispatchers.IO) {
            db.getUserDao().getAll().collect {
                println(it)
            }
        }
    }
}
