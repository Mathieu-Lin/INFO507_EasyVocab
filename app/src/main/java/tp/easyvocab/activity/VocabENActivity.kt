package tp.easyvocab.activity

import android.graphics.Color
import tp.easyvocab.R
import tp.easyvocab.dictionaryEN.adapter.VocabENAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VocabENActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endictionary)

        val id = intent.getIntExtra(MainActivity.EXTRA_VOCAB, 0)
        val vocabEN = VocabENStorage.get(applicationContext).find(id)

        findViewById<TextView>(R.id.VocabEN_value_insert).text = vocabEN?.value
    }
//    private lateinit var adapter: VocabENAdapter
//    private lateinit var vocabList: RecyclerView
//    private lateinit var searchButton: Button
//    private lateinit var searchInput: EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_endictionary)
//
//        searchButton = findViewById(R.id.button_search_vocab)
//        searchInput = findViewById(R.id.VocabEN_value_insert)
//
//        // Initialisation de la RecyclerView
//        vocabList.layoutManager = LinearLayoutManager(this)
//        adapter = VocabENAdapter(this, VocabENStorage.get(this).findAll())
//        vocabList.adapter = adapter
//
//        // Action lors du clic sur le bouton de recherche
//        searchButton.setOnClickListener {
//            val query = searchInput.text.toString()
//            val filteredList = VocabENStorage.get(this).findAll().filter {
//                it.value.contains(query, ignoreCase = true)
//            }
//            adapter.updateList(filteredList)
//        }
//    }
}