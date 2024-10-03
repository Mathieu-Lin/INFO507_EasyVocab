package dictionaryEN.storage

import dictionaryEN.model.VocabEN
import storage.Storage

class VocabENNoneStorage: Storage<VocabEN> {
    override fun insert(obj: VocabEN): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): VocabEN? = null

    override fun findAll(): List<VocabEN> = emptyList()

    override fun delete(id: Int) = Unit

    override fun update(id: Int, obj: VocabEN) = Unit

}