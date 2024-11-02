package EasyVocab.items

data class DefinitionItem(
    val word: String,
    val partOfSpeech: String,
    val definition: String,
    val example: String?
)
