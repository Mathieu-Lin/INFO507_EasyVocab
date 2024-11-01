package EasyVocab.adapter

import EasyVocab.storage.VocabENStorage
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyvocab.R

abstract class VocabENAdapter(private val context: Context): RecyclerView.Adapter<VocabENAdapter.VocabENHolder>() {
    class VocabENHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val value: TextView = itemView.findViewById(R.id.VocabEN_value)
    }

    abstract fun onClickListener(view: View)
    abstract fun onLongClickListener(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabENHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocab, parent, false)

        view.setOnClickListener {
            onClickListener(view)
        }
        view.setOnLongClickListener {
            onLongClickListener(view)
        }
        return VocabENHolder(view)
    }

    override fun onBindViewHolder(holder: VocabENHolder, position: Int) {
        val vocabEN = VocabENStorage.get(context).findAll()[position]
        holder.itemView.tag = vocabEN.id
        holder.value.text = vocabEN.value
    }

    override fun getItemCount(): Int {
        return VocabENStorage.get(context).size()
    }
}