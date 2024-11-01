package EasyVocab.activity

import EasyVocab.storage.VocabENStorage
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.easyvocab.R


class VocabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envocab)

        val id = intent.getIntExtra(ENDictionaryActivity.EXTRA_VOCAB, 0)
        val vocabEN = VocabENStorage.get(applicationContext).find(id)

        findViewById<TextView>(R.id.VocabEN_value).text = vocabEN?.value

    }
}