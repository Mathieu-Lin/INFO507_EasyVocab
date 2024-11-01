package easyvocab.activity

import easyvocab.storage.QuestionSerieStorage
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.easyvocab.R

class ENDifficultyActivity : ComponentActivity() {
    companion object {
        const val DIFFICULTY = "DIFFICULTY"
    }

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endifficulty)

        setButtons()
        setScores()

        // Initialisation du GestureDetector
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                // Vérifiez si e1 est non nul
                if (e1 != null) {
                    val deltaX = e2.x - e1.x
                    val deltaY = e2.y - e1.y
                    // Détecte un swipe vers le haut
                    if (Math.abs(deltaY) > Math.abs(deltaX) && deltaY < -5) { // Swipe vers le haut
                        goToDictionaryActivity()
                        return true
                    }
                }
                return false
            }
        })
    }


    private fun setButtons() {
        val intent = Intent(applicationContext, QuestionActivity::class.java)
        val buttonEasy = findViewById<Button>(R.id.ENEasy)
        buttonEasy.setOnClickListener {
            intent.apply {
                putExtra(DIFFICULTY, 1)
            }
            startActivity(intent)
        }

        val buttonMoyen = findViewById<Button>(R.id.ENMoyen)
        buttonMoyen.setOnClickListener {
            intent.apply {
                putExtra(DIFFICULTY, 2)
            }
            startActivity(intent)
        }
        val buttonHard = findViewById<Button>(R.id.ENHard)
        buttonHard.setOnClickListener {
            intent.apply {
                putExtra(DIFFICULTY, 3)
            }
            startActivity(intent)
        }
    }

    private fun setScores() {
        val scoreEasy = findViewById<TextView>(R.id.scoreEasy)
        val scoreMoyen = findViewById<TextView>(R.id.scoreMoyen)
        val scoreHard = findViewById<TextView>(R.id.scoreHard)

        var score = getScore(1)
        scoreEasy.text = "Best : $score"

        score = getScore(2)
        scoreMoyen.text = "Best : $score"

        score = getScore(3)
        scoreHard.text = "Best : $score"
    }

    private fun getScore(id: Int): Int? {
        return QuestionSerieStorage.get(applicationContext).find(id)?.bestScore
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Passer l'événement de touch à GestureDetector
        return gestureDetector.onTouchEvent(event!!) || super.onTouchEvent(event)
    }

    private fun goToDictionaryActivity() {
        val intent = Intent(applicationContext, ENDictionaryActivity::class.java)
        startActivity(intent)
        finish() // Optionnel : terminer l'activité actuelle
    }
}
