package tomljanovic.matko.trivianightapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tomljanovic.matko.trivianightapp.data.remote.dto.QuestionsResponse

interface TriviaApi {

    @GET("api.php")
    suspend fun fetchQuestions(
        @Query("amount") numberOfQuestions: Int
    ): QuestionsResponse

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}
