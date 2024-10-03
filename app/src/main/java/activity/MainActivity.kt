package activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import com.example.easyvocab.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var screen = findViewById<LinearLayout>(R.id.mainScreen)
        screen.setOnClickListener{
            startActivity(Intent(applicationContext, ENDifficultyActivity::class.java))
        }
    }
}