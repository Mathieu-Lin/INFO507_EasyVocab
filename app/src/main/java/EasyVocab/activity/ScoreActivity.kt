package EasyVocab.activity

import EasyVocab.storage.QuestionSerieStorage
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.easyvocab.R

class ScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        val idDifficulty = intent.getIntExtra(QuestionActivity.DIFFICULTY, 1)
        val score = intent.getIntExtra(QuestionActivity.SCORE, 0)
        val congratsView = findViewById<TextView>(R.id.congrats)
        val scoreView = findViewById<TextView>(R.id.score)

        scoreView.text = "$score/10"

        if (score == 10) {
            congratsView.text = "Parfait !"
        } else if (score == 9) {
            congratsView.text = "Presque parfait !"
        } else if (score >= 7) {
            congratsView.text = "Bien !"
        } else if (score >= 4) {
            congratsView.text = "Entraine toi encore !"
        } else if (score >= 1) {
            congratsView.text = "C'est déjà ça..."
        } else {
            congratsView.text = "Même pas un seul point ??"
        }

        if (QuestionSerieStorage.get(applicationContext).find(idDifficulty)?.bestScore!! < score) {
            findViewById<TextView>(R.id.bestScore).visibility = View.VISIBLE
            val questionSerie = QuestionSerieStorage.get(applicationContext).find(idDifficulty)
            if (questionSerie != null) {
                questionSerie.bestScore = score
                QuestionSerieStorage.get(applicationContext).update(idDifficulty, questionSerie)
            }
        }
    }
}