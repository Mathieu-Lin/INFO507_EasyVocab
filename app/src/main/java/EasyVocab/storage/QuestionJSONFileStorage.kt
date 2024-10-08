package EasyVocab.storage

import android.content.Context
import EasyVocab.model.Question
import EasyVocab.model.QuestionSerie
import org.json.JSONArray
import org.json.JSONObject

class QuestionJSONFileStorage(context: Context): JSONFileStorage<QuestionSerie>(context, "question") {

    override fun create(id: Int,obj: QuestionSerie): QuestionSerie {
        return QuestionSerie(id, obj.questions, obj.bestScore)
    }
    override fun objectToJson(id: Int, obj: QuestionSerie): JSONObject {
        val json = JSONObject()
        json.put(QuestionSerie.ID, obj.id)
        json.put(QuestionSerie.QUESTIONS, obj.questions)
        json.put(QuestionSerie.BESTSCORE, obj.bestScore)
        return json
    }
    override fun jsonToObject(json: JSONObject): QuestionSerie {
        val jsonarray = json.getJSONArray(QuestionSerie.QUESTIONS)
        val questions = mutableListOf<Question>()
        for (i in 0 until jsonarray.length()) {
            val question = getQuestion(jsonarray.getJSONObject(i))
            questions.add(i, question)
        }

        return QuestionSerie(
            json.getInt(QuestionSerie.ID),
            questions,
            json.getInt(QuestionSerie.BESTSCORE)
        )
    }

    private fun getQuestion(json: JSONObject): Question {
        return Question(
            json.getInt(Question.ID),
            json.getString(Question.QUESTION),
            json.getString(Question.OPTION1),
            json.getString(Question.OPTION2),
            json.getString(Question.OPTION3),
            json.getInt(Question.REPONSE)
        )
    }
}