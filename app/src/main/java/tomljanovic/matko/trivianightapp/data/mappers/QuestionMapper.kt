package tomljanovic.matko.trivianightapp.data.mappers

import tomljanovic.matko.trivianightapp.data.local.QuestionEntity
import tomljanovic.matko.trivianightapp.data.remote.dto.ResultsResponse
import tomljanovic.matko.trivianightapp.domain.model.Question

fun QuestionEntity.toQuestion(): Question {
    return Question(
        question = question,
        correctAnswer = correctAnswer,
        answers = answers
    )
}

fun ResultsResponse.toQuestionEntity(): QuestionEntity {
    val answers = incorrectAnswers.apply { add(correctAnswer) }.shuffled()
    return QuestionEntity(
        question = question,
        correctAnswer = correctAnswer,
        answers = answers
    )
}
