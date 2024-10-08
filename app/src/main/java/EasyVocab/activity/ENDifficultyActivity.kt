package EasyVocab.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyvocab.R

class ENDifficultyActivity : ComponentActivity() {
    companion object {
        const val DIFFICULTY = "DIFFICULTY"
        const val QUESTION = "QUESTION"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endifficulty)

        val intent = Intent(applicationContext, QuestionActivity::class.java)
        intent.apply{
            putExtra(QUESTION, 0)
        }
        val buttonEasy = findViewById<Button>(R.id.ENEasy)
        buttonEasy.setOnClickListener{
            intent.apply{
                putExtra(DIFFICULTY, 0)
            }
            startActivity(intent)
        }

        val buttonMoyen = findViewById<Button>(R.id.ENMoyen)
        buttonMoyen.setOnClickListener{
            intent.apply{
                putExtra(DIFFICULTY, 1)
            }
            startActivity(intent)
        }
        val buttonHard = findViewById<Button>(R.id.ENHard)
        buttonHard.setOnClickListener{
            intent.apply{
                putExtra(DIFFICULTY, 2)
            }
            startActivity(intent)
        }
    }
}