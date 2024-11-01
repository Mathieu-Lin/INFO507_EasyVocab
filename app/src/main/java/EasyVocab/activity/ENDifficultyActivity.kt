package EasyVocab.activity

import EasyVocab.storage.QuestionSerieStorage
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyvocab.R

class ENDifficultyActivity : ComponentActivity() {
    companion object {
        const val DIFFICULTY = "DIFFICULTY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endifficulty)

        setButtons()
        setScores()
    }

    private fun setButtons() {
        val intent = Intent(applicationContext, QuestionActivity::class.java)
        val buttonEasy = findViewById<Button>(R.id.ENEasy)
        buttonEasy.setOnClickListener{
            intent.apply{
                putExtra(DIFFICULTY, 1)
            }
            startActivity(intent)
        }

        val buttonMoyen = findViewById<Button>(R.id.ENMoyen)
        buttonMoyen.setOnClickListener{
            intent.apply{
                putExtra(DIFFICULTY, 2)
            }
            startActivity(intent)
        }
        val buttonHard = findViewById<Button>(R.id.ENHard)
        buttonHard.setOnClickListener{
            intent.apply{
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

    fun getScore(id: Int) : Int? {
        return QuestionSerieStorage.get(applicationContext).find(id)?.bestScore
    }
}