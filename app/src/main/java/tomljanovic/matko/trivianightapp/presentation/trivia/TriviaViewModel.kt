package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import tomljanovic.matko.trivianightapp.domain.repository.TriviaRepository
import tomljanovic.matko.trivianightapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val repo: TriviaRepository
) : ViewModel() {
    var triviaState by mutableStateOf(TriviaState())

    init {
        getTriviaQuestions()
    }

    // Always fetch new data when app gets initialized
    private fun getTriviaQuestions(
        fetchFromRemote: Boolean = true
    ) = viewModelScope.launch {
        repo.getTriviaQuestions(fetchFromRemote).collect { result ->
            when (result) {
                is Resource.Error -> {
                    result.message?.let {
                        triviaState = triviaState.copy(
                            error = it
                        )
                    }
                }

                is Resource.Loading -> triviaState = triviaState.copy(
                    isLoading = result.isLoading
                )

                is Resource.Success -> {
                    result.data?.let {
                        triviaState = triviaState.copy(
                            questions = it
                        )

                        Timber.d("Trivia questions: $it")
                    }
                }
            }

        }
    }
}
