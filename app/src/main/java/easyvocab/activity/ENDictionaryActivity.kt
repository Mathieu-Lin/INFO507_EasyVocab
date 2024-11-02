package easyvocab.activity

import easyvocab.adapter.ENDictionaryAdapter
import easyvocab.request.VocabENRequest
import easyvocab.storage.VocabENStorage
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        list.adapter = object : ENDictionaryAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                val intent = Intent(applicationContext, VocabActivity::class.java).apply {
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
        val vocabStorage = VocabENStorage.get(applicationContext)
        val allWords = vocabStorage.findAll()

        // Filtrer les mots selon le critère de recherche
        val filteredWords = if (query.isEmpty()) {
            allWords.take(20) // Si la recherche est vide, afficher les 20 premiers mots
        } else {
            allWords.filter { it.value.startsWith(query, ignoreCase = true) }
                .take(20) // Limiter les résultats à 20 mots
        }

        // Log les mots filtrés pour vérifier le résultat
        filteredWords.forEach { vocab ->
            Log.d("performSearch", "Mot trouvé : ${vocab.value}")
        }

        // Créez un nouvel adaptateur avec les mots filtrés
        list.adapter = object : ENDictionaryAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                val intent = Intent(applicationContext, VocabActivity::class.java).apply {
                    putExtra(EXTRA_VOCAB, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                VocabENStorage.get(applicationContext).delete(view.tag as Int)
                return true
            }

            override fun getItemCount(): Int {
                return filteredWords.size
            }

            override fun onBindViewHolder(holder: ENDictionaryHolder, position: Int) {
                val vocabEN = filteredWords[position]
                holder.itemView.tag = vocabEN.id
                holder.value.text = vocabEN.value
            }
        }
        list.adapter?.notifyDataSetChanged()
    }




    override fun update() {
        list.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }
}