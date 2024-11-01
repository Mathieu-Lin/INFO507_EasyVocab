package easyvocab.model

class Question(val id: Int, val question: String, val option1: String, val option2: String, val option3: String, val reponse: Int) {
    companion object {
        const val ID = "id"
        const val QUESTION = "question"
        const val OPTION1 = "option1"
        const val OPTION2 = "option2"
        const val OPTION3 = "option3"
        const val REPONSE = "reponse"
    }
}