import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.Books
import com.example.myapplication.Data
import com.example.myapplication.databinding.FragmentBookBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentBook : Fragment() {
    private lateinit var binding: FragmentBookBinding
    private var isCheckboxChecked: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(inflater, container, false)

        val item = requireActivity().intent.getSerializableExtra("item") as Data

        binding.imMainBook.setImageResource(item.imageId)
        binding.tvTitleBook.text = item.title
        binding.tvAboutBook.text = "About book: " + item.about_book
        binding.tvAuthorBook.text = "Author: " + item.author
        binding.tvPublisherBook.text = "Publisher: " + item.publisher
        binding.tvGenreBook.text = "Genre: " + item.genre
        binding.tvYearPublishedBook.text = "Published in " + item.year_of_publishing

        binding.checkboxLike.isChecked = isCheckboxChecked

        binding.checkboxLike.setOnCheckedChangeListener { _, isChecked ->
            isCheckboxChecked = isChecked
            val dao = Books.getDb(this).getDao()
            GlobalScope.launch(Dispatchers.IO) {
                dao.updateLike(item.title, isChecked)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        isCheckboxChecked = sharedPrefs.getBoolean("checkbox_state", false)
        binding.checkboxLike.isChecked = isCheckboxChecked
    }

    override fun onPause() {
        super.onPause()
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("checkbox_state", isCheckboxChecked).apply()
    }
}
