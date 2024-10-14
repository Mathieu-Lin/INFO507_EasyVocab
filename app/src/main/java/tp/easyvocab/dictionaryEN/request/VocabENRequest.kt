package tp.easyvocab.dictionaryEN.request
import VocabENStorage
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import tp.easyvocab.R
import android.content.Context
import org.json.JSONArray
import tp.easyvocab.activity.Updatable
import tp.easyvocab.dictionaryEN.model.VocabEN

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
                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show() },
            { err ->
                println("DEBUG" + err.toString())
                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show() }
        )
        queue.add(request)
        queue.start()

    }

    private fun delete(){
        for (vocabEN in VocabENStorage.get(context).findAll()){
            VocabENStorage.get(context).delete(vocabEN.id)
        }
    }

    private fun insert(array: JSONArray) {
        for(i in 0 until array.length()){
            val vocabEN = array.getJSONObject(i)
            VocabENStorage.get(context).insert(
                VocabEN(
                    0,
                    vocabEN.getString(VocabEN.VALUE)
                )
            )
        }

    }


}