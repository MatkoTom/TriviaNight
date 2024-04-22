package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
// Destination here needs to be changed. This will not be the first screen.
@Destination(start = true)
fun TriviaScreen(
    navigator: DestinationsNavigator,
    viewModel: TriviaViewModel = hiltViewModel()
) {
    // Call api with number of questions passed from screen before
    LaunchedEffect(key1 = Unit) {
        viewModel.getTriviaQuestions(20)
    }
    val state = viewModel.triviaState

    if (state.questions.isNotEmpty()) {
        Text(text = state.questions[0].question)
    }
}
