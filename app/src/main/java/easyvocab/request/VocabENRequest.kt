package easyvocab.request

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import easyvocab.activity.Updatable
import easyvocab.model.VocabEN
import com.example.easyvocab.R
import easyvocab.storage.VocabENStorage

// VocabENRequest.kt
class VocabENRequest(private val context: Context, private val updatable: Updatable) {

    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            "http://51.68.91.213/gr-1-1/vocabEN.json",
            null,
            { response ->
                delete()
                insert(response.getJSONArray("vocabEN"))
                updatable.update() // Appel à la mise à jour de l'interface
                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()
            },
            { error ->
                // Utilisation de Log.e pour les erreurs
                Log.e("VocabENRequest", "Erreur de requête : ${error.message}", error)
                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request) // Ajouter la requête à la file d'attente
    }

    private fun delete() {
        // Supprime tous les éléments du stockage
        VocabENStorage.get(context).findAll().forEach { vocab ->
            VocabENStorage.get(context).delete(vocab.id)
        }
    }

    private fun insert(array: JSONArray) {
        // Insère chaque élément dans le stockage
        for (i in 0 until array.length()) {
            val vocab = array.getJSONObject(i)
            VocabENStorage.get(context).insert(
                VocabEN(
                    0,
                    vocab.getString(VocabEN.VALUE) // Assurez-vous que VocabEN.VALUE est correctement défini
                )
            )
        }
    }
}
