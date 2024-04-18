package tomljanovic.matko.trivianightapp.presentation.trivia

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun TriviaScreen(
    navigator: DestinationsNavigator,
    viewModel: TriviaViewModel = hiltViewModel()
) {
    val state = viewModel.triviaState

    if (state.questions.isNotEmpty()) {
        Text(text = state.questions[0].question)
    }
}
