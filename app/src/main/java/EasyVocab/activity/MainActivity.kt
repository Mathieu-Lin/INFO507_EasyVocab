package EasyVocab.activity

import EasyVocab.model.Question
import EasyVocab.model.QuestionSerie
import EasyVocab.storage.QuestionSerieStorage
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import com.example.easyvocab.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (QuestionSerieStorage.get(applicationContext).findAll().isEmpty()) {
            QuestionSerieStorage.get(applicationContext).insert(creerQuestionsFaciles())
            QuestionSerieStorage.get(applicationContext).insert(creerQuestionsMoyennes())
        }

        var screen = findViewById<LinearLayout>(R.id.mainScreen)
        screen.setOnClickListener{
            startActivity(Intent(applicationContext, ENDifficultyActivity::class.java))
        }
    }

    private fun creerQuestionsFaciles(): QuestionSerie {
        val question1 = Question(0, "Comment dit-on 'Jaune' en anglais ?", "Blue", "Red", "Yellow", 3)
        val question2 = Question(1, "Comment dit-on 'Oiseau' en anglais ?", "Bird", "Dog", "Snake", 1)
        val question3 = Question(2, "Comment dit-on 'Arbre' en anglais ?", "Door", "Tree", "Board", 2)
        val question4 = Question(3, "Comment dit-on 'Pluie' en anglais ?", "Rain", "Tree", "Pillow", 1)
        val question5 = Question(4, "Que veut dire 'Fall' ?", "Grimper", "Fouiller", "Tomber", 3)
        val question6 = Question(5, "Que veut dire 'Dog' ?", "Chat", "Chien", "Danger", 2)
        val question7 = Question(6, "Que veut dire 'Eat' ?", "Manger", "Boire", "Dormir", 1)
        val question8 = Question(7, "Que veut dire 'Chair' ?", "Cher", "Chaise", "Char", 2)
        val question9 = Question(8, "Complete la phrase 'My name ... Jean'", "Be", "Have", "Is", 3)
        val question10 = Question(9, "Complete la phrase 'I speak ...'", "Italy", "Italian", "Pastas", 2)
        val questions = listOf<Question>(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10)
        return QuestionSerie(1, questions, 0)
    }

    private fun creerQuestionsMoyennes(): QuestionSerie {
        val question1 = Question(0, "Comment dit-on 'Proche de' en anglais ?", "Next to", "Nearby", "Under", 2)
        val question2 = Question(1, "Comment dit-on 'Toilette' en anglais ?", "Bathroom", "Bedroom", "Restroom", 3)
        val question3 = Question(2, "Comment dit-on 'Jardin' en anglais ?", "Garden", "Hill", "Yard", 1)
        val question4 = Question(4, "Comment dit-on 'Centre-ville' en anglais ?", "Downtown", "Center-city", "Middle-city", 1)
        val question5 = Question(4, "Que veut dire 'Knife' ?", "Cuillere", "Fourchette", "Couteau", 3)
        val question6 = Question(5, "Que veut dire 'Under' ?", "Dessus", "Dessous", "Entre", 2)
        val question7 = Question(6, "Que veut dire 'Computer' ?", "Ordinateur", "Comparer", "Comprendre", 1)
        val question8 = Question(7, "Que veut dire 'Chair' ?", "Cher", "Chaise", "Char", 2)
        val question9 = Question(8, "Complete la phrase 'My name ... Jean'", "Be", "Have", "Is", 3)
        val question10 = Question(9, "Complete la phrase 'I speak ...'", "Italy", "Italian", "Pastas", 2)
        val questions = listOf<Question>(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10)
        return QuestionSerie(2, questions, 0)
    }
}