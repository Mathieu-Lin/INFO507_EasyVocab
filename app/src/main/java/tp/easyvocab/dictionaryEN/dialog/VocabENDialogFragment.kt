package tp.easyvocab.dictionaryEN.dialog

import VocabENStorage
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import tp.easyvocab.R
import tp.easyvocab.activity.Updatable
import tp.easyvocab.dictionaryEN.model.VocabEN

class VocabENDialogFragment(private val updatable: Updatable): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_endictionary, null)
        return AlertDialog.Builder(context)
            .setTitle(R.string.dialog_creation)
            .setView(view)
            .setPositiveButton(R.string.dialog_confirm) {_, _ ->
                VocabENStorage.get(requireContext()).insert(
                    VocabEN (
                        0,
                        view.findViewById<EditText>(R.id.VocabEN_value).text.toString(),
                    )
                )
                updatable.update()
            }
            .setNegativeButton(R.string.dialog_cancel, null)
            .create()
    }
}