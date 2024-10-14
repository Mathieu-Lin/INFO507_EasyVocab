package tp.easyvocab.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tp.easyvocab.R
import tp.easyvocab.dictionaryEN.adapter.VocabENAdapter
import tp.easyvocab.dictionaryEN.request.VocabENRequest

class MainActivity : Updatable, AppCompatActivity() {
    companion object {
        const val EXTRA_VOCAB = "EXTRA_VOCAB"
    }

    private lateinit var list: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_endictionary)

        list = findViewById(R.id.VocabEN_list)
        list.adapter = object: VocabENAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                var intent = Intent(applicationContext, VocabENActivity::class.java).apply{
                    putExtra(EXTRA_VOCAB, view.tag as Int)
                    // on ajoute les informations à l'intention
                    // on donne l'id de la vocabulaire ciblée
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                VocabENStorage.get(applicationContext).delete(view.tag as Int)
                return true
            }

        }


        refresh = findViewById(R.id.VocabEN_refresh)
        findViewById<SwipeRefreshLayout>(R.id.VocabEN_refresh).setOnRefreshListener {
            VocabENRequest(applicationContext, this)
            update()
        }

    }


    override fun update() {
        list.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }


}