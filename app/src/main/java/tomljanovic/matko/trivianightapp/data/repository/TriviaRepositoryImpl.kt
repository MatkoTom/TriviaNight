package tomljanovic.matko.trivianightapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import tomljanovic.matko.trivianightapp.data.local.TriviaDatabase
import tomljanovic.matko.trivianightapp.data.mappers.toQuestion
import tomljanovic.matko.trivianightapp.data.mappers.toQuestionEntity
import tomljanovic.matko.trivianightapp.data.remote.TriviaApi
import tomljanovic.matko.trivianightapp.domain.model.Question
import tomljanovic.matko.trivianightapp.domain.repository.TriviaRepository
import tomljanovic.matko.trivianightapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TriviaRepositoryImpl @Inject constructor(
    private val triviaApi: TriviaApi,
    db: TriviaDatabase
) : TriviaRepository {

    private val dao = db.dao

    override fun getTriviaQuestions(
        numberOfQuestions: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Question>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            // Use this only if we want to emit old data first
//            val localQuestions = dao.getLocalQuestions()
//            emit(Resource.Success(data = localQuestions.ømap { it.toQuestion() }))

//            val isDbEmpty = localQuestions.isEmpty()
//            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
//            if (shouldLoadFromCache) {
//                emit(Resource.Loading(isLoading = false))
//                return@flow
//            }

            val remoteQuestions = try {
                triviaApi.fetchQuestions(numberOfQuestions).results
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            }

            remoteQuestions?.let { questions ->
                dao.clearQuestions()
                dao.insertQuestions(questions.map { it.toQuestionEntity() })
                emit(Resource.Success(data = dao.getLocalQuestions().map { it.toQuestion() }))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }
}
