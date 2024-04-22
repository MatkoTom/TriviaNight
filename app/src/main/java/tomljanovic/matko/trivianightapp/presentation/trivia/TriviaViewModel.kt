package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tomljanovic.matko.trivianightapp.domain.repository.TriviaRepository
import tomljanovic.matko.trivianightapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val repo: TriviaRepository
) : ViewModel() {
    var triviaState by mutableStateOf(TriviaState())

    // Always fetch new data when app gets initialized
    fun getTriviaQuestions(
        numberOfQuestions: Int,
        fetchFromRemote: Boolean = true
    ) = viewModelScope.launch {
        repo.getTriviaQuestions(numberOfQuestions, fetchFromRemote).collect { result ->
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
                    }
                }
            }
        }
    }
}
