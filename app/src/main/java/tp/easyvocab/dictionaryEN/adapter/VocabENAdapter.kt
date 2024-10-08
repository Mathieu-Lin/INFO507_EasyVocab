package tp.easyvocab.dictionaryEN.adapter

import tp.easyvocab.R
import tp.easyvocab.dictionaryEN.model.VocabEN
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


abstract class VocabENAdapter(private val context: Context, private var vocabList: List<VocabEN>) :
    class VocabENHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val value: TextView = itemView.findViewById(R.id.VocabEN_value)
    }

    abstract fun onClickListener(view: View)
    abstract fun onLongClickListener(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabENHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_endictionary, parent, false)
        // view et itemView sont les mêmes instances

        view.setOnClickListener() {
            onClickListener(view)
        }
        view.setOnLongClickListener{
            onLongClickListener(view)
        }
        return VocabENHolder(view)
    }

    override fun onBindViewHolder(holder: VocabENHolder, position: Int) {
        val card = VocabENStorage.get(context).findAll()[position]
        holder.itemView.tag = card.id

        holder.value.text = card.value
    }

    override fun getItemCount(): Int {
        return VocabENStorage.get(context).size()
    }
}
