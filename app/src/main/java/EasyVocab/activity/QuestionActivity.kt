package EasyVocab.activity

import EasyVocab.storage.QuestionSerieStorage
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyvocab.R

class QuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val idDifficulty = intent.getIntExtra(ENDifficultyActivity.DIFFICULTY, 0)
        val idQuestion = intent.getIntExtra(ENDifficultyActivity.QUESTION, 0)
        val serie = QuestionSerieStorage.get(applicationContext).find(idDifficulty)
        val question = serie?.questions?.get(idQuestion)

        findViewById<TextView>(R.id.question).text = question?.question
        findViewById<Button>(R.id.option1).text = question?.option1
        findViewById<Button>(R.id.option2).text = question?.option2
        findViewById<Button>(R.id.option3).text = question?.option3


    }
}