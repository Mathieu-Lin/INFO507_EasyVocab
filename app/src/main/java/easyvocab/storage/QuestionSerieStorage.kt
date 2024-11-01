package easyvocab.storage

import easyvocab.model.QuestionSerie
import android.content.Context

object QuestionSerieStorage {

    fun get(context: Context): Storage<QuestionSerie> {
        return QuestionJSONFileStorage(context)
    }
}