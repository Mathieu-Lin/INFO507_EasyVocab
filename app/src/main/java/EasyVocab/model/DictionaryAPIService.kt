package EasyVocab.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("api/v2/entries/en/{word}")
    fun getDefinitions(@Path("word") word: String): Call<List<DictionaryResponse>>
}