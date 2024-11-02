package EasyVocab.activity

import EasyVocab.storage.QuestionSerieStorage
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.easyvocab.R

class QuestionActivity : AppCompatActivity() {
    private lateinit var gestureDetector: GestureDetector
    companion object {
        const val DIFFICULTY = "DIFFICULTY"
        const val QUESTION = "QUESTION"
        const val SCORE = "SCORE"
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        gestureDetector = GestureDetector(this, GestureListener())

        val backArrow: ImageView = findViewById(R.id.backArrow)
        backArrow.setOnClickListener {
            back()
        }

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

        val openDictionaryButton: Button = findViewById(R.id.openDictionaryButton)
        openDictionaryButton.setOnClickListener {
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
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
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 != null) {
                if (e1.x - e2.x > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    back()
                    return true
                }
            }
            return false
        }
    }

    private fun back() {
        val intent = Intent(this, ENDifficultyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}