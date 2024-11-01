package easyvocab.storage

import easyvocab.model.VocabEN
import android.content.Context


object VocabENStorage {
    fun get(context: Context): Storage<VocabEN> {
        var storage: Storage<VocabEN> = VocabENJSONFileStorage(context)
        return storage
    }
}
