package easyvocab.model

class QuestionSerie(val id: Int, val questions: List<Question>, var bestScore: Int) {
    companion object{
        const val ID = "id"
        const val QUESTIONS = "questions"
        const val BESTSCORE = "bestScore"
    }
}