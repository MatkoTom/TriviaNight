package tomljanovic.matko.trivianightapp.presentation.trivia

import tomljanovic.matko.trivianightapp.domain.model.Question

data class TriviaState(
    val questions: List<Question> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
