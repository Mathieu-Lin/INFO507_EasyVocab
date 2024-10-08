package EasyVocab.activity

import EasyVocab.model.Question
import EasyVocab.model.QuestionSerie
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import com.example.easyvocab.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionsTest = listOf<Question>(Question(0, "oui", "non", "non", "oui", 2), Question(0, "oui", "non", "oui", "non", 1))
        val serieTest = QuestionSerie(0, questionsTest, 0)
        

        var screen = findViewById<LinearLayout>(R.id.mainScreen)
        screen.setOnClickListener{
            startActivity(Intent(applicationContext, ENDifficultyActivity::class.java))
        }
    }
}