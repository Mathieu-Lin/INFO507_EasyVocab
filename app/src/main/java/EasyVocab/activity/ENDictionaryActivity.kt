package EasyVocab.activity

import EasyVocab.adapter.VocabENAdapter
import EasyVocab.request.VocabENRequest
import EasyVocab.storage.VocabENStorage
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.easyvocab.R


class ENDictionaryActivity : Updatable, AppCompatActivity() {

    companion object {
        const val EXTRA_VOCAB = "EXTRA_VOCAB"
    }

    private lateinit var list: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout
    private lateinit var searchInput: EditText
    private lateinit var searchButton: Button
    private lateinit var returnButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endictionary)

        searchInput = findViewById(R.id.VocabEN_value_insert)
        searchButton = findViewById(R.id.button_search_vocab)
        returnButton = findViewById(R.id.button_return_vocab)

        list = findViewById(R.id.VocabEN_list)
        list.adapter = object : VocabENAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                var intent = Intent(applicationContext, VocabActivity::class.java).apply {
                    putExtra(EXTRA_VOCAB, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                VocabENStorage.get(applicationContext).delete(view.tag as Int)
                return true
            }
        }

        refresh = findViewById(R.id.VocabEN_refresh)
        findViewById<SwipeRefreshLayout>(R.id.VocabEN_refresh).setOnRefreshListener {
            VocabENRequest(applicationContext, this)
        }


        returnButton.setOnClickListener {
            finish()
        }

        searchButton.setOnClickListener {
            performSearch(searchInput.text.toString())
        }
    }

    private fun performSearch(query: String) {
        val filteredWords = VocabENStorage.get(applicationContext)
            .findAll()
            .filter { it.value.startsWith(query, ignoreCase = true) }
        // actualiser l'adapter avec les résultats filtrés si nécessaire
    }

    override fun update() {
        list.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }
}