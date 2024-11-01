package EasyVocab.request

import android.widget.Toast
import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

import EasyVocab.activity.Updatable
import EasyVocab.model.VocabEN
import com.example.easyvocab.R
import EasyVocab.storage.VocabENStorage

class VocabENRequest(private val context: Context, updatable: Updatable) {
    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            "http://51.68.91.213/gr-1-1/vocabEN.json",
            null,
            { res ->
                delete()
                insert(res.getJSONArray("vocabEN"))
                updatable.update()
                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()
            },
            { err ->
                println("DEBUG" + err.toString())
                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
        queue.start()
    }

    private fun delete() {
        for (vocab in VocabENStorage.get(context).findAll()) {
            VocabENStorage.get(context).delete(vocab.id)
        }
    }

    private fun insert(array: JSONArray) {
        for (i in 0 until array.length()) {
            val vocab = array.getJSONObject(i)
            VocabENStorage.get(context).insert(
                VocabEN(
                    0,
                    vocab.getString(VocabEN.VALUE)
                )
            )
        }
    }
}