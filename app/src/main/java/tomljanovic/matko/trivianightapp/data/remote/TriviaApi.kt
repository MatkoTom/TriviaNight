package tomljanovic.matko.trivianightapp.data.remote

import retrofit2.http.GET
import tomljanovic.matko.trivianightapp.data.remote.dto.QuestionsResponse

interface TriviaApi {

    @GET("api.php?amount=10")
    suspend fun fetchQuestions(): QuestionsResponse

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}
