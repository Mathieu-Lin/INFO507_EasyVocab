package EasyVocab.storage

import EasyVocab.model.Question
import EasyVocab.model.QuestionSerie
import android.content.Context

object QuestionSerieStorage {

    fun get(context: Context): Storage<QuestionSerie> {
        val storage: Storage<QuestionSerie> = QuestionJSONFileStorage(context)
        return storage
    }
}