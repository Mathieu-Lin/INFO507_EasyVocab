package easyvocab.storage

import android.content.Context
import easyvocab.model.Question
import easyvocab.model.QuestionSerie
import org.json.JSONArray
import org.json.JSONObject

class QuestionJSONFileStorage(context: Context): JSONFileStorage<QuestionSerie>(context, "question") {

    override fun create(id: Int,obj: QuestionSerie): QuestionSerie {
        return QuestionSerie(id, obj.questions, obj.bestScore)
    }

    private fun questionToJson(question: Question): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", question.id)
        jsonObject.put("question", question.question)
        jsonObject.put("option1", question.option1)
        jsonObject.put("option2", question.option2)
        jsonObject.put("option3", question.option3)
        jsonObject.put("reponse", question.reponse)
        return jsonObject
    }

    override fun objectToJson(id: Int, obj: QuestionSerie): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", obj.id)

        // Convertir la liste de questions en JSONArray
        val questionsJsonArray = JSONArray()
        for (question in obj.questions) {
            questionsJsonArray.put(questionToJson(question))
        }

        jsonObject.put("questions", questionsJsonArray)
        jsonObject.put("bestScore", obj.bestScore)
        return jsonObject
    }
    override fun jsonToObject(json: JSONObject): QuestionSerie {
        val jsonArray = json.getJSONArray(QuestionSerie.QUESTIONS)
        val questions = mutableListOf<Question>()
        for (i in 0 until jsonArray.length()) {
            val question = getQuestion(jsonArray.getJSONObject(i))
            questions.add(question)
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