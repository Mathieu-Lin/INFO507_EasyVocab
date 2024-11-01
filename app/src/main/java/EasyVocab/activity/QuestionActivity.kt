package EasyVocab.activity

import EasyVocab.storage.QuestionSerieStorage
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyvocab.R

class QuestionActivity : ComponentActivity() {
    companion object {
        const val DIFFICULTY = "DIFFICULTY"
        const val QUESTION = "QUESTION"
        const val SCORE = "SCORE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val idDifficulty = intent.getIntExtra(QuestionActivity.DIFFICULTY, 1)
        val idQuestion = intent.getIntExtra(QuestionActivity.QUESTION, 0)
        val serie = QuestionSerieStorage.get(applicationContext).find(idDifficulty)
        val question = serie?.questions?.get(idQuestion)
        var score = intent.getIntExtra(QuestionActivity.SCORE, 0)

        findViewById<TextView>(R.id.question).text = question?.question

        val option1 = findViewById<Button>(R.id.option1)
        val option2 = findViewById<Button>(R.id.option2)
        val option3 = findViewById<Button>(R.id.option3)

        option1.text = question?.option1
        option2.text = question?.option2
        option3.text = question?.option3

        option1.setOnClickListener {
            option1.setBackgroundColor(Color.RED)
            answer(question?.reponse, option1, option2, option3)
            score = getNewScore(question?.reponse, 1, score)
            toNextQuestion(idDifficulty, idQuestion, score)
        }

        option2.setOnClickListener {
            option2.setBackgroundColor(Color.RED)
            answer(question?.reponse, option1, option2, option3)
            score = getNewScore(question?.reponse, 2, score)
            toNextQuestion(idDifficulty, idQuestion, score)
        }

        option3.setOnClickListener {
            option3.setBackgroundColor(Color.RED)
            answer(question?.reponse, option1, option2, option3)
            score = getNewScore(question?.reponse, 3, score)
            toNextQuestion(idDifficulty, idQuestion, score)
        }




    }

    private fun answer(id:Int?, option1:Button, option2:Button, option3:Button) {
        when (id) {
            1 -> {
                option1.setBackgroundColor(Color.GREEN)
            }
            2 -> {
                option2.setBackgroundColor(Color.GREEN)
            }
            3 -> {
                option3.setBackgroundColor(Color.GREEN)
            }
        }
    }

    private fun getNewScore(id:Int?, selected : Int, score : Int) : Int {
        return if (id == selected) {
            score+1
        } else {
            score
        }
    }

    private fun toNextQuestion(idDifficulty: Int, idQuestion:Int, score : Int) {
        val nextQuestion = findViewById<LinearLayout>(R.id.nextQuestionLayout)
        nextQuestion.visibility = View.VISIBLE
        val intent : Intent
        if (idQuestion < 9) {
            intent = Intent(applicationContext, QuestionActivity::class.java)
            intent.apply {
                putExtra(DIFFICULTY, idDifficulty)
                putExtra(QUESTION, idQuestion+1)
                putExtra(SCORE, score)
            }
        } else {
            intent = Intent(applicationContext, ScoreActivity::class.java)
            intent.apply {
                putExtra(DIFFICULTY, idDifficulty)
                putExtra(SCORE, score)
            }
        }
        nextQuestion.setOnClickListener {
            startActivity(intent)
        }
    }
}