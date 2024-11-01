package EasyVocab.storage

import EasyVocab.model.Question
import EasyVocab.model.VocabEN
import android.content.Context
import org.json.JSONObject

class VocabENJSONFileStorage(context: Context): JSONFileStorage<VocabEN>(context, "vocabEN") {

    override fun create(id: Int,obj: VocabEN): VocabEN {
        return VocabEN(id, obj.value)
    }
    override fun objectToJson(id: Int, obj: VocabEN): JSONObject {
        val json = JSONObject()
        json.put(VocabEN.ID, obj.id)
        json.put(VocabEN.VALUE, obj.value)
        return json
    }
    override  fun jsonToObject(json: JSONObject): VocabEN {
        return VocabEN(
            json.getInt(VocabEN.ID),
            json.getString(VocabEN.VALUE),
        )
    }

    private fun getVocabEN(json: JSONObject): VocabEN {
        return VocabEN(
            json.getInt(VocabEN.ID),
            json.getString(VocabEN.VALUE)
        )
    }

}