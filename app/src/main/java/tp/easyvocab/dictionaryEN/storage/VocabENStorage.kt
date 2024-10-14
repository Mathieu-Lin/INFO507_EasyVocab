import android.content.Context
import android.content.SharedPreferences
import tp.easyvocab.dictionaryEN.model.VocabEN
import tp.easyvocab.dictionaryEN.storage.VocabENJSONFileStorage
import tp.easyvocab.dictionaryEN.storage.VocabENNoneStorage
import tp.easyvocab.storage.Storage

object VocabENStorage {
    private const val LOGIN = "login"
    private const val STORAGE = "tp/easyvocab/storage"
    const val NONE = 0
    const val DATA_BASE = 1
    const val FILE_JSON = 2

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("tp.easyvocab.preferences", Context.MODE_PRIVATE)
    }

    fun getLogin(context: Context): String? {
        return getPreferences(context).getString(LOGIN, "")
    }

    fun setLogin(context: Context, prefLogin: String) {
        getPreferences(context).edit().putString(LOGIN, prefLogin).apply()
    }

    fun getStorage(context: Context): Int {
        return getPreferences(context).getInt(STORAGE, NONE)
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<VocabEN> {
        var storage: Storage<VocabEN> = VocabENNoneStorage()
        when (getStorage(context)) {
            NONE -> storage = VocabENNoneStorage()
            // DATA_BASE -> storage = VocabENDataBaseStorage(context)
            FILE_JSON -> storage = VocabENJSONFileStorage(context)
        }
        return storage
    }
}