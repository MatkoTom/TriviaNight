package tomljanovic.matko.trivianightapp.presentation.trivia

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import tomljanovic.matko.trivianightapp.R
import tomljanovic.matko.trivianightapp.domain.model.LeaderboardItem
import tomljanovic.matko.trivianightapp.presentation.destinations.LeaderboardScreenDestination
import tomljanovic.matko.trivianightapp.presentation.destinations.StartGameScreenDestination
import tomljanovic.matko.trivianightapp.presentation.leaderboard.LeaderboardViewModel
import tomljanovic.matko.trivianightapp.ui.theme.fontFamily

@Composable
@Destination
@Preview
fun EndGame(
    navigator: DestinationsNavigator? = null,
    leaderboardViewModel: LeaderboardViewModel = hiltViewModel(),
    maxQuestions: Int = 0,
    score: Int = 0,
    playerName : String = ""
) {

    LaunchedEffect(key1 = Unit) {
        leaderboardViewModel.addPlayer(
            LeaderboardItem(
                score = score,
                name = playerName
            )
        )
    }

    ScreenBackground(backgroundId = R.drawable.img_background)
    val doneQuestion = remember {
        mutableIntStateOf(maxQuestions)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        QuestionToolbar(
            modifier = Modifier,
            activeQuestion = doneQuestion,
            numberOfQuestions = maxQuestions
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your score:",
                style = MaterialTheme.typography.displayMedium,
                fontFamily = fontFamily
            )
            Text(
                text = "$score/$maxQuestions",
                style = MaterialTheme.typography.displayMedium,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )
        }

        TriviaButtons(
            drawableId = R.drawable.img_leaderboard,
            buttonText = "Leaderboard"
        ) {
            navigator?.navigate(LeaderboardScreenDestination)
        }

        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable {
                    navigator?.navigate(StartGameScreenDestination)
                },
            text = "Main menu",
            style = MaterialTheme.typography.titleLarge
        )
    }
}