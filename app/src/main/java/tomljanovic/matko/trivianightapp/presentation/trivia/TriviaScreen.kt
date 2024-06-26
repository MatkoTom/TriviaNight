package tomljanovic.matko.trivianightapp.presentation.trivia

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber
import tomljanovic.matko.trivianightapp.R
import tomljanovic.matko.trivianightapp.presentation.destinations.EndGameDestination

@Composable
@Destination
@Preview
fun TriviaScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: TriviaViewModel = hiltViewModel(),
    numberOfQuestions: Int = 0,
    playerName: String = ""
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getTriviaQuestions(numberOfQuestions)
    }

    val state = viewModel.triviaState

    val activeQuestion = remember {
        mutableIntStateOf(0)
    }
    var clickedButtonIndex by remember { mutableIntStateOf(-1) }

    val playerScore = remember {
        mutableIntStateOf(0)
    }
    val isClicked = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ScreenBackground(R.drawable.img_background)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(56.dp)
                    )
                }
            } else {
                QuestionToolbar(
                    modifier = Modifier,
                    numberOfQuestions = numberOfQuestions,
                    activeQuestion = activeQuestion
                )
                if (state.questions.isNotEmpty()) {
                    Text(
                        text = Html.fromHtml(state.questions[activeQuestion.intValue].question)
                            .toString(),
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 22.sp
                    )
                    state.questions[activeQuestion.intValue].answers.forEachIndexed { index, answer ->
                        TriviaButtons(
                            buttonText = Html.fromHtml(answer).toString(),
                            drawableId = null,
                            buttonColor = when {
                                index == clickedButtonIndex && answer == state.questions[activeQuestion.intValue].correctAnswer -> {
                                    if (!isClicked.value) playerScore.intValue++
                                    isClicked.value = true
                                    Color.Green
                                }

                                index == clickedButtonIndex && answer != state.questions[activeQuestion.intValue].correctAnswer -> {
                                    isClicked.value = true
                                    Color.Red
                                }

                                else -> Color.White
                            },
                        ) {
                            clickedButtonIndex = index
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    val question = activeQuestion.intValue
                    Text(
                        text = if (question == state.questions.size - 1) "Finish" else "Next Question",
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .clickable {
                                if (question == state.questions.size - 1) {
                                    navigator?.navigate(
                                        EndGameDestination(
                                            maxQuestions = numberOfQuestions,
                                            score = playerScore.intValue,
                                            playerName = playerName
                                        )
                                    )
                                } else {
                                    activeQuestion.intValue += 1
                                    clickedButtonIndex = -1
                                    isClicked.value = false
                                }
                            },
                        style = MaterialTheme.typography.titleLarge
                    )
                } else {
                    Timber.d("Error: ${state.error}")
                }
            }
        }
    }
}

@Composable
fun QuestionToolbar(
    modifier: Modifier = Modifier,
    activeQuestion: MutableState<Int> = mutableIntStateOf(0),
    numberOfQuestions: Int,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = Color(0xFF232F42)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (activeQuestion.value < numberOfQuestions) {
                    "Question ${activeQuestion.value + 1} / $numberOfQuestions"
                } else {
                    "Question ${activeQuestion.value} / $numberOfQuestions"
                },
                color = Color(0xFFFFCB00),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
