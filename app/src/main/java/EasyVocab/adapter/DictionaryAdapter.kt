package EasyVocab.adapter

import EasyVocab.items.DefinitionItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyvocab.R

class DictionaryAdapter(private val definitions: List<DefinitionItem>) : RecyclerView.Adapter<DictionaryAdapter.DefinitionViewHolder>() {

    // ViewHolder pour chaque item de définition
    class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordText: TextView = itemView.findViewById(R.id.wordText)
        val definitionText: TextView = itemView.findViewById(R.id.definitionText)
        val exampleText: TextView = itemView.findViewById(R.id.exampleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        val definitionItem = definitions[position]

        // Affiche le mot, la nature grammaticale et la définition
        holder.wordText.text = "${definitionItem.word} (${definitionItem.partOfSpeech})"
        holder.definitionText.text = "- ${definitionItem.definition}"

        // Exemple d'utilisation si disponible
        holder.exampleText.text = definitionItem.example?.let { "Ex: $it" } ?: "No example available"
    }

    override fun getItemCount() = definitions.size
}



