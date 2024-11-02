package EasyVocab.activity

import EasyVocab.adapter.DictionaryAdapter
import EasyVocab.items.DefinitionItem
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyvocab.R
import EasyVocab.model.DictionaryApiService
import EasyVocab.model.DictionaryResponse
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DictionaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchWord: EditText
    private lateinit var searchButton: Button
    private lateinit var api: DictionaryApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialisation des vues
        recyclerView = findViewById(R.id.recyclerView)
        searchWord = findViewById(R.id.searchWord)
        searchButton = findViewById(R.id.searchButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialisation de Retrofit
        api = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApiService::class.java)

        // Action du bouton de recherche
        searchButton.setOnClickListener {
            val word = searchWord.text.toString().trim()
            if (word.isNotEmpty()) {
                fetchDefinitions(word)
            } else {
                Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchDefinitions(word: String) {
        api.getDefinitions(word).enqueue(object : Callback<List<DictionaryResponse>> {
            override fun onResponse(call: Call<List<DictionaryResponse>>, response: Response<List<DictionaryResponse>>) {
                if (response.isSuccessful && response.body() != null) {
                    val wordResponse = response.body()?.firstOrNull()
                    if (wordResponse != null) {
                        val definitionItems = flattenDefinitions(wordResponse)
                        recyclerView.adapter = DictionaryAdapter(definitionItems)
                    } else {
                        Toast.makeText(this@DictionaryActivity, "No definitions found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DictionaryActivity, "No definitions found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<DictionaryResponse>>, t: Throwable) {
                Toast.makeText(this@DictionaryActivity, "Failed to fetch definitions", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun flattenDefinitions(response: DictionaryResponse): List<DefinitionItem> {
        val definitionItems = mutableListOf<DefinitionItem>()
        for (meaning in response.meanings) {
            for (definition in meaning.definitions) {
                definitionItems.add(
                    DefinitionItem(
                        word = response.word,
                        partOfSpeech = meaning.partOfSpeech,
                        definition = definition.definition,
                        example = definition.example
                    )
                )
            }
        }
        return definitionItems
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Ferme l'activité actuelle et retourne à l'activité précédente
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

