package tomljanovic.matko.trivianightapp.domain.repository

import kotlinx.coroutines.flow.Flow
import tomljanovic.matko.trivianightapp.domain.model.Question
import tomljanovic.matko.trivianightapp.util.Resource

interface TriviaRepository {
    fun getTriviaQuestions(fetchFromRemote: Boolean): Flow<Resource<List<Question>>>
}
