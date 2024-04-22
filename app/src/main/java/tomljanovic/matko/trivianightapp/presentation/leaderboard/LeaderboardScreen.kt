package tomljanovic.matko.trivianightapp.presentation.leaderboard

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

// Setup destination as per library docs
@Composable
@Destination()
fun LeaderboardScreen(
    navigator: DestinationsNavigator,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val state = viewModel.leaderboardState
}
