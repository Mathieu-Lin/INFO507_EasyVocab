package easyvocab.activity

import android.media.MediaPlayer
import easyvocab.storage.VocabENStorage
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.easyvocab.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class VocabActivity : AppCompatActivity() {
    private lateinit var returnButton: ImageButton
    private lateinit var vocabTextView: TextView
    private lateinit var definitionTextView: TextView
    private lateinit var phoneticTextView: TextView
    private lateinit var mediaPlayer: MediaPlayer
    private var audioUrl: String? = null


    companion object {
        private const val TAG = "VocabActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envocab)

        val id = intent.getIntExtra(ENDictionaryActivity.EXTRA_VOCAB, 0)
        val vocabEN = VocabENStorage.get(applicationContext).find(id)

        vocabTextView = findViewById(R.id.VocabEN_value)
        definitionTextView = findViewById(R.id.definitionTextView)
        phoneticTextView = findViewById(R.id.phoneticTextView)

        vocabTextView.text = vocabEN?.value

        returnButton = findViewById(R.id.button_return_vocab)
        returnButton.setOnClickListener {
            finish()
        }

        vocabEN?.value?.let {
            fetchDefinition(it)
        }
    }

    private fun fetchDefinition(word: String) {
        val url = "https://api.dictionaryapi.dev/api/v2/entries/en/$word"

        val requestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                parseResponse(response)
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Error fetching definition: ${error.message}")
            }
        )
        requestQueue.add(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray) {
        try {
            val firstEntry = response.getJSONObject(0)
            val meanings: JSONArray = firstEntry.getJSONArray("meanings")
            val firstMeaning = meanings.getJSONObject(0)

            // Get part of speech and definitions
            val partOfSpeech = firstMeaning.getString("partOfSpeech")
            val definitions = firstMeaning.getJSONArray("definitions")
            val firstDefinition = definitions.getJSONObject(0).getString("definition")

            // Get phonetic
            val phonetics = firstEntry.getJSONArray("phonetics")
            val phonetic = if (phonetics.length() > 0) phonetics.getJSONObject(0).getString("text") else "N/A"

            // Get audio URL
            audioUrl = if (phonetics.length() > 0) {
                phonetics.getJSONObject(0).getString("audio")
            } else {
                null
            }

            // Update UI
            phoneticTextView.text = phonetic
            definitionTextView.text = "$partOfSpeech: $firstDefinition"

            // Set up audio button
            setupAudioButton()

        } catch (e: JSONException) {
            Log.e(TAG, "Error parsing response: ${e.message}")
        }
    }

    private fun setupAudioButton() {
        val playAudioButton: Button = findViewById(R.id.button_play_audio)
        playAudioButton.setOnClickListener {
            audioUrl?.let { url ->
                playAudio(url)
            } ?: Log.e(TAG, "Audio URL is not available")
        }
    }

    private fun playAudio(url: String) {
        try {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(url)
                prepare() // prepare synchronously
                start()
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error playing audio: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // release the MediaPlayer when done
    }


}
