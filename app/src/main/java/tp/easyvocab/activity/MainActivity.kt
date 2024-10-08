package tp.easyvocab.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tp.easyvocab.R
import tp.easyvocab.dictionaryEN.adapter.VocabENAdapter

class MainActivity : ComponentActivity() {
    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }

    private lateinit var list: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_endictionary)

        list = findViewById(R.id.VocabEN_list)
        list.adapter = object: VocabENAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                var intent = Intent(applicationContext, VocabENActivity::class.java).apply{
                    putExtra(EXTRA_CARD, view.tag as Int)
                    // on ajoute les informations à l'intention
                    // on donne l'id de la card ciblée
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                VocabENStorage.get(applicationContext).delete(view.tag as Int)
                return true
            }
        }

    }
}