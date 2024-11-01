package EasyVocab.storage

import EasyVocab.model.VocabEN
import android.content.Context

object VocabENStorage {

    fun get(context: Context): Storage<VocabEN> {
        return VocabENJSONFileStorage(context)
    }
}

